import { Pagination, PaginationProps } from "semantic-ui-react";
import '../../../node_modules/semantic-ui-css/semantic.min.css';
import { useState } from "react";
import { TableWrapper, ScrollableContainer, StyledTable, StyledPagination } from "../patientsTable/PatientsTable.styled";
import { Disease } from "../../models/Disease";
import { TestType } from "../../models/enums/TestType";
import { testTypeMappings } from "../../utils/data";

type DiseasesTableProps = {
    diseases: Disease[];
    searchInput: string;
};
export default function DiseaseTable({ diseases, searchInput }: DiseasesTableProps) {
    const [currentPage, setCurrentPage] = useState(1);
    const itemsPerPage = 3;

    const totalNumberOfPages = Math.ceil(diseases.length / itemsPerPage);
    const indexOfLastItem = currentPage * itemsPerPage;
    const indexOfFirstItem = indexOfLastItem - itemsPerPage;
    const currentItems = diseases.slice(indexOfFirstItem, indexOfLastItem);

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

    const getDisplayName = (testType: TestType): string => {
        return testTypeMappings[testType];
      };
      
    return (
        <TableWrapper>
            <ScrollableContainer>
                <StyledTable>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Test name</th>
                        </tr>
                    </thead>

                    <tbody>
                        {currentItems.length > 0 ? (
                            currentItems.map((disease) => (
                                <tr key={disease.id}>
                                    <td>{highlightText(disease.name, searchInput)}</td>
                                    <td>{highlightText(getDisplayName(disease.testType), searchInput)}</td>
                                </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan={2}><h2>No data</h2></td>
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