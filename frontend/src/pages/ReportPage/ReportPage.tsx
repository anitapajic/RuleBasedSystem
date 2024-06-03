/* eslint-disable @typescript-eslint/no-unused-vars */
import QueryChart from "../../components/queryChart/QueryChart";
import { Segmented, SegmentedGroup, setOptions, localeSr, Select } from '@mobiscroll/react';
import { useEffect, useState } from "react";
import '@mobiscroll/react/dist/css/mobiscroll.min.css';
import { Container } from "../../components/homeInfo/HomeInfo.styled";
import { Wrapper } from "../DoctorHomePage/DoctorHomePage.styled";
import { StyledSegmented, StyledSelectWrapper } from "./ReportPage.styled";
import { QueryDTO } from "../../models/QueryDTO";
import { multipleData } from "../../components/newAnamnesisForm/NewAnamnesisForm";
import customAxios from "../../services/AxiosInterceptor/AxiosInterceptor";
import { Medicine } from "../../models/Medicine";
import { Ingredient } from "../../models/Ingredient";

export default function ReportPage() {
    setOptions({
        locale: localeSr,
        theme: 'ios',
        themeVariant: 'light'
    });
    const [selectedReport, setSelectedReport] = useState('3');
    const [queryData, setQueryData] = useState<QueryDTO | null>(null);
    const [selectedValue, setSelectedValue] = useState<number | null>(null);
    const [dataReport1, setDataReport1] = useState<Medicine[]>([]);
    const [dataReport2, setDataReport2] = useState<Ingredient[]>([]);

    useEffect(() => {
        async function fetchReportOptions() {
            try {
                const [report1Response, report2Response] = await Promise.all([
                    customAxios.get('/medicine'),
                    customAxios.get('/ingredient')
                ]);
                setDataReport1(report1Response.data);
                setDataReport2(report2Response.data);
            } catch (error) {
                console.error('Error fetching report options:', error);
            }
        }

        fetchReportOptions();
    }, []);

    useEffect(() => {
        async function fetchData(reportId: string, value: number) {
            try {
                let response;
                if (reportId === '1') {
                    response = await customAxios.get(`/report/report1/${value}`);
                } else if (reportId === '2') {
                    response = await customAxios.get(`/report/report2/${value}`);
                } else {
                    response = await customAxios.get(`/report/report3/${value}`);
                }
                console.log(response.data);
                setQueryData(response.data);
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        }

        if (selectedValue !== null) {
            fetchData(selectedReport, selectedValue);
        }
    }, [selectedReport, selectedValue]);

    const handleSegmentChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setSelectedReport(event.target.value);
        setSelectedValue(null); // Reset selected value when report changes
        setQueryData(null);
    };

    const handleChange = (event: { value: number }) => {
        setSelectedValue(event.value);
    };
      const mapDataToSelectOptions = (data: { id: number; name: string }[]) => {
        return data.map(item => ({ value: item.id, text: item.name }));
    };

    return (
        <>
            <Container>
                <Wrapper>
                    <StyledSegmented>
                        <div className="mbsc-form-group">
                            <SegmentedGroup name="range" onChange={handleSegmentChange}>
                                <Segmented value="1">Report 1</Segmented>
                                <Segmented value="2">Report 2</Segmented>
                                <Segmented value="3" defaultChecked={true}>Report 3</Segmented>
                            </SegmentedGroup>
                        </div>
                    </StyledSegmented>
                    <StyledSelectWrapper>
                    {selectedReport === '1' && (
                            <Select
                                data={mapDataToSelectOptions(dataReport1)}
                                selectMultiple={false}
                                label="Select medicine for Report 1"
                                inputStyle="outline"
                                labelStyle="stacked"
                                placeholder="Medicines"
                                onChange={handleChange}
                                required={true}
                            />
                        )}
                        {selectedReport === '2' && (
                            <Select
                                data={mapDataToSelectOptions(dataReport2)}
                                selectMultiple={false}
                                label="Select allergen for Report 2"
                                inputStyle="outline"
                                labelStyle="stacked"
                                placeholder="Allergens"
                                onChange={handleChange}
                                required={true}
                            />
                        )}
                        {selectedReport === '3' && (
                            <Select
                                data={multipleData}
                                selectMultiple={false}
                                label="Select symptom for Report 3"
                                inputStyle="outline"
                                labelStyle="stacked"
                                placeholder="Symptoms"
                                onChange={handleChange}
                                required={true}
                            />
                        )}
                    </StyledSelectWrapper>
                    {queryData && <QueryChart queryData={queryData} />}
                </Wrapper>
            </Container>
        </>
    )
}