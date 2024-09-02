"use server";
import { ApiResponse } from "@/dto/responses/ApiResponse";
import { CartRequest } from "@/dto/requests/CartRequest";
import axios, { AxiosError, AxiosHeaders } from "axios";
import { CartResponse } from "@/dto/responses/CartResponse";
import { getToken } from "./AuthService";

export const addProduct = async (request: CartRequest) => {
  const token = await getToken();

  const res = await axios.request<ApiResponse<CartResponse>>({
    url: "http://localhost:9100/api/v1/cart/add-product",
    data: request,
    method: "post",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  return res.data;
};

export const getAllProductFromCart = async () => {
  const token = await getToken();
  try {
    const res = await axios.request<ApiResponse<CartResponse[]>>({
      url: "http://localhost:9100/api/v1/cart/items",
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
