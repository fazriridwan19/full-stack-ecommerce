import { Product } from "@/models/Product";

export interface CartResponse {
  cartId: number;
  cartDetailId: number;
  quantity: number;
  totalPrice: number;
  totalDiscountedPrice: number;
  product: Product;
  isSelected: boolean;
}
