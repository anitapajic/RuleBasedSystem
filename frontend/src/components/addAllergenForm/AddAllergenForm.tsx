/* eslint-disable @typescript-eslint/no-explicit-any */
/* eslint-disable @typescript-eslint/no-unused-vars */
import { Button, Container, StyledSelectWrapper } from "../newAnamnesisForm/NewAnamnesisForm.styled";
import { Select, setOptions, localeSr } from '@mobiscroll/react';
import '@mobiscroll/react/dist/css/mobiscroll.min.css';
import { Title } from "../../pages/DoctorHomePage/DoctorHomePage.styled";
import { useState } from "react";
import useUser from "../../utils/UserContext/useUser";
import { AddAllergen } from "../../models/Ingredient";
import PatientService from "../../services/PatientService/PatientService";

export type AddAllergenFormProps = {
  onSubmit: () => void;
};

const multipleData = [

  { text: 'Penicilin V', value: 1 },
  { text: 'Laktoza', value: 2 },
  { text: 'Povidon', value: 3 },

  { text: 'Eritromicin', value: 4 },

  { text: 'Mikroceluloza', value: 5 },
  { text: 'Hipromeloza', value: 6 },

  { text: 'Azitromicin', value: 7 },
  { text: 'Kukuruzni skrob', value: 8 },
  { text: 'Titanijum dioksid', value: 9 },

  { text: 'Levofloxacin', value: 10 },
  { text: 'Krospovidon', value: 11 },
  { text: 'Hidroksipropilmetilceluloza', value: 12 },

  { text: 'Amoksicilin', value: 13 },
  { text: 'Magnezijum stearat', value: 14 },

  { text: 'Doksiciklin', value: 15 },

  { text: 'Rifampicin', value: 16 },

  { text: 'Pirazinamid', value: 17 },

  { text: 'Etambutol', value: 18 },

  { text: 'Ciprofloksacin', value: 19 },

  { text: 'Ceftriakson', value: 20 },
  { text: 'Natrijum hidroksid', value: 21 },
  { text: 'Limunska kiselina', value: 22 },

  { text: 'Prednizon', value: 23 },
  { text: 'Kalcijum stearat', value: 24 },

  { text: 'Deksametazon', value: 25 },
  { text: 'Skrob', value: 26 },

  { text: 'Metilprednizolon', value: 27 },

  { text: 'Ceftriakson', value: 28 },

  { text: 'Cefotaksim', value: 29 },

  { text: 'Isoniazid', value: 30 },

];


export default function AddAllergenForm({ onSubmit }: AddAllergenFormProps) {
  setOptions({
    locale: localeSr,
    theme: 'material',
    themeVariant: 'light'
  });

  const [selectedValues, setSelectedValues] = useState<number[]>([]);
  const { user } = useUser();


  const handleChangeAllergens = (event: { value: number[] }) => {
    setSelectedValues(event.value);
  };

  const handleSubmit = () => {

    const addAllergen: AddAllergen = {
      patientId: user?.userId,
      allergens: selectedValues
    }

    PatientService.addAllergens(addAllergen).then((response: any) => {
      console.log("Response: ", response.data);
      onSubmit();
    })
      .catch((error: any) => {
        console.error("Error in request:", error);
      });
  };

  return (
    <>
      <Container>
        <Title>Select your allergens</Title>
        <StyledSelectWrapper>
          <Select
            data={multipleData}
            selectMultiple={true}
            label="Select allergens"
            inputStyle="outline"
            labelStyle="stacked"
            placeholder="Allergens"
            onChange={handleChangeAllergens}
            required={true}
          />
        </StyledSelectWrapper>
        <Button type="button" onClick={handleSubmit}>
          Add allergens
        </Button>
      </Container>
    </>
  )
}