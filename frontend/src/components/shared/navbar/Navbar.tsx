/* eslint-disable @typescript-eslint/no-unused-vars */
import { useContext, useState } from "react";
import {
  NavbarStyle,
  Title,
  Menu,
  MenuItem,
  MenuLink,
  Hamburger,
} from "./Navbar.styled";
import { Link } from "react-router-dom";
import UserContext from "../../../utils/UserContext/userContext";
import Modal from "../modal/Modal";
import AddAllergenForm from "../../addAllergenForm/AddAllergenForm";
import SockJsClient from 'react-stomp';
import {toast} from "react-toastify";

export interface NavbarProps {
  title: string;
  role?: string;
  label?: string;
  isMenuOpen: boolean;
  options: { href: string; value: string }[];
  setIsMenuOpen: React.Dispatch<React.SetStateAction<boolean>>;
}

export default function Navbar({
  title,
  isMenuOpen,
  setIsMenuOpen,
  options,
}: NavbarProps) {

  const userContext = useContext(UserContext);
  const { setUser } = userContext!;
  const [isModalVisible, setIsModalVisible] = useState(false);


  const handleLogout = () => {
    localStorage.removeItem("user");
    setUser(null);
  };

  const handleAddAllergensClick = () => {
    setIsModalVisible(true);
  };

  const handleFormCancel = () => {
    setIsModalVisible(false);
  };

  const handleMessage = (topic: string, message: any) => {
    if (topic == "/emergency/all") {
      toast.error(
          <div>
            <h2>Emergency!</h2> patient with id {message.patientId}!
            <br />
            {message.message}
          </div>,
          {
            position: "top-center",
            style: {
              backgroundColor: "#b0282f",
              color: "#fcedee",
              fontSize: "large"
            },
            autoClose: 5000
          });
    }
  }

  return (
    <>
      <NavbarStyle>
        <SockJsClient url='http://localhost:8080/socket' topics={['/emergency/all']}
                      onMessage={(msg, topic) => { handleMessage(topic, msg) }}
        />
        <Title as={Link} to={"/"}>
          {title}
        </Title>
        <Hamburger onClick={() => setIsMenuOpen(!isMenuOpen)}>☰</Hamburger>
        <Menu isOpen={isMenuOpen}>
          {options.map((link, index) => (
            <MenuItem key={index}>
              <MenuLink
                as={Link}
                onClick={() => {
                  if (link.value === "Log Out") {
                    handleLogout();
                  }
                  if (link.value === "Add allergens") {
                    handleAddAllergensClick();
                  }
                  setIsMenuOpen(false);
                }}
                to={link.href}
              >
                {link.value}
              </MenuLink>
            </MenuItem>
          ))}
        </Menu>
      </NavbarStyle>
      <Modal isVisible={isModalVisible} onClose={handleFormCancel}>
        <AddAllergenForm onSubmit={handleFormCancel}></AddAllergenForm>
      </Modal>
    </>
  );
}
