import { Category } from "./Category";

export interface Product {
  id: number;
  code: string;
  name: string;
  description: string;
  price: number;
  discountedPrice: number;
  stock: number;
  isInStock: boolean;
  category: Category;
  listMedia: [];
}
