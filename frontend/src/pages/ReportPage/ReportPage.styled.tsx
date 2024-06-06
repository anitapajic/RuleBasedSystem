import styled from "styled-components";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

export const StyledSegmented = styled.div`
    width: 200%;
    padding: 20px;
    max-width: 250%;
`
export const StyledSelectWrapper = styled.div`
    .mbsc-select {
        border-radius: 5px;    
        font-size: 16px; 
        background-color: transparent; 
        width: 300px;
        border: 1px solid grey;
    }

    .mbsc-label {
        color: black;           
        font-weight: bold; 
        background-color: transparent;        
    }
`;
export const CustomInputLabel = styled.label`
  font-size:  ${({ theme }) => theme.fontSizes.standard};
  font-weight: bold;
`;
export const AllInputContainer = styled.div`
margin-top: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap;
  gap: 10px;
`;
export const InputContainer = styled.div`

`;
export const StyledFontAwesomeIcon = styled(FontAwesomeIcon)`
  margin-left: 5px;
`;

export const ClearDatesButton = styled.button`
  background-color: whitesmoke;
  color: black;
  border: 1px solid grey;
  padding: 7px;
  padding-right: 9px;
  border-radius: 8px;
  cursor: pointer;
  font-size: 1em;

  &:hover {
    background-color: ${({ theme }) => theme.colors.main2};
    border: 1px solid  grey;
    color: white;
  }

  &:focus {
    outline: none;
  }
`;

export const Name = styled.p`
  font-size: 18px;
  font-weight: bold;
  text-align:center;
  padding:10px;
  //padding-left: 20px;
  color:rgb(77, 75, 75);
`;