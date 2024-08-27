"use server";
import { ApiResponse } from "@/models/ApiResponse";
import { LoginRequest } from "@/models/LoginRequest";
import axios from "axios";
import { hasCookie, setCookie } from "./CookieService";

export const login = async (loginRequest: LoginRequest) => {
  const res = await axios.request<ApiResponse<string>>({
    url: "http://localhost:9100/api/v1/auth/login",
    data: loginRequest,
    method: "post",
  });

  setCookie("token", res.data.data);

  return res.data;
};

export const authenticated = () => {
  return hasCookie();
};
