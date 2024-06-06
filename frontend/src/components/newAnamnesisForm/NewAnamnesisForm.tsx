/* eslint-disable @typescript-eslint/no-explicit-any */
/* eslint-disable @typescript-eslint/no-unused-vars */
import { Select, setOptions, localeSr } from '@mobiscroll/react';
import '@mobiscroll/react/dist/css/mobiscroll.min.css';
import { Container, StyledSelectWrapper, Input, InputDescription, Button, Gif } from './NewAnamnesisForm.styled';
import { useEffect, useState } from 'react';
import { Title, Wrapper } from '../../pages/DoctorHomePage/DoctorHomePage.styled';
import { newAnamnesis } from '../../models/Anamnesis';
import useUser from '../../utils/UserContext/useUser';
import AnamnesisService from '../../services/AnamnesisService/AnamnesisService';
import { AnamnesisEvaluation } from '../../models/enums/AnamnesisEvaluation';
import { StyledLabel } from '../shared/styled/SharedStyles.styled';
import TherapyService from '../../services/TherapyService/TherapyService';
import { Therapy } from '../../models/Therapy';
import { formatDate } from '../../utils/functions/formatDateTime';
import { Symptom } from '../../models/Symptom';
import customAxios from '../../services/AxiosInterceptor/AxiosInterceptor';
import { symptomLevel } from '../../models/enums/SymptomLevel';


const defaultAnamnesisEvaluation : AnamnesisEvaluation = {
  symptoms:[],
  level1Symptoms:0,
  level2Symptoms:0,
  bloodAnalysisNeeded:false,
  bloodAnalysisResult:false,
  testNeeded:true,
  possibleDiseaseName: "",
  isConfirmationTestNeeded:false,
  confirmationTestResult:false,
  diseaseProbability:0
}

