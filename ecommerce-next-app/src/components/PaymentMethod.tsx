"use client";

import usePaymentHooks from "@/hooks/PaymentHooks";
import { Button, Card, CardBody, Radio, RadioGroup } from "@nextui-org/react";
import Image from "next/image";
import { useState } from "react";

const PaymentMethod = () => {
  const [type, setType] = useState<string | null>("transfer");
  const [payments, getPayments] = usePaymentHooks(type);
  const handlePaymentType = async (type: string) => {
    setType(type);
    await getPayments();
  };

  return (
    <Card className="p-3 w-full">
      <CardBody>
        <div className="flex flex-col gap-3">
          <div className="flex gap-10 justify-start items-center p-3">
            <span className="font-semibold">Metode pembayaran</span>
            <div className="flex flex-wrap w-[70%] gap-2">
              <div
                className={`border ${
                  type === "e_wallet" && "border-red-600"
                } p-2 text-sm cursor-pointer`}
                onClick={() => handlePaymentType("e_wallet")}
              >
                E-Wallet
              </div>
              <div
                className={`border ${
                  type === "transfer" && "border-red-600"
                } p-2 text-sm cursor-pointer`}
                onClick={() => handlePaymentType("transfer")}
              >
                Bank T
              </div>
            </div>
          </div>
          {/* Detail payment method */}
          <div className="flex gap-7 justify-start p-3">
            <span className="font-semibold w-[15%]">Pilih Bank</span>
            <RadioGroup defaultValue={"1"}>
              <div className="flex flex-col gap-5">
                {payments.map((payment, index) => {
                  return (
                    <Radio value={payment.id + ""} color="danger" key={index}>
                      <div className="flex gap-4 items-center ms-5">
                        <Image
                          className="border"
                          src={"/bca.jpg"}
                          alt="bca"
                          width={50}
                          height={50}
                        />
                        <span className="font-normal">{payment.name}</span>
                      </div>
                    </Radio>
                  );
                })}
              </div>
            </RadioGroup>
          </div>
        </div>
        <div className="flex gap-2 bg-gray-100 p-4 rounded-lg mt-3">
          <div className="w-[65%]"></div>
          <div className="flex flex-col w-[20%] gap-3">
            <span className="font-light text-sm">Subtotal Produk</span>
            <span className="font-light text-sm">Total Ongkos Kirim</span>
            <span className="font-light text-sm">Total Pembayaran</span>
          </div>
          <div className="flex flex-col w-[15%] gap-3">
            <span className="text-sm">Rp. 30.000.000,00</span>
            <span className="text-sm">Rp. 45.000,00</span>
            <span className="text-red-600">Rp. 30.045.000,00</span>
          </div>
        </div>
        <div className="flex justify-end p-4">
          <Button className="bg-custom p-2 text-white w-[35%]">
            Buat Pesanan
          </Button>
        </div>
      </CardBody>
    </Card>
  );
};

export default PaymentMethod;
