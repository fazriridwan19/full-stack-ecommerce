import {
  Badge,
  Button,
  Card,
  CardBody,
  Radio,
  RadioGroup,
} from "@nextui-org/react";
import { CheckIcon } from "./CheckIcon";
import Image from "next/image";

const PaymentMethod = () => {
  return (
    <Card className="p-3 w-full">
      <CardBody>
        <div className="flex flex-col gap-3">
          <div className="flex gap-10 justify-start items-center p-3">
            <span className="font-semibold">Metode pembayaran</span>
            <div className="flex flex-wrap w-[70%] gap-2">
              <div className="border p-2 text-sm cursor-pointer">COD</div>
              <div className="border p-2 text-sm cursor-pointer">E-Wallet</div>
              <Badge
                content={<CheckIcon />}
                color="danger"
                placement="bottom-right"
              >
                <div className="border border-red-600 p-2 text-sm cursor-pointer">
                  Bank Transfer
                </div>
              </Badge>
            </div>
          </div>
          {/* Detail payment method */}
          <div className="flex gap-7 justify-start p-3">
            <span className="font-semibold w-[15%]">Pilih Bank</span>
            <RadioGroup defaultValue={"tes"}>
              <div className="flex flex-col gap-5">
                <Radio value="tes" color="danger">
                  <div className="flex gap-4 items-center ms-5">
                    <Image
                      className="border"
                      src={"/bca.jpg"}
                      alt="bca"
                      width={50}
                      height={50}
                    />
                    <span className="font-normal">Bank BCA</span>
                  </div>
                </Radio>
                <Radio value="tes2" color="danger">
                  <div className="flex gap-4 items-center ms-5">
                    <Image
                      className="border"
                      src={"/bni.jpg"}
                      alt="bca"
                      width={50}
                      height={50}
                    />
                    <span className="font-normal">Bank BNI</span>
                  </div>
                </Radio>
                <Radio value="tes3" color="danger">
                  <div className="flex gap-4 items-center ms-5">
                    <Image
                      className="border"
                      src={"/bri.png"}
                      alt="bca"
                      width={50}
                      height={50}
                    />
                    <span className="font-normal">Bank BRI</span>
                  </div>
                </Radio>
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
