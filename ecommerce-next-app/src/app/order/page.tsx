"use client";
import AddressOrder from "@/components/AddressOrder";
import OrderedProducts from "@/components/OrderedProducts";
import PaymentMethod from "@/components/PaymentMethod";
import { CheckoutRequest } from "@/dto/requests/CheckoutRequest";
import { ProfileResponse } from "@/dto/responses/ProfileResponse";
import useCartResponseHooks from "@/hooks/CartResponseHooks";
import useProfileHooks from "@/hooks/ProfileHooks";
import useValidateOrderHooks from "@/hooks/ValidateOrderHooks";
import { checkout } from "@/services/OrderService";
import { AxiosError } from "axios";
import Image from "next/image";
import Link from "next/link";
import { useRouter } from "next/navigation";
import Swal from "sweetalert2";

const OrderPage = () => {
  const router = useRouter();
  const [profile] = useProfileHooks();
  const [cartResponses] = useCartResponseHooks();
  const checkoutItems = async (request: CheckoutRequest) => {
    try {
      const res = await checkout(request);
      window.localStorage.removeItem(`checkout-${profile?.id}`);
      if (res && res.status === 201) {
        console.log("success checkout lanjut bayar");
        Swal.fire({
          position: "center",
          icon: "success",
          title: `Success`,
          text: `Anda telah berhasil melakukan order`,
          showConfirmButton: true,
        }).then(() => router.push("/"));
      }
    } catch (error) {
      console.log(AxiosError.from(error));

      Swal.fire({
        position: "center",
        icon: "error",
        title: `Failed`,
        text: AxiosError.from(error).message.split(":")[1],
        showConfirmButton: true,
      });
    }
  };
  return (
    <div className="mt-4 -mb-10 px-4 md:px-32 lg:px-32 xl:px-32 2xl:px-96 relative flex flex-col justify-between gap-3">
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
      <AddressOrder profile={profile} />
      <OrderedProducts cartResponses={cartResponses} />
      <PaymentMethod
        cartResponses={cartResponses}
        checkoutItems={(requst: CheckoutRequest) => checkoutItems(requst)}
        profile={profile as ProfileResponse}
      />
    </div>
  );
};

export default OrderPage;
