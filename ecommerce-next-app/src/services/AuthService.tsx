"use server";
import { ApiResponse } from "@/dto/responses/ApiResponse";
import { LoginRequest } from "@/dto/requests/LoginRequest";
import axios from "axios";
import { deleteCookie, getCookie, hasCookie, setCookie } from "./CookieService";

export const login = async (loginRequest: LoginRequest) => {
  const res = await axios.request<ApiResponse<string>>({
    url: "http://localhost:9100/api/v1/auth/login",
    data: loginRequest,
    method: "post",
  });

  setCookie("token", res.data.data);

  return res.data;
};

export const getToken = () => {
  return getCookie("token");
};

export const isAuthenticated = () => {
  return hasCookie();
};

export const logout = () => {
  deleteCookie();
};
