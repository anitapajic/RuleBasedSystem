import SockJsClient from 'react-stomp';
import { useEffect, useState } from "react";

import '@mobiscroll/react/dist/css/mobiscroll.min.css';
import {localeSr, Segmented, SegmentedGroup, setOptions} from "@mobiscroll/react";
import {StyledSegmented} from "../ReportPage/ReportPage.styled.tsx";
import {Wrapper} from "../DoctorHomePage/DoctorHomePage.styled.tsx";
import {Container} from "../../components/homeInfo/HomeInfo.styled.tsx";
import MonitoringTable from "../../components/monitoring/MonitoringTable.tsx";
import PatientService from "../../services/PatientService/PatientService.tsx";
import {Patient, PatientMonitoring} from "../../models/Patient.ts";
import {Simulate} from "react-dom/test-utils";
import error = Simulate.error;
import {toast} from "react-toastify";

export default function MonitoringPage () {
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
        // setSelectedValue(null); // Reset selected value when report changes
        // setQueryData(null);
    };

    const handleMessage = (topic: string, message: any) => {
        if (topic == "/temperature/all" && selectedMonitoring == "temperature"){
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
        } else if (topic == "/oxygen/all" && selectedMonitoring == "oxygen"){
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
                            </SegmentedGroup>
                        </div>
                    </StyledSegmented>
                    <MonitoringTable monitoringType={selectedMonitoring} patientMonitoring={tableData}></MonitoringTable>
                </Wrapper>
            </Container>


        </div>
    )
}