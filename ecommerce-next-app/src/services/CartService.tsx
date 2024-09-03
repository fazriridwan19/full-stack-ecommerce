"use server";
import { ApiResponse } from "@/dto/responses/ApiResponse";
import { CartRequest } from "@/dto/requests/CartRequest";
import axios, { AxiosError, AxiosHeaders } from "axios";
import { CartResponse } from "@/dto/responses/CartResponse";
import { getToken } from "./AuthService";
import { CartUpdateRequest } from "@/dto/requests/CartUpdateRequest";

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
  try {
    const token = await getToken();
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

export const updateCart = async (request: CartUpdateRequest) => {
  try {
    const token = await getToken();
    const res = await axios.request<ApiResponse<string>>({
      url: "http://localhost:9100/api/v1/cart/cart-detail/update",
      method: "put",
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

export const removeItemFromCart = async (cartDetailId: number) => {
  try {
    const token = await getToken();
    const res = await axios.request<ApiResponse<string>>({
      url: `http://localhost:9100/api/v1/cart/cart-detail/remove/${cartDetailId}`,
      method: "delete",
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
