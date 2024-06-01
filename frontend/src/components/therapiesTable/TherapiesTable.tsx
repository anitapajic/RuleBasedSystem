import { Pagination, PaginationProps } from "semantic-ui-react";
import '../../../node_modules/semantic-ui-css/semantic.min.css';
import { useState } from "react";
import { formatDate } from "../../utils/functions/formatDateTime";
import { TableWrapper, ScrollableContainer, StyledTable, StyledPagination } from "../patientsTable/PatientsTable.styled";
import { Therapy } from "../../models/Therapy";

type TherapiesTableProps = {
    therapies: Therapy[];
    searchInput: string;
};
export default function TherapiesTable({ therapies, searchInput }: TherapiesTableProps) {
    const [currentPage, setCurrentPage] = useState(1);
    const itemsPerPage = 5;

    const totalNumberOfPages = Math.ceil(therapies.length / itemsPerPage);
    const indexOfLastItem = currentPage * itemsPerPage;
    const indexOfFirstItem = indexOfLastItem - itemsPerPage;
    const currentItems = therapies.slice(indexOfFirstItem, indexOfLastItem);

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
                            <th>Disease</th>
                            <th>Medicine</th>
                            <th>Milligrams</th>
                            <th>Frequency a day</th>
                            <th>Start date</th>
                            <th>End date</th>
                        </tr>
                    </thead>

                    <tbody>
                        {currentItems.length > 0 ? (
                            currentItems.map((therapy) => (
                                <tr key={therapy.id}>
                                    <td>{highlightText(therapy.diagnosis.disease.name, searchInput)}</td>
                                    <td>{highlightText(therapy.medicine.name, searchInput)}</td>
                                    <td>{highlightText(therapy.milligrams.toString(), searchInput)}</td>
                                    <td>{highlightText(therapy.frequency.toString(), searchInput)}</td>
                                    <td>{formatDate(therapy.dateRange.startDate)}</td> 
                                    <td>{formatDate(therapy.dateRange.endDate)}</td>                                 
                                </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan={6}><h2>No data</h2></td>
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