"use server";

import { ApiResponse } from "@/dto/responses/ApiResponse";
import { PaymentResponse } from "@/dto/responses/PaymentResponse";
import axios, { AxiosError } from "axios";
import { getToken } from "./AuthService";

export const getPayments = async (type = "") => {
  try {
    const token = await getToken();
    const res = await axios.request<ApiResponse<PaymentResponse[]>>({
      url: `http://localhost:9100/api/v1/payments?type=${type}`,
      method: "get",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });

    return res.data;
  } catch (error: any) {
    const errorData = error.response.data;
    throw new AxiosError(errorData.message);
  }
};
