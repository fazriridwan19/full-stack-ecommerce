"use client";
import Link from "next/link";
import Hamburger from "./Hamburber";
import Image from "next/image";
import SearchBar from "./SearchBar";
import NavIcons from "./NavIcons";
import { PropModel } from "@/models/PropModel";
import { CartResponse } from "@/dto/responses/CartResponse";

const Navbar = ({ data: cartItems }: PropModel<CartResponse[]>) => {
  return (
    <div className="h-20 px-4 md:px-8 lg:px-16 xl:px-32 2xl:px-64 relative">
      {/* MOBILE */}
      <div className="h-full flex items-center justify-between md:hidden">
        <Link href="/">
          <div className="text-2xl tracking-wide">U-STORE</div>
        </Link>
        <Hamburger />
      </div>

      {/* BIGGER SCREEN */}
      <div className="hidden md:flex items-center justify-between gap-8 h-full">
        {/* LEFT */}
        <div className="w-1/3 xl:w-1/2 flex items-center gap-12">
          <Link href="/" className="flex items-center gap-3">
            <Image src="/logo.png" alt="" width={24} height={24} />
            <div className="text-2xl tracking-wide">U-STORE</div>
          </Link>
          <div className="hidden xl:flex gap-4">
            <Link href="/">Homepage</Link>
            <Link href="/product/list">Shop</Link>
            <Link href="/product/list">Deals</Link>
            <Link href="/">Categories</Link>
          </div>
        </div>
        {/* RIGHT */}
        <div className="w-2/3 xl:w-1/2 flex items-center justify-between gap-8">
          <SearchBar />
          <NavIcons data={cartItems} />
        </div>
      </div>
    </div>
  );
};

export default Navbar;
