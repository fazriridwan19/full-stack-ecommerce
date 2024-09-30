"use client";
import CartTable from "@/components/CartTable";
import { CartUpdateRequest } from "@/dto/requests/CartUpdateRequest";
import { ApiResponse } from "@/dto/responses/ApiResponse";
import { CartResponse } from "@/dto/responses/CartResponse";
import useCartResponseHooks from "@/hooks/CartResponseHooks";
import { isAuthenticated } from "@/services/AuthService";
import { removeItemFromCart, updateCart } from "@/services/CartService";
import { Button, Card, CardBody } from "@nextui-org/react";
import { AxiosError } from "axios";
import Link from "next/link";
import Swal from "sweetalert2";

const CartPage = () => {
  const [cartResponses, setCartResponses, getCartResponses] =
    useCartResponseHooks();
  let idrFormatter = new Intl.NumberFormat("id-ID", {
    style: "currency",
    currency: "IDR",
  });
  const processData = async (
    operation: "update" | "remove",
    request: CartUpdateRequest
  ) => {
    try {
      const isLoggedIn = await isAuthenticated();
      if (isLoggedIn && request) {
        let res: ApiResponse<string> | null = null;
        if (operation === "update") {
          res = await updateCart(request);
        }
        if (operation === "remove" && request.cartDetailId) {
          res = await removeItemFromCart(request.cartDetailId);
        }
        if (res && res.status === 200) {
          await getCartResponses();
          window.localStorage.setItem(
            "cart",
            JSON.stringify({
              isDataChange: true,
            })
          );
          if (operation === "remove") {
            Swal.fire({
              position: "center",
              icon: "success",
              title: `Success`,
              text: `Product successfully remove from your cart`,
              showConfirmButton: true,
            }).then(() => window.location.reload());
          }
        }
      }
    } catch (error) {
      console.log(AxiosError.from(error).message);
    }
  };

  return (
    <div className="mt-4 px-4 md:px-8 lg:px-16 xl:px-32 2xl:px-64 relative flex flex-col gap-5">
      <CartTable
        items={cartResponses}
        setItems={(cartResponses: CartResponse[]) =>
          setCartResponses(cartResponses)
        }
        processData={(
          operation: "update" | "remove",
          request: CartUpdateRequest
        ) => processData(operation, request)}
      />
      <Card>
        <CardBody>
          <div className="flex justify-between gap-3 items-center">
            <span>
              Total Harga:{" "}
              {idrFormatter.format(
                cartResponses
                  .filter((cartResponse) => cartResponse.isSelected)
                  .reduce(
                    (acumulator, cartResponse) =>
                      acumulator + cartResponse.totalPrice,
                    0
                  )
              )}
            </span>
            <Button className="bg-black text-white w-1/6">
              <Link href={"/order"}>Checkout</Link>
            </Button>
          </div>
        </CardBody>
      </Card>
    </div>
  );
};

export default CartPage;
