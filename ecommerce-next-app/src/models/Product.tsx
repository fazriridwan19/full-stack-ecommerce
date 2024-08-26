import { Category } from "./Category";

export interface Product {
  id: number;
  code: string;
  name: string;
  description: string;
  price: number;
  discounted: number;
  stock: number;
  isInStock: boolean;
  category: Category;
  listMedia: [];
}
