/* eslint-disable @typescript-eslint/no-explicit-any */
/* eslint-disable @typescript-eslint/no-unused-vars */
import { Select, setOptions, localeSr } from '@mobiscroll/react';
import '@mobiscroll/react/dist/css/mobiscroll.min.css';
import { Container, StyledSelectWrapper, Input, InputDescription, Button, Gif } from './NewAnamnesisForm.styled';
import { useState } from 'react';
import { Title, Wrapper } from '../../pages/DoctorHomePage/DoctorHomePage.styled';
import { newAnamnesis } from '../../models/Anamnesis';
import useUser from '../../utils/UserContext/useUser';
import AnamnesisService from '../../services/AnamnesisService/AnamnesisService';
import { AnamnesisEvaluation } from '../../models/enums/AnamnesisEvaluation';
import { StyledLabel } from '../shared/styled/SharedStyles.styled';


const multipleData = [
  { text: 'Bol u grlu', group: 'Level 1', value: 1 },
  { text: 'Poteskoce pri gutanju', group: 'Level 1', value: 2 },
  { text: 'Povisena temperatura', group: 'Level 1', value: 3 },
  { text: 'Belicaste tacke na krajnicima', group: 'Level 2', value: 4 },
  { text: 'Crvenilo grla', group: 'Level 2', value: 5 },
  { text: 'Bolni limfni cvorovi', group: 'Level 2', value: 6 },

  { text: 'Kasalj', group: 'Level 1', value: 7 },
  { text: 'Zimica', group: 'Level 1', value: 8 },
  { text: 'Bolovi u grudima', group: 'Level 1', value: 9 },
  { text: 'Otezano disanje', group: 'Level 1', value: 10 },
  { text: 'Krckanje u plucima', group: 'Level 2', value: 11 },

  { text: 'Hronican kasalj sa krvavom pljuvackom', group: 'Level 1', value: 12 },
  { text: 'Gubitak telesne tezine', group: 'Level 1', value: 13 },
  { text: 'Nocno znojenje', group: 'Level 1', value: 14 },

  { text: 'Dijareja', group: 'Level 2', value: 15 },
  { text: 'Bolovi u stomaku', group: 'Level 1', value: 16 },
  { text: 'Povracanje', group: 'Level 1', value: 17 },

  { text: 'Jaka glavobolja', group: 'Level 1', value: 18 },
  { text: 'Pojava osipa', group: 'Level 1', value: 19 },
  { text: 'Ukocenost vrata', group: 'Level 2', value: 20 },
  { text: 'Zbunjenost ili promene u ponasanju', group: 'Level 2', value: 21 },
];

const booleanOptions = [
  { text: 'YES', value: 1 },
  { text: 'NO', value: 2 },
]

const defaultAnamnesisEvaluation : AnamnesisEvaluation = {
  symptoms:[],
  level1Symptoms:0,
  level2Symptoms:0,
  bloodAnalysisNeeded:false,
  bloodAnalysisResult:false,
  testNeeded:false,
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

  const [selectedValues, setSelectedValues] = useState<number[]>([]);
  const [patientEmail, setPatientEmail] = useState('');
  const [description, setDescription] = useState('');
  const [isTestNeeded, setIsTestNeeded] = useState<number>();
  const { user } = useUser();

  const [loading, setLoading] = useState(false);
  const [response, setResponse] = useState<any>(null);
  const [error, setError] = useState<string | null>(null);

  const [anamnesisEvaluation, setAnamnesisEvaluation] = useState<AnamnesisEvaluation>(defaultAnamnesisEvaluation);

  const handleChangeSymptoms = (event: { value: number[] }) => {
    setSelectedValues(event.value);
  };

  const handleChangePatientEmail = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPatientEmail(event.target.value);
  };

  const handleChangeDescription = (event: React.ChangeEvent<HTMLInputElement>) => {
    setDescription(event.target.value);
  };

  const handleChangeIsTestNeeded = (event: { value: number }) => {
    setIsTestNeeded(event.value);
  };



  const handleSubmit = () => {

    setLoading(true);
    setError(null);
    setResponse(null);

    const anamnesis: newAnamnesis = {
      patientEmail: patientEmail,
      patientsSymptomsIds: selectedValues,
      description: description,
      isTestNeeded: isTestNeeded == 1 ? true : false,
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
                data={multipleData}
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
            <StyledSelectWrapper>
              <Select
                data={booleanOptions}
                selectMultiple={false}
                label="Is test needed"
                inputStyle="outline"
                labelStyle="stacked"
                placeholder="Select an option"
                onChange={handleChangeIsTestNeeded}
                required={true}
              />
            </StyledSelectWrapper>
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