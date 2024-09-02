"use client";
import { useState } from "react";

const Add = ({
  stockNumber,
  defaultQuantity = 1,
}: {
  stockNumber: number;
  defaultQuantity?: number;
}) => {
  const [quantity, setQuantity] = useState(defaultQuantity);

  // // TEMPORARY
  // const stock = 4;

  const handleQuantity = (type: "i" | "d") => {
    if (type === "d" && quantity > 1) {
      setQuantity((prev) => prev - 1);
    }
    if (type === "i" && quantity < stockNumber) {
      setQuantity((prev) => prev + 1);
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
