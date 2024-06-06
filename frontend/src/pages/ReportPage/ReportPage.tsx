/* eslint-disable @typescript-eslint/no-explicit-any */
/* eslint-disable @typescript-eslint/no-unused-vars */
import QueryChart from "../../components/queryChart/QueryChart";
import { Segmented, SegmentedGroup, setOptions, localeSr, Select } from '@mobiscroll/react';
import { useEffect, useState } from "react";
import '@mobiscroll/react/dist/css/mobiscroll.min.css';
import { Container } from "../../components/homeInfo/HomeInfo.styled";
import { Wrapper } from "../DoctorHomePage/DoctorHomePage.styled";
import { AllInputContainer, ClearDatesButton, CustomInputLabel, InputContainer, StyledFontAwesomeIcon, StyledSegmented, StyledSelectWrapper } from "./ReportPage.styled";
import { QueryDTO } from "../../models/QueryDTO";
import customAxios from "../../services/AxiosInterceptor/AxiosInterceptor";
import { Medicine } from "../../models/Medicine";
import { Ingredient } from "../../models/Ingredient";
import { Symptom } from "../../models/Symptom";
import { faRefresh } from "@fortawesome/free-solid-svg-icons";
import { StyledInput } from "../../components/shared/styled/SharedStyles.styled";

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
    const [dataReport3, setDataReport3] = useState<Symptom[]>([]);

    const [startDate, setStartDate] = useState<string | null>(null);
    const [endDate, setEndDate] = useState<string | null>(null);

    useEffect(() => {
        async function fetchReportOptions() {
            try {
                const [report1Response, report2Response, report3Response] = await Promise.all([
                    customAxios.get('/medicine'),
                    customAxios.get('/ingredient'),
                    customAxios.get('/symptom')
                ]);
                setDataReport1(report1Response.data);
                setDataReport2(report2Response.data);
                setDataReport3(report3Response.data);
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
                    const params: any = {};
                    if (startDate) params.startDate = `${startDate}T00:00:00`;
                    if (endDate) params.endDate = `${endDate}T23:59:59`;
                    response = await customAxios.get(`/report/report3/${value}`, { params });
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
    }, [selectedReport, selectedValue, startDate, endDate]);

    const handleSegmentChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setSelectedReport(event.target.value);
        setSelectedValue(null); // Reset selected value when report changes
        setQueryData(null);
        setStartDate(null);
        setStartDate(null);
        setEndDate(null);
    };

    const handleChange = (event: { value: number }) => {
        setSelectedValue(event.value);
    };
    const mapDataToSelectOptions = (data: { id: number; name: string }[]) => {
        return data.map(item => ({ value: item.id, text: item.name }));
    };
    const clearDateFilters = () => {
        setStartDate(null);
        setEndDate(null);
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
                    {selectedReport === '1' && (
                        <StyledSelectWrapper>
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
                        </StyledSelectWrapper>
                    )}
                    {selectedReport === '2' && (
                        <StyledSelectWrapper>
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
                        </StyledSelectWrapper>
                    )}
                    {selectedReport === '3' && (
                        <>
                        <AllInputContainer>
                                <InputContainer>
                                    <CustomInputLabel>From: </CustomInputLabel>
                                    <StyledInput
                                        type="date"
                                        value={startDate ?? ''}
                                        onChange={(e) => setStartDate(e.target.value || null)}
                                        placeholder="Choose start date"
                                        isValid={true}
                                    />
                                </InputContainer>
                                <InputContainer>
                                    <CustomInputLabel>To: </CustomInputLabel>
                                    <StyledInput
                                        type="date"
                                        value={endDate ?? ''}
                                        onChange={(e) => setEndDate(e.target.value || null)}
                                        min={startDate ?? ''}
                                        placeholder="Choose end date"
                                        isValid={true}
                                    />
                                </InputContainer>
                                <ClearDatesButton onClick={clearDateFilters}>{<StyledFontAwesomeIcon icon={faRefresh} />} </ClearDatesButton>
                            </AllInputContainer>
                            <StyledSelectWrapper>
                                <Select
                                    data={mapDataToSelectOptions(dataReport3)}
                                    selectMultiple={false}
                                    label="Select symptom for Report 3"
                                    inputStyle="outline"
                                    labelStyle="stacked"
                                    placeholder="Symptoms"
                                    onChange={handleChange}
                                    required={true}
                                />
                            </StyledSelectWrapper>
                        </>
                    )}
                    {queryData && <QueryChart queryData={queryData} />}
                </Wrapper>
            </Container>
        </>
    )
}