export default function NewAnamnesisForm() {
  setOptions({
    locale: localeSr,
    theme: 'material',
    themeVariant: 'light'
  });
  const [selectData, setselectData] = useState<Symptom[]>([]);


  useEffect(() => {
      async function fetchSymptomOptions() {
          try {
              const [response] = await Promise.all([
                  customAxios.get('/symptom')
              ]);
              setselectData(response.data);
          } catch (error) {
              console.error('Error fetching symptom options:', error);
          }
      }

      fetchSymptomOptions();
  }, []);

  const [selectedValues, setSelectedValues] = useState<number[]>([]);
  const [patientEmail, setPatientEmail] = useState('');
  const [description, setDescription] = useState('');
  const { user } = useUser();

  const [loading, setLoading] = useState(false);
  const [response, setResponse] = useState<any>(null);
  const [response2, setResponse2] = useState<any>(null);
  const [error, setError] = useState<string | null>(null);

  const [anamnesisEvaluation, setAnamnesisEvaluation] = useState<AnamnesisEvaluation>(defaultAnamnesisEvaluation);
  const [therapy, setTherapy] = useState<Therapy>();

  const handleChangeSymptoms = (event: { value: number[] }) => {
    setSelectedValues(event.value);
  };

  const handleChangePatientEmail = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPatientEmail(event.target.value);
  };

  const handleChangeDescription = (event: React.ChangeEvent<HTMLInputElement>) => {
    setDescription(event.target.value);
  };


  const mapDataToSelectOptions = (data: { id: number; name: string, symptomLevel: symptomLevel; }[]) => {
    return data.map(item => ({ value: item.id, text: item.name, group: item.symptomLevel == symptomLevel.LEVEL_1 ? 'Level 1' : 
    item.symptomLevel == symptomLevel.LEVEL_2 ? 'Level 2' : 
    item.symptomLevel }));
};



  const handleSubmit = () => {

    setLoading(true);
    setError(null);
    setResponse(null);
    setResponse2(null);

    const anamnesis: newAnamnesis = {
      patientEmail: patientEmail,
      patientsSymptomsIds: selectedValues,
      description: description,
      isTestNeeded: true,
      doctorId: user?.userId
    }

    console.log('Anamnesis submitted:', anamnesis);
    AnamnesisService.diagnostic(anamnesis).then((response: any) => {
      console.log("Response: ", response.data);
      setLoading(false);
      setResponse(response.data);
      setAnamnesisEvaluation(response.data);
    })
      .catch((error: any) => {
        console.error("Error in request:", error);
        setLoading(false);
        setError("Error in request: " + error.message);
      });
  };

  const handleSubmitTherapy = () => {

    setLoading(true);
    setError(null);
    setResponse(null);
    setResponse2(null);

    TherapyService.diagnostic(patientEmail).then((response: any) => {
      console.log("Response: ", response.data);
      setLoading(false);
      setResponse2(response.data);
      setTherapy(response.data);
    })
      .catch((error: any) => {
        console.error("Error in request:", error);
        setLoading(false);
        setError("Error in request: " + error.message);
      });
  };


  return (
    <>
      <Container>
        {loading ? (
          <Wrapper>
            <Title>Loading</Title>
            <Gif src="src/assets/icegif-1262.gif" alt="Loading" />
          </Wrapper>
        ) : response ? (
          <Wrapper>
            <Title>Diagnose</Title> 
            <StyledLabel>Possible disease: <b>{anamnesisEvaluation.possibleDiseaseName}</b></StyledLabel>
            <StyledLabel>Disease Probability: <b>{(anamnesisEvaluation.diseaseProbability*100).toFixed(2)}%</b></StyledLabel>
            <StyledLabel>Result of confirmation test: <b>{anamnesisEvaluation.confirmationTestResult ? "POSITIVE !" : "NEGATIVE !"}</b></StyledLabel>
            <StyledLabel><b>Patient {patientEmail} {anamnesisEvaluation.confirmationTestResult ? "has" : "doesn't have"} disease {anamnesisEvaluation.possibleDiseaseName}!</b></StyledLabel>
            {anamnesisEvaluation.confirmationTestResult==true && (
              <Button type="button" onClick={handleSubmitTherapy}>
                Find therapy
              </Button>
            )}
            
          </Wrapper>
        ) : response2? (
          <Wrapper>
            <Title>Therapy for disease {anamnesisEvaluation.possibleDiseaseName}</Title> 
            <StyledLabel>Patient: <b>{patientEmail}</b></StyledLabel>
            <StyledLabel>Medicine name: <b>{therapy?.medicine.name}</b></StyledLabel>
            <StyledLabel>Milligrams: <b>{therapy?.milligrams} mg</b></StyledLabel>
            <StyledLabel>Frequency: <b>{therapy?.frequency} times a day</b></StyledLabel>
            <StyledLabel>From: <b>{formatDate(therapy?.dateRange.startDate)}</b></StyledLabel>
            <StyledLabel>To: <b>{formatDate(therapy?.dateRange.endDate)}</b></StyledLabel>
          </Wrapper>
        ) : (
          <>
            <Title>New Anamnesis</Title>
            <Input
              type="email"
              placeholder="Patient's Email"
              name="patientEmail"
              onChange={handleChangePatientEmail}
              required={true}
            />
            <StyledSelectWrapper>
              <Select
                data={mapDataToSelectOptions(selectData)}
                selectMultiple={true}
                label="Select symptoms"
                inputStyle="outline"
                labelStyle="stacked"
                placeholder="Symptoms"
                onChange={handleChangeSymptoms}
                required={true}
              />
            </StyledSelectWrapper>
            <InputDescription
              type="text"
              placeholder="Description"
              name="description"
              onChange={handleChangeDescription}
            />
            <Button type="button" onClick={handleSubmit}>
              Forward anamnesis
            </Button>
            {error && <div className="error">{error}</div>}
          </>
        )}
      </Container>
    </>
  )
}