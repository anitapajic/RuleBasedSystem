/* eslint-disable @typescript-eslint/no-unused-vars */
import { useEffect, useState } from "react";
import { TableCardContainer, Name, StyledFontAwesomeIcon3, StyledInputSearch, AllInputContainer } from "../AllPatientsPage/AllPatientsPage.styled";
import { faSearch } from "@fortawesome/free-solid-svg-icons";
import { Container } from "../../components/homeInfo/HomeInfo.styled";
import { Disease } from "../../models/Disease";
import DiseaseService from "../../services/DiseaseService/DiseaseService";
import DiseaseTable from "../../components/diseasesTable/DiseasesTable";

export default function DiseasesPage() {
    const [searchInput, setSearchInput] = useState('');
    const [tableData, setTableData] = useState<Disease[]>([]);

    useEffect(() => {
        DiseaseService.findAll().then(response => {
            setTableData(response.data);
            console.log(response.data);
        }).catch(error => {
            console.error("Error fetching diseases: ", error);
        });
    }, []);

    const filteredDiseases = tableData.filter(patient => {
        const matchesSearch = patient.name.toLowerCase().includes(searchInput.toLowerCase()) ||
            patient.testType.toLowerCase().includes(searchInput.toLowerCase());
        return matchesSearch;
    });
    

    return (
        <>
        <Container>
            <TableCardContainer>
                <Name>Diseases</Name>
                <AllInputContainer>
                    <StyledFontAwesomeIcon3 icon={faSearch} />
                    <StyledInputSearch
                        type="text"
                        placeholder="Search diseases"
                        value={searchInput}
                        onChange={(e) => setSearchInput(e.target.value)}
                    />
                </AllInputContainer>
                <DiseaseTable diseases={filteredDiseases} searchInput={searchInput} />
            </TableCardContainer>
        </Container>
        </>
    )
}