"use client";
import { CartResponse } from "@/dto/responses/CartResponse";
import { isAuthenticated } from "@/services/AuthService";
import { getAllProductFromCart } from "@/services/CartService";
import { useState, useEffect, Dispatch, SetStateAction } from "react";
import useCheckoutStorageHooks from "./CheckoutStorageHooks";

const useCartResponseHooks = (): [
  CartResponse[],
  setCartResponses: Dispatch<SetStateAction<CartResponse[]>>,
  getCartResponses: () => Promise<void>
] => {
  const [cartResponses, setCartResponses] = useState<CartResponse[]>([]);
  const [checkoutRequest] = useCheckoutStorageHooks();

  const fetchData = async () => {
    try {
      const isLoggedIn = await isAuthenticated();
      if (isLoggedIn) {
        const res = await getAllProductFromCart();
        setCartResponses(
          res.data.map((val) => {
            val.isSelected = checkoutRequest.cartDetailIds?.includes(
              val.cartDetailId
            ) as boolean;
            return val;
          })
        );
      }
    } catch (error) {
      console.log(error);
    }
  };
  useEffect(() => {
    fetchData();
  }, [checkoutRequest]);

  return [cartResponses, setCartResponses, async () => await fetchData()];
};

export default useCartResponseHooks;
