"use client";

import { CartResponse } from "@/dto/responses/CartResponse";
import { isAuthenticated } from "@/services/AuthService";
import Image from "next/image";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import CartModal from "./CartModal";
import ProfileModal from "./ProfileModal";

const NavIcons = ({
  data: cartItems,
  fetchData,
}: {
  data: CartResponse[];
  fetchData: () => Promise<void>;
}) => {
  const [isProfileOpen, setIsProfileOpen] = useState(false);
  const [isCartOpen, setIsCartOpen] = useState(false);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const router = useRouter();
  const handleProfile = () => {
    if (!isLoggedIn) {
      router.push("/auth/login");
    } else {
      setIsProfileOpen((prev) => !prev);
      if (isCartOpen) {
        setIsCartOpen((prev) => !prev);
      }
    }
  };
  const handleCart = async () => {
    const cartStatus: { isDataChange: boolean } = JSON.parse(
      window.localStorage.getItem("cart") as string
    ) as { isDataChange: boolean };
    if (cartStatus && cartStatus.isDataChange) {
      await fetchData();
      cartStatus.isDataChange = false;
      window.localStorage.setItem("cart", JSON.stringify(cartStatus));
    }
    setIsCartOpen((prev) => !prev);
    if (isProfileOpen) {
      setIsProfileOpen((prev) => !prev);
    }
  };
  const authenticating = async () => {
    setIsLoggedIn(await isAuthenticated());
  };

  useEffect(() => {
    authenticating();
  }, []);

  return (
    <div className="flex items-center gap-4 xl:gap-6 relative">
      <Image
        src="/profile.png"
        alt="profile"
        width={22}
        height={22}
        className="cursor-pointer"
        onClick={handleProfile}
      />
      {isProfileOpen && <ProfileModal />}
      <Image
        src="/notification.png"
        alt="notification"
        width={22}
        height={22}
        className="cursor-pointer"
      />
      <div className="relative cursor-pointer">
        <Image
          src="/cart.png"
          alt="cart"
          width={22}
          height={22}
          onClick={handleCart}
        />
        <div className="absolute -top-4 -right-4 w-6 h-6 bg-custom rounded-full text-white text-sm flex items-center justify-center">
          {cartItems.length}
        </div>
      </div>
      {isCartOpen && (
        <CartModal data={cartItems} closeModal={() => setIsCartOpen(false)} />
      )}
    </div>
  );
};

export default NavIcons;
