/* eslint-disable @typescript-eslint/no-explicit-any */
import SockJsClient from 'react-stomp';
import { useEffect, useState } from "react";

import '@mobiscroll/react/dist/css/mobiscroll.min.css';
import { localeSr, Segmented, SegmentedGroup, setOptions } from "@mobiscroll/react";
import { Wrapper } from "../DoctorHomePage/DoctorHomePage.styled.tsx";
import { Container } from "../../components/homeInfo/HomeInfo.styled.tsx";
import MonitoringTable from "../../components/monitoring/MonitoringTable.tsx";
import PatientService from "../../services/PatientService/PatientService.tsx";
import { PatientMonitoring } from "../../models/Patient.ts";
import { Name } from '../AllPatientsPage/AllPatientsPage.styled.tsx';
import { StyledSegmented, TableCardContainer } from './MonitoringPage.styled.tsx';
import { Button } from '../../components/newAnamnesisForm/NewAnamnesisForm.styled.tsx';
import customAxios from '../../services/AxiosInterceptor/AxiosInterceptor.tsx';

export default function MonitoringPage() {
    setOptions({
        locale: localeSr,
        theme: 'ios',
        themeVariant: 'light'
    });

    const [selectedMonitoring, setSelectedMonitoring]
        = useState('temperature');

    const [tableData, setTableData] = useState<PatientMonitoring[]>([]);

    const handleSegmentChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setSelectedMonitoring(event.target.value);
    };

    const handleMessage = (topic: string, message: any) => {
        if (topic == "/temperature/all" && selectedMonitoring == "temperature") {
            const newPatientMonitoring: PatientMonitoring[] = tableData.map(item => {
                if (item.id === message.patientId) {
                    return {
                        ...item,
                        monitoringValue: parseFloat(message.temperatureValue.toFixed(1)),
                    };
                }
                return item;
            });
            setTableData(newPatientMonitoring);
        } else if (topic == "/oxygen/all" && selectedMonitoring == "oxygen") {
            const newPatientMonitoring: PatientMonitoring[] = tableData.map(item => {
                if (item.id === message.patientId) {
                    return {
                        ...item,
                        monitoringValue: parseFloat(message.oxygenSaturationPercentage.toFixed(1)),
                    };
                }
                return item;
            });
            setTableData(newPatientMonitoring);
        }
    }

    useEffect(() => {
        setSelectedMonitoring("temperature");
    }, []);

    useEffect(() => {
        const diseaseId: number = selectedMonitoring == "temperature" ? -1 : 2;
        PatientService.findAllWithDisease(diseaseId).then(response => {
            const newData: PatientMonitoring[] = response.data.map((item: any) => ({
                ...item,
                monitoringValue: selectedMonitoring == "temperature" ? 36.5 : 94.5
            }));
            setTableData(newData);
            console.log(response.data);
        }).catch(error => {
            console.error("Error fetching patients: ", error);
        })
    }, [selectedMonitoring]);


    const handleSalmonellosis = () => {
        customAxios.get('/simulation/diarrhea');
    }

    return (
        <div>
            <SockJsClient url='http://localhost:8080/socket' topics={['/temperature/all', '/oxygen/all']}
                onMessage={(msg, topic) => { handleMessage(topic, msg) }}
            />
            <Container>
                <Wrapper>
                    <StyledSegmented>
                        <div className="mbsc-form-group">
                            <SegmentedGroup name="range" onChange={handleSegmentChange}>
                                <Segmented value="temperature" defaultChecked={true}>Temperature</Segmented>
                                <Segmented value="oxygen">Oxygen</Segmented>
                                <Segmented value="salmonellosis">Salmonellosis</Segmented>
                            </SegmentedGroup>
                        </div>
                    </StyledSegmented>
                    {selectedMonitoring == "salmonellosis" ? (
                        <>
                            <Button type="button" onClick={handleSalmonellosis}>
                                Simulate monitoring for salmonellosis
                            </Button>
                        </>
                    ) : (
                        <TableCardContainer>
                            <Name>Therapies</Name>
                            <MonitoringTable monitoringType={selectedMonitoring} patientMonitoring={tableData}></MonitoringTable>
                        </TableCardContainer>
                    )}
                </Wrapper>
            </Container>


        </div>
    )
}