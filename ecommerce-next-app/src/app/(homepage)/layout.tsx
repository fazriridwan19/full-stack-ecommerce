"use client";
import Footer from "@/components/Footer";
import Navbar from "@/components/Navbar";
import useCartResponseHooks from "@/hooks/CartResponseHooks";

export default function ProductLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  const [cartResponses, setCartResponses, getCartResponses] =
    useCartResponseHooks();

  return (
    <>
      <Navbar data={cartResponses} fetchData={() => getCartResponses()} />
      {children}
      <Footer />
    </>
  );
}
