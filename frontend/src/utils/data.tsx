import {
  FaFacebookF,
  FaInstagram,
  FaLinkedin,
  FaTwitter,
} from "react-icons/fa";
import { TestType } from "../models/enums/TestType";


export const theme = {
  colors: {
    main: "#b4e854",
    //main: "#1A8EFD",
    main2: "#f54263",
    secondColor: "#293241",
    textColor: "white",
    red: "#f54263",
    grey: "#f0f0f0",
  },
  radius: {
    buttons: "8px",
  },
  fontSizes: {
    standard: "14px",
    large: "18px",
    small: "12px",
    header: "30px",
  },
};

export const icons = [
  { href: "https://www.facebook.com", icon: <FaFacebookF /> },
  { href: "https://www.twitter.com", icon: <FaTwitter /> },
  { href: "https://www.instagram.com", icon: <FaInstagram /> },
  { href: "https://www.linkedin.com", icon: <FaLinkedin /> },
];

export const infoItems = [
  { label: "Email", value: "bacticure@gmail.com" },
  { label: "Phone", value: "+123456789" },
  { label: "Address", value: "Strumicka 6, Novi Sad" },
];
export const navbarTitle = "Bacti Cure";
  
  export const menuOptions = [
    { href: "", value: "Home", role: "guest" },
    { href: "/", value: "Home", role: "logged" },
    { href: "/patient-home-page", value: "Perscriptions", role: "patient" },
    { href: "/doctor-home-page", value: "New Anamnesis", role: "doctor" },
    { href: "/doctor-patients", value: "Patients", role: "doctor" },
    { href: "/doctor-diseases", value: "Diseases", role: "doctor" },
    { href: "/login", value: "Sign In/Sign up", role: "guest" },
    { href: "/login", value: "Log Out", role: "logged" },
  ];

  export const testTypeMappings: { [key in TestType]: string } = {
    RAPID_STREP_TEST: "Rapid Strep Test",
    CHEST_X_RAY: "Chest X Ray",
    SPUTUM_TEST: "Sputum Test",
    STOOL_CULTURE: "Stool Culture",
    HEAD_MRI: "Head MRI"
  };
