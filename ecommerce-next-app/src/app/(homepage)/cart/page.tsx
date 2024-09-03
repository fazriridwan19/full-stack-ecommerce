"use client";
import CartTable from "@/components/CartTable";
import { CartUpdateRequest } from "@/dto/requests/CartUpdateRequest";
import { CheckoutRequest } from "@/dto/requests/CheckoutRequest";
import { ApiResponse } from "@/dto/responses/ApiResponse";
import { CartResponse } from "@/dto/responses/CartResponse";
import { isAuthenticated } from "@/services/AuthService";
import {
  getAllProductFromCart,
  removeItemFromCart,
  updateCart,
} from "@/services/CartService";
import { AxiosError } from "axios";
import { useEffect, useState } from "react";
import Swal from "sweetalert2";

const CartPage = () => {
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
      if (isLoggedIn) {
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
  const processData = async (
    operation: "update" | "remove",
    request: CartUpdateRequest
  ) => {
    try {
      const isLoggedIn = await isAuthenticated();
      if (isLoggedIn && request) {
        let res: ApiResponse<string> | null = null;
        if (operation === "update") {
          res = await updateCart(request);
        }
        if (operation === "remove" && request.cartDetailId) {
          res = await removeItemFromCart(request.cartDetailId);
        }
        if (res && res.status === 200) {
          await fetchData();
          window.localStorage.setItem(
            "cart",
            JSON.stringify({
              isDataChange: true,
            })
          );
          if (operation === "remove") {
            Swal.fire({
              position: "center",
              icon: "success",
              title: `Success`,
              text: `Product successfully remove from your cart`,
              showConfirmButton: true,
            }).then(() => window.location.reload());
          }
        }
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
      <CartTable
        items={items}
        processData={(
          operation: "update" | "remove",
          request: CartUpdateRequest
        ) => processData(operation, request)}
      />
      {/* TODO: Component checkout */}
    </div>
  );
};

export default CartPage;
