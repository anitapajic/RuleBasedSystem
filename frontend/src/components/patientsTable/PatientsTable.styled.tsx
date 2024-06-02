import styled from "styled-components";


export const StyledTable = styled.table`
  //margin: 10px;
  width: 100%;
  border-collapse: collapse;
  border-radius: 10px;
  margin-top: 20px;
  margin-bottom:20px;
  
  th, td {
    border: 1px solid #a1cdca;
    padding: 8px;
    text-align: center;
  }

  th {
    background-color: ${({ theme }) => theme.colors.main2};
    text-align: center;
    color: white;
    
  }
  
  tr{
    :hover{
      cursor: pointer;
    }
  }

  tr:nth-child(even) {background-color: rgb(209, 211, 211);}
`;

export const ScrollableContainer = styled.div`
    height: auto;  // Adjust the height as needed
    overflow-y: auto; // Enables scrolling
    overflow-x: hidden;
    width:100%;
`;

export const StyledPagination = styled.div`
    padding: 20px;
    margin-left:10px;
`;
export const TableWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center ;
  justify-content: center;
`;