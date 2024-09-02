"use client";
import CartTable from "@/components/CartTable";
import { CheckoutRequest } from "@/dto/requests/CheckoutRequest";
import { CartResponse } from "@/dto/responses/CartResponse";
import { isAuthenticated } from "@/services/AuthService";
import { getAllProductFromCart } from "@/services/CartService";
import { AxiosError } from "axios";
import { useEffect, useState } from "react";

const CartPage = () => {
  /**
   * TODO
   * 1. Add api update
   * 2. Add api delete
   */
  const [items, setItems] = useState<CartResponse[]>([]);
  const [request, setRequest] = useState<CheckoutRequest>({
    paymentId: null,
    cartDetailIds: [],
  });
  const fetchStorageData = () => {
    setRequest(
      JSON.parse(
        window.localStorage.getItem("checkout") as string
      ) as CheckoutRequest
    );
  };
  const fetchData = async () => {
    try {
      const isLoggedIn = await isAuthenticated();
      if (isLoggedIn && request) {
        const res = await getAllProductFromCart();
        setItems(
          res.data.map((item) => {
            item.isSelected = request.cartDetailIds?.includes(
              item.cartDetailId
            ) as boolean;
            return item;
          })
        );
      }
    } catch (error) {
      console.log(AxiosError.from(error).message);
    }
  };
  useEffect(() => {
    fetchData();
  }, [request]);

  useEffect(() => {
    fetchStorageData();
  }, []);

  return (
    <div className="mt-4 px-4 md:px-8 lg:px-16 xl:px-32 2xl:px-64 relative">
      <CartTable items={items} />
    </div>
  );
};

export default CartPage;
