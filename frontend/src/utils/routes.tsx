import { Routes, Route} from "react-router";

import HomePage from "../pages/HomePage/HomePage";
import LoginPage from "../pages/LoginPage/LoginPage";
import PatientHomePage from "../pages/PatientHomePage/PatientHomePage";
import DoctorHomePage from "../pages/DoctorHomePage/DoctorHomePage";



export default function MyRoutes() {
  return (
    <Routes>
      <Route path="" element={<HomePage />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/patient-home-page" element={<PatientHomePage />} />
      <Route path="/doctor-home-page" element={<DoctorHomePage />} />
    </Routes>
  );
}
