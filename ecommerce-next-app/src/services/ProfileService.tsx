"use server";

import { ProfileResponse } from "@/dto/responses/ProfileResponse";
import { getToken } from "./AuthService";
import { ApiResponse } from "@/dto/responses/ApiResponse";
import axios, { AxiosError, AxiosHeaders } from "axios";

export const getProfile = async () => {
  try {
    const token = await getToken();
    const res = await axios.request<ApiResponse<ProfileResponse>>({
      url: "http://localhost:9100/api/v1/profile/get",
      method: "get",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    console.log(res.data);

    return res.data;
  } catch (error: any) {
    const errorData = error.response.data;
    throw new AxiosError(errorData.message);
  }
};
