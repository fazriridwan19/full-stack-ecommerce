"use client";

import { CheckoutRequest } from "@/dto/requests/CheckoutRequest";
import { CartResponse } from "@/dto/responses/CartResponse";
import { PropModel } from "@/models/PropModel";
import { Checkbox, CheckboxGroup } from "@nextui-org/react";
import Image from "next/image";
import { useEffect, useState } from "react";

const CartModal = ({ data: cartItems }: PropModel<CartResponse[]>) => {
  let idrFormatter = new Intl.NumberFormat("id-ID", {
    style: "currency",
    currency: "IDR",
  });
  const [checkoutRequest, setCheckoutRequest] = useState<CheckoutRequest>({
    paymentId: null,
    cartDetailIds: [],
  });
  const handleSelect = (cartDetailId: number) => {
    cartItems = cartItems.map((val) => {
      if (val.cartDetailId === cartDetailId) {
        val.isSelected = !val.isSelected;
      }
      return val;
    });
    const temp: CheckoutRequest = {
      ...checkoutRequest,
      cartDetailIds: [
        ...cartItems
          .filter((item) => item.isSelected)
          .map((item) => item.cartDetailId),
      ],
    };
    setCheckoutRequest(temp);
    window.localStorage.setItem("checkout", JSON.stringify(temp));
  };

  return (
    <div className="w-max absolute p-4 rounded-md shadow-[0_3px_10px_rgb(0,0,0,0.2)] bg-white top-12 right-0 flex flex-col gap-6 z-20">
      {cartItems.length === 0 ? (
        <div className="">Cart is empty</div>
      ) : (
        <>
          <h1 className="text-xl">Shopping Cart</h1>
          <div className="flex flex-col gap-8 overflow-hidden hover:overflow-auto h-[13rem]">
            {cartItems.map((item, index) => {
              return (
                <div className="flex gap-4" key={index}>
                  <Checkbox
                    id={`${item.cartDetailId}`}
                    key={index}
                    isSelected={item.isSelected}
                    onValueChange={() => handleSelect(item.cartDetailId)}
                  >
                    <Image
                      src="https://images.unsplash.com/photo-1553880414-5fe13d83ddb6?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                      alt="item"
                      width={72}
                      height={96}
                      className="object-cover rounded-md"
                    />
                  </Checkbox>
                  <div className="flex flex-col justify-between w-full">
                    {/* TOP */}
                    <div className="">
                      {/* TITLE */}
                      <div className="flex items-center justify-between gap-8">
                        <h3 className="font-semibold">{item.product.name}</h3>
                        <div className="p-1 rounded-sm">
                          {idrFormatter.format(item.product.price)}
                        </div>
                      </div>
                      <div className="text-sm text-gray-500">
                        {item.product.isInStock ? "Available" : "Unavailable"}
                      </div>
                    </div>

                    {/* BOTTOM */}
                    <div className="flex justify-between text-sm">
                      <span className="text-gray-500">
                        Qty. {item.quantity}
                      </span>
                      <span className=" text-red-500">Remove</span>
                    </div>
                  </div>
                </div>
              );
            })}
          </div>
          {/* BOTTOM */}
          <div className="">
            <div className="flex items-center justify-between font-semibold">
              <span>Subtotal</span>
              <span>
                {idrFormatter.format(
                  cartItems.reduce(
                    (acumulator, item) => acumulator + item.totalPrice,
                    0
                  )
                )}
              </span>
            </div>
            <p className="text-gray-500 text-sm mt-2 mb-4">
              Total harga tersebut belum termasuk ongkir.
            </p>
            <div className="flex justify-between text-sm">
              <button className="rounded-md py-3 px-4 ring-1 ring-gray-300">
                View Cart
              </button>
              <button className="rounded-md py-3 px-4 bg-black text-white">
                Checkout
              </button>
            </div>
          </div>
        </>
      )}
    </div>
  );
};

export default CartModal;
