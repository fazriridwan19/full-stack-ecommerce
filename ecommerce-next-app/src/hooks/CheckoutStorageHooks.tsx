"use client";
import { CheckoutRequest } from "@/dto/requests/CheckoutRequest";
import { Dispatch, SetStateAction, useEffect, useState } from "react";
import useProfileHooks from "./ProfileHooks";

const useCheckoutStorageHooks = (): [
  CheckoutRequest,
  boolean,
  Dispatch<SetStateAction<CheckoutRequest>>
] => {
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

  return [checkoutRequest, error, setCheckoutRequest];
};

export default useCheckoutStorageHooks;
