"use client";
import { CartUpdateRequest } from "@/dto/requests/CartUpdateRequest";
import { useState } from "react";

const Add = ({
  stockNumber,
  defaultQuantity = 1,
  cartDetailId,
  processData,
}: {
  stockNumber: number;
  defaultQuantity?: number;
  cartDetailId?: number;
  processData?: (
    operation: "update" | "remove",
    request: CartUpdateRequest
  ) => Promise<void>;
}) => {
  const [quantity, setQuantity] = useState(defaultQuantity);
  const handleQuantity = async (type: "i" | "d") => {
    let currentQuantity = quantity;
    if (type === "d" && quantity > 1) {
      currentQuantity -= 1;
      setQuantity(currentQuantity);
    }
    if (type === "i" && quantity < stockNumber) {
      currentQuantity += 1;
      setQuantity(currentQuantity);
    }
    if (processData) {
      await processData("update", {
        quantity: currentQuantity,
        cartDetailId: cartDetailId,
      });
    }
  };

  return (
    <div className="flex justify-between">
      <div className="flex items-center gap-4">
        <div
          className={`bg-gray-200 py-2 px-4 rounded-3xl flex items-center justify-between w-32`}
        >
          <button
            className="cursor-pointer text-xl disabled:cursor-not-allowed disabled:opacity-20"
            onClick={() => handleQuantity("d")}
            disabled={quantity === 1}
          >
            -
          </button>
          {quantity}
          <button
            className="cursor-pointer text-xl disabled:cursor-not-allowed disabled:opacity-20"
            onClick={() => handleQuantity("i")}
            disabled={quantity === stockNumber}
          >
            +
          </button>
        </div>
      </div>
    </div>
  );
};

export default Add;
