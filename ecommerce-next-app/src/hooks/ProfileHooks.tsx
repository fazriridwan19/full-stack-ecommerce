"use client";
import { ProfileResponse } from "@/dto/responses/ProfileResponse";
import { isAuthenticated } from "@/services/AuthService";
import { getProfile } from "@/services/ProfileService";
import { useState, useEffect } from "react";

const useProfileHooks = (): [ProfileResponse | null, boolean] => {
  const [profile, setProfile] = useState<ProfileResponse | null>(null);
  const [error, setError] = useState(false);

  const fetchProfile = async () => {
    try {
      const isLoggedIn = await isAuthenticated();
      if (isLoggedIn) {
        const res = await getProfile();
        setProfile(res.data);
      }
    } catch (err) {
      setError(true);
    }
  };

  useEffect(() => {
    fetchProfile();
  }, []);

  return [profile, error];
};

export default useProfileHooks;
