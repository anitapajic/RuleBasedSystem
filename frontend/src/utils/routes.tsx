import { Routes, Route} from "react-router";

import HomePage from "../pages/HomePage/HomePage";
import LoginPage from "../pages/LoginPage/LoginPage";
import DoctorHomePage from "../pages/DoctorHomePage/DoctorHomePage";
import AllPatientsPage from "../pages/AllPatientsPage/AllPatientsPage";
import DiseasesPage from "../pages/DiseasesPage/DiseasesPage";
import ThreapiesPage from "../pages/TherapiesPage/TherapiesPage";
import ReportPage from "../pages/ReportPage/ReportPage";
import MonitoringPage from "../pages/MonitoringPage/MonitoringPage.tsx";



export default function MyRoutes() {
  return (
    <Routes>
      <Route path="" element={<HomePage />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/doctor-home-page" element={<DoctorHomePage />} />
      <Route path="/doctor-patients" element={<AllPatientsPage />} />
      <Route path="/doctor-diseases" element={<DiseasesPage />} />
      <Route path="/patient-home-page" element={<ThreapiesPage />} />
      <Route path="/doctor-reports" element={<ReportPage />} />
      <Route path="/monitoring" element={<MonitoringPage />} />




    </Routes>
  );
}
