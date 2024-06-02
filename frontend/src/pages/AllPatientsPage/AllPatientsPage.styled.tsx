import styled from "styled-components";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

export const Name = styled.h2`
  font-size: 28px;
  font-weight: bold;
  text-align:center;
  padding:10px;
  padding-left: 20px;
  color:rgb(77, 75, 75);
`;

export const TableCardContainer = styled.div`
  border-radius: 15px;
  padding:20px;
  background-color: whitesmoke;
  box-shadow: 10px 12px 20px rgba(0, 0, 0, 0.1); 
  max-width:80%;
  min-width: 65%;
`;

export const StyledInputSearch = styled.input`
  width: 170px;
  padding: 8px;
  border: 1px solid rgb(77, 75, 75);;
  border-radius: 10px;
  font-size: ${({ theme }) => theme.fontSizes.standard};
  margin: 5px;

  &::placeholder, &::placeholder {
    text-align: left;
    font-size: 16px;
  }
`;

export const StyledFontAwesomeIcon3 = styled(FontAwesomeIcon)`
  margin-left: 5px;
  font-size: large;
  color: rgb(77, 75, 75);
`;

export const AllInputContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap;
  gap: 10px;
`;