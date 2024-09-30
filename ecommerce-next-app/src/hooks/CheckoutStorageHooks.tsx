"use client";
import { CheckoutRequest } from "@/dto/requests/CheckoutRequest";
import { useEffect, useState } from "react";
import useProfileHooks from "./ProfileHooks";
import { ProfileResponse } from "@/dto/responses/ProfileResponse";

const useCheckoutStorageHooks = (): [CheckoutRequest, boolean] => {
  const [profile] = useProfileHooks();
  const [checkoutRequest, setCheckoutRequest] = useState<CheckoutRequest>({
    paymentId: null,
    cartDetailIds: [],
  });
  const [error, setError] = useState(false);

  const fetchStorageData = () => {
    try {
      const checkoutRequestStorage = window.localStorage.getItem(
        `checkout-${profile?.id}`
      ) as string;
      let request: CheckoutRequest = {
        paymentId: null,
        cartDetailIds: [],
      };
      if (checkoutRequestStorage) {
        request = JSON.parse(checkoutRequestStorage) as CheckoutRequest;
      } else {
        window.localStorage.setItem(
          `checkout-${profile?.id}`,
          JSON.stringify(request)
        );
      }
      setCheckoutRequest(request);
    } catch (error) {
      setError(true);
    }
  };

  useEffect(() => {
    fetchStorageData();
  }, [profile]);

  return [checkoutRequest, error];
};

export default useCheckoutStorageHooks;
