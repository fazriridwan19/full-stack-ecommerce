"use server";
import { CheckoutRequest } from "@/dto/requests/CheckoutRequest";
import { ApiResponse } from "@/dto/responses/ApiResponse";
import axios, { AxiosError } from "axios";
import { getToken } from "./AuthService";

export const checkout = async (request: CheckoutRequest) => {
  try {
    const token = await getToken();
    const res = await axios.request<ApiResponse<string>>({
      url: "http://localhost:9100/api/v1/order/checkout",
      method: "post",
      data: request,
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
