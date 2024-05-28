import { Pagination, PaginationProps } from "semantic-ui-react";
import '../../../node_modules/semantic-ui-css/semantic.min.css';
import { useState } from "react";
import { formatDate } from "../../utils/functions/formatDateTime";
import { TableWrapper, ScrollableContainer, StyledTable, StyledPagination } from "./PatientsTable.styled";
import { Patient } from "../../models/Patient";

type PatientsTableProps = {
    patients: Patient[];
    searchInput: string;
};
export default function PatientsTable({ patients, searchInput }: PatientsTableProps) {
    const [currentPage, setCurrentPage] = useState(1);
    const itemsPerPage = 5;

    const totalNumberOfPages = Math.ceil(patients.length / itemsPerPage);
    const indexOfLastItem = currentPage * itemsPerPage;
    const indexOfFirstItem = indexOfLastItem - itemsPerPage;
    const currentItems = patients.slice(indexOfFirstItem, indexOfLastItem);

    const handlePageChange = (event: React.MouseEvent<HTMLAnchorElement>, data: PaginationProps) => {
        if (typeof data.activePage === 'number') {
            setCurrentPage(data.activePage);
        }
    };

    const highlightText = (text: string, search: string) => {
        if (!search.trim()) {
            return text;
        }

        const regex = new RegExp(`(${search})`, 'gi');
        const parts = text.split(regex);

        return parts.map((part, index) =>
            regex.test(part) ? <span key={index} style={{ backgroundColor: 'pink' }}>{part}</span> : part
        );
    };
    return (
        <TableWrapper>
            <ScrollableContainer>
                <StyledTable>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Surname</th>
                            <th>Email</th>
                            <th>Date of birth</th>
                            <th>Weight</th>
                        </tr>
                    </thead>

                    <tbody>
                        {currentItems.length > 0 ? (
                            currentItems.map((patient) => (
                                <tr key={patient.id}>
                                    <td>{highlightText(patient.name, searchInput)}</td>
                                    <td>{highlightText(patient.surname, searchInput)}</td>
                                    <td>{highlightText(patient.email, searchInput)}</td>
                                    <td>{formatDate(patient.birthDate)}</td> 
                                    <td>{highlightText(patient.weight.toString(), searchInput)}</td>                                   
                                </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan={4}><h2>No data</h2></td>
                            </tr>
                        )}
                    </tbody>
                </StyledTable>
            </ScrollableContainer>
            {currentItems.length > 0 && (
                <StyledPagination>
                    <Pagination
                        activePage={currentPage}
                        totalPages={totalNumberOfPages}
                        onPageChange={handlePageChange}
                    />
                </StyledPagination>
            )}


        </TableWrapper>
    )
}