"use client";
import Footer from "@/components/Footer";
import Navbar from "@/components/Navbar";
import { CartResponse } from "@/dto/responses/CartResponse";
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
      const res = await getAllProductFromCart();
      setCartResponses(res.data);
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
