/* eslint-disable @typescript-eslint/no-unused-vars */
import { useEffect, useState } from "react";
import { TableCardContainer, Name, StyledFontAwesomeIcon3, StyledInputSearch, AllInputContainer } from "../AllPatientsPage/AllPatientsPage.styled";
import { faSearch } from "@fortawesome/free-solid-svg-icons";
import { Container } from "../../components/homeInfo/HomeInfo.styled";
import { Therapy } from "../../models/Therapy";
import TherapyService from "../../services/TherapyService/TherapyService";
import useUser from "../../utils/UserContext/useUser";
import TherapiesTable from "../../components/therapiesTable/TherapiesTable";

export default function ThreapiesPage() {
    const [searchInput, setSearchInput] = useState('');
    const [tableData, setTableData] = useState<Therapy[]>([]);
    const {user} = useUser();

    useEffect(() => {
        TherapyService.findByPatientId(user?.userId).then(response => {
            setTableData(response.data);
            console.log(response.data);
        }).catch(error => {
            console.error("Error fetching therapies: ", error);
        });
    }, [user?.userId]);

    const filteredPatients = tableData.filter(therapy => {
        const matchesSearch = therapy.diagnosis.disease.name.toLowerCase().includes(searchInput.toLowerCase());
        return matchesSearch;
    });

    return (
        <>
        <Container>
            <TableCardContainer>
                <Name>Therapies</Name>
                <AllInputContainer>
                    <StyledFontAwesomeIcon3 icon={faSearch} />
                    <StyledInputSearch
                        type="text"
                        placeholder="Search therapies"
                        value={searchInput}
                        onChange={(e) => setSearchInput(e.target.value)}
                    />
                </AllInputContainer>
                <TherapiesTable therapies={filteredPatients} searchInput={searchInput} />
            </TableCardContainer>
        </Container>
        </>
    )
}