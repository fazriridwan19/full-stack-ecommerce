"use server";
import { ApiResponse } from "@/models/ApiResponse";
import { Product } from "@/models/Product";
import axios from "axios";

const getProducts = async () => {
  const { data } = await axios.get<ApiResponse<Product[]>>(
    "http://localhost:9100/api/v1/products"
  );
  return data;
};

export default getProducts;
