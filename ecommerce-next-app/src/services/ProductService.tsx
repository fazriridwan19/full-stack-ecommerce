"use server";
import { ApiResponse } from "@/dto/responses/ApiResponse";
import { Product } from "@/models/Product";
import axios from "axios";

export const getProducts = async () => {
  const { data } = await axios.get<ApiResponse<Product[]>>(
    "http://localhost:9100/api/v1/products"
  );
  return data;
};

export const getProductById = async (id: string) => {
  const { data } = await axios.get<ApiResponse<Product>>(
    `http://localhost:9100/api/v1/products/${id}`
  );
  return data;
};
