import { styled } from "styled-components";

export const CenteredToast = styled.div`
  background-color: ${({theme}) => theme.colors.whitesmoke};
  color: ${({theme}) => theme.colors.main2};
  padding: 16px;
  border-radius: 8px;
  border: 1.5px solid ${({theme}) => theme.colors.secondColor};
  width: 90%; 
  text-align: center;
  font-weight: bold;
`;