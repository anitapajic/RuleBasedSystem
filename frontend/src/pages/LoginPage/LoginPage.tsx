/* eslint-disable @typescript-eslint/no-explicit-any */
import React, { useState } from "react";
import {
  Anchor,
  Button,
  Container,
  Form,
  GhostButton,
  Input,
  LeftOverlayPanel,
  Overlay,
  OverlayContainer,
  Paragraph,
  RightOverlayPanel,
  SignInContainer,
  SignUpContainer,
  Title,
  Wrapper,
} from "./LoginPage.styled";
import { NewUser } from "../../models/User";
import UserService from "../../services/UserService/UserService";
import useUser from "../../utils/UserContext/useUser";
import { useNavigate } from "react-router";
import { validateEmail, validatePassword } from "../../utils/functions/validations";
import { getNext } from "../../utils/functions/getNextPage";
import { CustomInput } from "../../components/shared/styled/SharedStyles.styled";
import Role from "../../models/enums/Role";


export interface Props {
  signinIn?: boolean;
}

const LoginPage = () => {
  const [signInPanel, setSignInPanel] = useState(true);

  const [newUser, setNewUser] = useState<NewUser>({
    name: "",
    surname:"",
    email: "",
    password: "",
    confPassword: "",
    role: Role.ROLE_PATIENT,
    weight: ""
  });

  const [isValidUsername, setIsValidUsername] = useState(true);
  const [isValidPassword, setIsValidPassword] = useState(true);
  const [isValidConfPassword, setIsValidConfPassword] = useState(true);

  const [loginUser, setLoginUser] = useState({
    email: "",
    password: "",
  });

  const { setUser } = useUser();
  const navigate = useNavigate();

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setNewUser((prevState) => ({
      ...prevState,
      [name]: value,
    }));

    if (name === 'email') {
      setIsValidUsername(validateEmail(value));
    } else if (name === 'password') {
      setIsValidPassword(validatePassword(value));
    } else if (name === 'confPassword') {
      setIsValidConfPassword(value === newUser.password);
    }

  };

  const handleLoginInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setLoginUser((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };


  const handleSignUp = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    const isValidUsernameValue = validateEmail(newUser.email);
    const isValidPasswordValue = validatePassword(newUser.password);
    const isValidConfPasswordValue = newUser.password !== newUser.confPassword;
    if (!isValidUsernameValue || !isValidPasswordValue || isValidConfPasswordValue) {
      setIsValidUsername(isValidUsernameValue)
      setIsValidPassword(isValidPasswordValue)
      setIsValidConfPassword(!isValidConfPasswordValue)
    } else {

      console.log(newUser);
      UserService.register(newUser)
        .then((response: any) => {
          if(response!=null){
            console.log(response.data)
          }
        })
        .catch((error: any) => {
          console.error("Error registering user:", error);
        });
    }
  };

  const handleSignIn = (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    UserService.login(loginUser)
      .then((response: any) => {
        localStorage.setItem("user", JSON.stringify(response.data));
        setUser(response.data);
        console.log(response.data);
        navigate(getNext(response.data.role))

      })
      .catch((error: any) => {
        console.error("Error logging in:", error);
      });
  };


  return (
    <div>
    <Wrapper>
      <Container>
        <SignUpContainer signinIn={signInPanel}>
          <Form>
            <Title>Create Account</Title>
            <CustomInput
              type="text"
              placeholder="Name"
              name="name"
              value={newUser.name}
              onChange={handleInputChange}
            />
            <CustomInput
              type="text"
              placeholder="SurName"
              name="surname"
              value={newUser.surname}
              onChange={handleInputChange}
            />
            <CustomInput
              type="email"
              placeholder="Email"
              name="email"
              value={newUser.email}
              onChange={handleInputChange}
              className={isValidUsername ? '' : 'invalidInput'}
            />
            {isValidUsername ? null : (
              <small className="error-text">Email is not valid!</small>
            )}
            <CustomInput
              type="number"
              placeholder="Weight"
              name="weight"
              value={newUser.weight}
              onChange={handleInputChange}
            />
            <CustomInput
              type="password"
              placeholder="Password"
              name="password"
              value={newUser.password}
              onChange={handleInputChange}
              className={isValidPassword ? '' : 'invalidInput'}

            />
            {isValidPassword ? null : (
              <small className="error-text">Password is not strong enough!</small>
            )}
            <CustomInput
              type="password"
              placeholder="Confirm password"
              name="confPassword"
              value={newUser.confPassword}
              onChange={handleInputChange}
              className={isValidConfPassword ? '' : 'invalidInput'}

            />
            {isValidConfPassword ? null : (
              <small className="error-text">Passwords do not match!</small>
            )}

            <Button onClick={handleSignUp}>Sign Up</Button>
          </Form>
        </SignUpContainer>

        <SignInContainer signinIn={signInPanel}>
          <Form>
            <Title>Sign in</Title>
            <Input
              type="email"
              placeholder="Email"
              name="email"
              value={loginUser.email}
              onChange={handleLoginInputChange}
            />
            <Input
              type="password"
              placeholder="Password"
              name="password"
              value={loginUser.password}
              onChange={handleLoginInputChange}
            />
            <Anchor href="#">Forgot your password?</Anchor>
            <Button onClick={handleSignIn}>Sign In</Button>
          </Form>
        </SignInContainer>

        <OverlayContainer signinIn={signInPanel}>
          <Overlay signinIn={signInPanel}>
            <LeftOverlayPanel signinIn={signInPanel}>
              <Title>Welcome Back!</Title>
              <Paragraph>
                To keep connected with us please login with your personal info
              </Paragraph>
              <GhostButton onClick={() => setSignInPanel(true)}>
                Sign In
              </GhostButton>
            </LeftOverlayPanel>

            <RightOverlayPanel signinIn={signInPanel}>
              <Title>Hello, Friend!</Title>
              <Paragraph>
                Enter Your personal details and start journey with us
              </Paragraph>
              <GhostButton onClick={() => setSignInPanel(false)}>
                Sign Up
              </GhostButton>
            </RightOverlayPanel>
          </Overlay>
        </OverlayContainer>
      </Container>
    </Wrapper>
    </div>

  );
};

export default LoginPage;
