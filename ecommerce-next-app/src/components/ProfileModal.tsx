"use client";

import { logout } from "@/services/AuthService";
import Link from "next/link";

const ProfileModal = () => {
  const handleLogout = async () => {
    await logout();
    window.location.reload();
  };
  return (
    <div className="absolute p-4 rounded-md top-12 left-0 text-sm shadow-[0_3px_10px_rgb(0,0,0,0.2)] z-20 bg-white">
      <Link href="/">Profile</Link>
      <div className="mt-2 cursor-pointer" onClick={handleLogout}>
        Logout
      </div>
    </div>
  );
};

export default ProfileModal;
