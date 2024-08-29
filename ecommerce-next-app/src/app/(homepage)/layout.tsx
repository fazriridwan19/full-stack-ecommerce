"use client";
import Footer from "@/components/Footer";
import Navbar from "@/components/Navbar";
import { CheckoutRequest } from "@/dto/requests/CheckoutRequest";
import { CartResponse } from "@/dto/responses/CartResponse";
import { isAuthenticated } from "@/services/AuthService";
import { getAllProductFromCart } from "@/services/CartService";
import { useEffect, useState } from "react";

export default function ProductLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  const [cartResponses, setCartResponses] = useState<CartResponse[]>([]);
  const fetchData = async () => {
    try {
      const isLoggedIn = await isAuthenticated();
      if (isLoggedIn) {
        const res = await getAllProductFromCart();
        const request = JSON.parse(
          window.localStorage.getItem("checkout") as string
        ) as CheckoutRequest;
        setCartResponses(
          res.data.map((val) => {
            val.isSelected = request.cartDetailIds?.includes(
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
  }, []);

  return (
    <>
      <Navbar data={cartResponses} />
      {children}
      <Footer />
    </>
  );
}
