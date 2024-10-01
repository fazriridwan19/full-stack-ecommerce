import { PaymentResponse } from "@/dto/responses/PaymentResponse";
import { isAuthenticated } from "@/services/AuthService";
import { getPayments } from "@/services/PaymentService";
import { useEffect, useState } from "react";

const usePaymentHooks = (
  type: string | null
): [PaymentResponse[], getPaymentResponse: () => Promise<void>] => {
  const [payments, setPayments] = useState<PaymentResponse[]>([]);
  const fetchData = async () => {
    try {
      const isLoggedIn = await isAuthenticated();
      if (isLoggedIn) {
        const res = await getPayments(type as string);
        setPayments(res.data);
      }
    } catch (error) {
      console.log(error);
    }
  };
  useEffect(() => {
    fetchData();
  }, [type]);

  return [payments, fetchData];
};

export default usePaymentHooks;
