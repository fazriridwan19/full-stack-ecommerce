"use client";
import AddressOrder from "@/components/AddressOrder";
import OrderedProducts from "@/components/OrderedProducts";
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
    <div className="mt-4 px-4 md:px-32 lg:px-32 xl:px-32 2xl:px-96 relative flex flex-col justify-between gap-3">
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
    </div>

    // <div className="w-[50%] flex flex-col gap-8">
    //     {/* Left */}
    //     <div className="flex flex-col gap-5">
    //       {/* Billing details */}
    //       <h2 className="font-bold">Detail Pembayaran</h2>
    //       <Input
    //         required
    //         type="text"
    //         label="Nama"
    //         variant="bordered"
    //         labelPlacement="outside"
    //         placeholder="Fulan"
    //       />
    //       <Input
    //         required
    //         type="email"
    //         label="Alamat email"
    //         variant="bordered"
    //         labelPlacement="outside"
    //         placeholder="fulan@gmail.com"
    //       />
    //       <Divider />

    //       <Textarea
    //         variant="bordered"
    //         label="Alamat"
    //         labelPlacement="outside"
    //         placeholder="Jl. Durian no 12, RT 01 RW 02, Desa Keramat, Kec. Buah"
    //         classNames={{
    //           input: "resize-y min-h-[40px]",
    //         }}
    //       />
    //       <Input
    //         required
    //         type="text"
    //         label="Kota/Kabupaten"
    //         variant="bordered"
    //         labelPlacement="outside"
    //         placeholder="Jakarta Pusat"
    //       />
    //       <div className="flex justify-between gap-3">
    //         <Input
    //           required
    //           type="text"
    //           label="Provinsi"
    //           variant="bordered"
    //           labelPlacement="outside"
    //           placeholder="DKI Jakarta"
    //           className="w-1/2"
    //         />
    //         <Input
    //           required
    //           type="text"
    //           label="Kode Pos"
    //           variant="bordered"
    //           labelPlacement="outside"
    //           placeholder="12345"
    //           className="w-1/2"
    //         />
    //       </div>
    //     </div>
    //     <div className="flex flex-col gap-5">
    //       {/* Payment method */}
    //       <h2 className="font-bold">Metode pembayaran</h2>
    //       <Select
    //         variant="bordered"
    //         label="Tipe pembayaran"
    //         labelPlacement="outside"
    //         placeholder="Pilih tipe pembayaran"
    //       >
    //         {typePayments.map((type, index) => {
    //           return <SelectItem key={index}>{type.name}</SelectItem>;
    //         })}
    //       </Select>
    //       <Select
    //         variant="bordered"
    //         label="Metode pembayaran"
    //         labelPlacement="outside"
    //         placeholder="Pilih metode pembayaran"
    //       >
    //         {payments.map((payment, index) => (
    //           <SelectItem key={index}>{payment.name}</SelectItem>
    //         ))}
    //       </Select>
    //     </div>
    //   </div>
    //   <div className="mt-16">
    //     {/* Right */}
    //     <CartSummary />
    //   </div>
  );
};

export default OrderPage;
