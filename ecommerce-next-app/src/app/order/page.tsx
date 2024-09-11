"use client";
import AddressOrder from "@/components/AddressOrder";
import OrderedProducts from "@/components/OrderedProducts";
import PaymentMethod from "@/components/PaymentMethod";
import Image from "next/image";
import Link from "next/link";
const payments = [
  { type: "Transfer Bank", name: "BRI" },
  { type: "Transfer Bank", name: "BCA" },
  { type: "Transfer Bank", name: "BNI" },
  { type: "E-Wallet", name: "Dana" },
];
const typePayments = [
  { name: "Transfer Bank" },
  { name: "E-Wallet" },
  { name: "Credit Cart" },
];
const OrderPage = () => {
  return (
    <div className="mt-4 -mb-10 px-4 md:px-32 lg:px-32 xl:px-32 2xl:px-96 relative flex flex-col justify-between gap-3">
      <div className="hidden md:flex items-center justify-between gap-8 h-full py-5">
        <div className="w-1/3 xl:w-1/2 flex items-center gap-5">
          <Link href="/" className="flex items-center gap-3">
            <Image src="/logo.png" alt="" width={24} height={24} />
            <div className="text-2xl tracking-wide">U-STORE</div>
          </Link>
          <div className="hidden xl:flex gap-4">
            <div>Checkout</div>
          </div>
        </div>
      </div>
      <AddressOrder />
      <OrderedProducts />
      <PaymentMethod />
    </div>
  );
};

export default OrderPage;
