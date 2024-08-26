"use server";

import { ApiResponse } from "@/models/ApiResponse";
import { Category } from "@/models/Category";
import axios from "axios";

export const getCategories = async () => {
  const { data } = await axios.get<ApiResponse<Category[]>>(
    "http://localhost:9100/api/v1/categories"
  );
  return data;
};
