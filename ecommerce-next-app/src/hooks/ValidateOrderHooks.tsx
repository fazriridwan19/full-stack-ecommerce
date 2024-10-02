import { CheckoutRequest } from "@/dto/requests/CheckoutRequest";
import { CartResponse } from "@/dto/responses/CartResponse";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const useValidateOrderHooks = (cartResponses: CartResponse[]) => {
  const router = useRouter();
  const [valid, setValid] = useState(true);
  const validate = () => {
    if (cartResponses && cartResponses.length === 0) {
      setValid(false);
    }
  };
  useEffect(() => {
    validate();
  }, [cartResponses]);

  useEffect(() => {
    router.push("/");
  }, [valid]);
};

export default useValidateOrderHooks;
