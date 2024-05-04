import { useContext } from "react";
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

export interface NavbarProps {
  title: string;
  role?: string;
  label?: string;
  isMenuOpen: boolean;
  options: { href: string; value: string }[];
  footerRef?: React.RefObject<HTMLDivElement>;
  setIsMenuOpen: React.Dispatch<React.SetStateAction<boolean>>;
}

export default function Navbar({
  title,
  isMenuOpen,
  setIsMenuOpen,
  options,
  footerRef,
}: NavbarProps) {

  const userContext = useContext(UserContext);
  const { setUser } = userContext!;


  const handleLogout = () => {
    localStorage.removeItem("user");
    setUser(null);
  };



  const handleContactClick = () => {
    footerRef?.current?.scrollIntoView({ behavior: "smooth" });
  };



  return (
    <>
      <NavbarStyle>
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
                  if (link.value === "Contact") {
                    handleContactClick();
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
    </>
  );
}
