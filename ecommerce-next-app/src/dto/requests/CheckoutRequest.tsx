export interface CheckoutRequest {
  paymentId?: number | null;
  cartDetailIds?: number[] | null;
}
