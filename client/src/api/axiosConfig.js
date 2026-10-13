import axios from "axios";

// Base URL of the Spring Boot API. Set REACT_APP_API_URL in client/.env for other environments.
const api = axios.create({
  baseURL: process.env.REACT_APP_API_URL || "http://localhost:8080",
});

export default api;
