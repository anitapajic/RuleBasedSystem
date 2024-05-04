import styled from "styled-components";

export const StyledFooter = styled.footer`
  width: 100%;
  background-color: ${({ theme }) => theme.colors.secondColor};
  color: ${({ theme }) => theme.colors.textColor};
  text-align: center;
  padding-top: 40px;
  padding-bottom: 40px;
  padding-left: 0;
  padding-right: 0;
  margin-top: 20px;
`;

export const SocialIcons = styled.div`
  a {
    margin: 10px;
    color: ${({ theme }) => theme.colors.textColor};
    font-size: 24px;
    text-decoration: none;
    cursor: pointer;
    padding: 20 0px;
  }
`;

export const ContactInfo = styled.div`
  p {
    margin: 0;
    font-size: ${({ theme }) => theme.fontSizes.small};
    padding: 5px;
  }
`;
