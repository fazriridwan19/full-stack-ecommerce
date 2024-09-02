"use client";

import { isAuthenticated } from "@/services/AuthService";
import Image from "next/image";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import CartModal from "./CartModal";
import ProfileModal from "./ProfileModal";
import { PropModel } from "@/models/PropModel";
import { CartResponse } from "@/dto/responses/CartResponse";

const NavIcons = ({ data: cartItems }: PropModel<CartResponse[]>) => {
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
  const handleCart = () => {
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
