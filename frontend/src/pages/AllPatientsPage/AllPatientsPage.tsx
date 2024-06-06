/* eslint-disable @typescript-eslint/no-unused-vars */
import { useEffect, useState } from "react";
import { Patient } from "../../models/Patient";
import PatientService from "../../services/PatientService/PatientService";
import { TableCardContainer, Name, StyledFontAwesomeIcon3, StyledInputSearch, AllInputContainer } from "./AllPatientsPage.styled";
import PatientsTable from "../../components/patientsTable/PatientsTable";
import { faSearch } from "@fortawesome/free-solid-svg-icons";
import { Container } from "../../components/homeInfo/HomeInfo.styled";

export default function AllPatientsPage() {
    const [searchInput, setSearchInput] = useState('');
    const [tableData, setTableData] = useState<Patient[]>([]);

    useEffect(() => {
        PatientService.findAll().then(response => {
            setTableData(response.data);
            console.log(response.data);
        }).catch(error => {
            console.error("Error fetching patients: ", error);
        });
    }, []);

    const filteredPatients = tableData.filter(patient => {
        const matchesSearch = patient.email.toLowerCase().includes(searchInput.toLowerCase()) ||
            patient.name.toLowerCase().includes(searchInput.toLowerCase()) ||
            patient.surname.toLowerCase().includes(searchInput.toLowerCase());
        return matchesSearch;
    });

    return (
        <>
        <Container>
            <TableCardContainer>
                <Name>Patients</Name>
                <AllInputContainer>
                    <StyledFontAwesomeIcon3 icon={faSearch} />
                    <StyledInputSearch
                        type="text"
                        placeholder="Search patients"
                        value={searchInput}
                        onChange={(e) => setSearchInput(e.target.value)}
                    />
                </AllInputContainer>
                <PatientsTable patients={filteredPatients} searchInput={searchInput} />
            </TableCardContainer>
        </Container>
        </>
    )
}