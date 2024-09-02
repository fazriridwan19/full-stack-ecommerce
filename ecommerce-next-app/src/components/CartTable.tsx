"use client";
import { CartResponse } from "@/dto/responses/CartResponse";
import {
  Button,
  Checkbox,
  Table,
  TableBody,
  TableCell,
  TableColumn,
  TableHeader,
  TableRow,
} from "@nextui-org/react";
import Image from "next/image";
import Add from "./Add";
import { CheckoutRequest } from "@/dto/requests/CheckoutRequest";
import { useEffect, useState } from "react";

const CartTable = ({ items }: { items: CartResponse[] }) => {
  const [checkoutRequest, setCheckoutRequest] = useState<CheckoutRequest>({
    paymentId: null,
    cartDetailIds: [],
  });
  const [selectAll, setSelectAll] = useState(
    checkoutRequest.cartDetailIds?.length === items.length
  );
  let idrFormatter = new Intl.NumberFormat("id-ID", {
    style: "currency",
    currency: "IDR",
  });

  const handleSelect = (cartDetailId: number) => {
    items = items.map((val) => {
      if (val.cartDetailId === cartDetailId) {
        val.isSelected = !val.isSelected;
      }
      return val;
    });
    let temp: CheckoutRequest = {
      ...checkoutRequest,
      cartDetailIds: [
        ...items
          .filter((item) => item.isSelected)
          .map((item) => item.cartDetailId),
      ],
    };
    setSelectAll(false);
    setCheckoutRequest(temp);
    window.localStorage.setItem("checkout", JSON.stringify(temp));
  };

  const handleSelectAll = () => {
    let isSelectAll = !selectAll;
    items = items.map((val) => {
      val.isSelected = isSelectAll;
      return val;
    });
    const temp: CheckoutRequest = {
      ...checkoutRequest,
      cartDetailIds: [
        ...items
          .filter((item) => item.isSelected)
          .map((item) => item.cartDetailId),
      ],
    };
    setSelectAll(isSelectAll);
    setCheckoutRequest(temp);
    window.localStorage.setItem("checkout", JSON.stringify(temp));
  };

  const getDataCheckout = () => {
    setCheckoutRequest(
      JSON.parse(
        window.localStorage.getItem("checkout") as string
      ) as CheckoutRequest
    );
  };

  useEffect(() => {
    getDataCheckout();
  }, []);

  return (
    <div className="flex flex-col gap-3">
      <Table
        color="default"
        aria-label="Example static collection table"
        id="1"
        key={1}
      >
        <TableHeader>
          <TableColumn className="w-1/12">
            <Checkbox
              className="w-min"
              isSelected={
                items.filter((item) => item.isSelected).length === items.length
              }
              onValueChange={() => handleSelectAll()}
            ></Checkbox>
          </TableColumn>
          <TableColumn className="w-2/5">Product</TableColumn>
          <TableColumn className="w-1/5">{""}</TableColumn>
          <TableColumn className="w-1/5">Harga Product</TableColumn>
          <TableColumn className="w-1/2">Total Harga</TableColumn>
          <TableColumn className="w-1/5">Kuantitas</TableColumn>
          <TableColumn className="w-1/5">Aksi</TableColumn>
        </TableHeader>
        <TableBody>
          {items.map((item, index) => {
            return (
              <TableRow key={index}>
                <TableCell className="w-1/12 p-2">
                  <Checkbox
                    className="w-min"
                    id={`${item.cartDetailId}`}
                    key={index}
                    isSelected={item.isSelected}
                    onValueChange={() => handleSelect(item.cartDetailId)}
                  ></Checkbox>
                </TableCell>
                <TableCell>
                  <div className="flex justify-start gap-x-4 w-full">
                    <Image
                      src="https://images.unsplash.com/photo-1553880414-5fe13d83ddb6?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                      alt="item"
                      width={72}
                      height={96}
                      className="object-cover rounded-md"
                    />
                    <div>
                      <div className="flex flex-col items-start justify-between gap-3">
                        <h3 className="font-semibold">{item.product.name}</h3>
                        <span className="font-normal">
                          {item.product.description}
                        </span>
                      </div>
                    </div>
                  </div>
                </TableCell>
                <TableCell className="w-1/5">
                  Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ex,
                  ipsum.
                </TableCell>
                <TableCell className="w-1/5">
                  {idrFormatter.format(item.product.price)}
                </TableCell>
                <TableCell className="w-1/2">
                  {idrFormatter.format(item.totalPrice)}
                </TableCell>
                <TableCell className="w-1/5">
                  <Add
                    stockNumber={item.product.stock}
                    defaultQuantity={item.quantity}
                  />
                </TableCell>
                <TableCell className="w-1/5 cursor-pointer">
                  <Button className="bg-red-200">Hapus</Button>
                </TableCell>
              </TableRow>
            );
          })}
        </TableBody>
      </Table>
    </div>
  );
};

export default CartTable;
