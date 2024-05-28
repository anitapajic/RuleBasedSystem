import styled from "styled-components";

export const StyledSelectWrapper = styled.div`
    .mbsc-select {
        border-radius: 4px;    
        font-size: 16px; 
        background-color: transparent; 
        width: 95%;
    }

    .mbsc-label {
        color: black;           
        font-weight: bold; 
        background-color: transparent;        
    }
`;

export const Container = styled.form`
    padding: 35px;
    gap: 24px;
    background: whitesmoke;
    min-width: 40%;
    border-radius: 15px;
    max-width: 600px;
    //border: 1px solid grey;
    box-shadow: 10px 12px 20px rgba(0, 0, 0, 0.1); 
`

export const Input = styled.input`
  background-color: #dfdddd;
  border: 1px solid ${({ theme }) => theme.colors.grey};
  padding: 12px 15px;
  margin-left: 15px;
  width: 95%;
  border-radius: 7px;
  border: 0.5px solid grey;
`;

export const InputDescription = styled.input`
  background-color: #dfdddd;
  border: 1px solid ${({ theme }) => theme.colors.grey};
  padding: 12px 15px;
  margin-left: 15px;
  width: 95%;
  border-radius: 7px;
  min-height: 60px;
  border: 0.5px solid grey;
`;

export const Button = styled.button`
    padding: 14px 18px;
    color: white;
    border: 2px solid transparent;
    border-radius: 50px;
    outline: transparent;
    background-color: #f54263;
    font-size: 18px;
    font-family: 'Rubik', sans-serif;
    letter-spacing: .8px;
    cursor: pointer;
    transition: all .4s ease;
    width: 100%;
    margin-top: 20px;
    margin-bottom: 20px;

`

export const Gif = styled.img`
    min-width: 20%;
    border-radius: 15px;
    max-width: 300px;
    align-items: center;
    justify-content: center;
`