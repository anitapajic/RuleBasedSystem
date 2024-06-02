/* eslint-disable @typescript-eslint/no-explicit-any */
import axios from "axios";
import { useNavigate } from "react-router";

const customAxios = axios.create({
  baseURL: "http://localhost:8080/api",
});

customAxios.interceptors.request.use(
  (config: any) => {
    const storedData = localStorage.getItem("user");
    if (storedData) {
      const userData = JSON.parse(storedData);
      console.log('User data:', userData); // Dodajte ovu liniju za proveru
      if (userData && userData.accessToken) {
        console.log('Token:', userData.accessToken); // Dodajte ovu liniju za proveru
        config.headers["Authorization"] = `Bearer ${userData.accessToken}`;
      }
    }
    return config;
  },
  (error: any) => {
    console.log(error, "error");
    return Promise.reject(error);
  }
);

customAxios.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response.status === 401) {
      const navigate = useNavigate();
      navigate("/login");
    }
    return Promise.reject(error);
  }
);

export default customAxios;
