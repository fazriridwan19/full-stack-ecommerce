"use server";
import { LoginRequest } from "@/dto/requests/LoginRequest";
import { ApiResponse } from "@/dto/responses/ApiResponse";
import axios, { AxiosError } from "axios";
import { deleteCookie, getCookie, hasCookie, setCookie } from "./CookieService";

export const login = async (loginRequest: LoginRequest) => {
  try {
    const res = await axios.request<ApiResponse<string>>({
      url: "http://localhost:9100/api/v1/auth/login",
      data: loginRequest,
      method: "post",
    });

    setCookie("token", res.data.data);

    return res.data;
  } catch (error: any) {
    const errorData = error.response.data;
    throw new AxiosError(errorData.message);
  }
};

export const getToken = () => {
  return getCookie("token");
};

export const isAuthenticated = async () => {
  return hasCookie();
};

export const logout = () => {
  deleteCookie();
};
