/* eslint-disable @typescript-eslint/no-explicit-any */
/* eslint-disable @typescript-eslint/no-unused-vars */
import { Button, Container, StyledSelectWrapper } from "../newAnamnesisForm/NewAnamnesisForm.styled";
import { Select, setOptions, localeSr } from '@mobiscroll/react';
import '@mobiscroll/react/dist/css/mobiscroll.min.css';
import { Title } from "../../pages/DoctorHomePage/DoctorHomePage.styled";
import { useEffect, useState } from "react";
import useUser from "../../utils/UserContext/useUser";
import { AddAllergen, Ingredient } from "../../models/Ingredient";
import PatientService from "../../services/PatientService/PatientService";
import customAxios from "../../services/AxiosInterceptor/AxiosInterceptor";

export type AddAllergenFormProps = {
  onSubmit: () => void;
};

export default function AddAllergenForm({ onSubmit }: AddAllergenFormProps) {
  setOptions({
    locale: localeSr,
    theme: 'material',
    themeVariant: 'light'
  });

  const [selectedValues, setSelectedValues] = useState<number[]>([]);
  const { user } = useUser();
  const [selectData, setSelectData] = useState<Ingredient[]>([]);

  useEffect(() => {
    async function fetchSelectOptions() {
        try {
            const [response] = await Promise.all([
                customAxios.get('/ingredient')
            ]);
            setSelectData(response.data);

        } catch (error) {
            console.error('Error fetching ingredients:', error);
        }
    }

    fetchSelectOptions();
}, []);

const mapDataToSelectOptions = (data: { id: number; name: string }[]) => {
  return data.map(item => ({ value: item.id, text: item.name }));
};

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
            data={mapDataToSelectOptions(selectData)}
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