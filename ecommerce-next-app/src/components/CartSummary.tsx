"use client";
import {
  Card,
  CardBody,
  CardFooter,
  CardHeader,
  Divider,
  Link,
  Image,
} from "@nextui-org/react";

const CartSummary = () => {
  return (
    <Card className="">
      <CardHeader className="flex gap-3">
        <Image
          alt="nextui logo"
          height={40}
          radius="sm"
          src="/cart.png"
          width={40}
        />
        <p className="text-md">Cart Summary</p>
        <div className="flex flex-col"></div>
      </CardHeader>
      <CardBody>
        <div className="flex flex-col gap-3">
          <div className="flex justify-between w-full px-2">
            <div className="flex items-center justify-between gap-8">
              <div className="">
                <h3 className="font-semibold">1 x Lenovo LOQ</h3>
                <div className="text-sm">
                  <span className="text-gray-500">
                    Lorem ipsum dolor sit amet consectetur,...
                  </span>
                </div>
              </div>
              <div className="p-1 rounded-sm">Rp. 15.000.000,00</div>
            </div>
          </div>
          <Divider />
          <div className="flex justify-between w-full px-2">
            <div className="flex items-center justify-between gap-8">
              <div className="">
                <h3 className="font-semibold">1 x Lenovo LOQ</h3>
                <div className="text-sm">
                  <span className="text-gray-500">
                    Lorem ipsum dolor sit amet consectetur,...
                  </span>
                </div>
              </div>
              <div className="p-1 rounded-sm">Rp. 15.000.000,00</div>
            </div>
          </div>
          <Divider />
          <div className="flex justify-between w-full px-2">
            <div className="flex items-center justify-between gap-8">
              <div className="">
                <h3 className="font-semibold">1 x Lenovo LOQ</h3>
                <div className="text-sm">
                  <span className="text-gray-500">
                    Lorem ipsum dolor sit amet consectetur,...
                  </span>
                </div>
              </div>
              <div className="p-1 rounded-sm">Rp. 15.000.000,00</div>
            </div>
          </div>
          <Divider />
          <div className="flex justify-between w-full px-2">
            <div className="flex items-center justify-between gap-8">
              <div className="">
                <h3 className="font-semibold">1 x Lenovo LOQ</h3>
                <div className="text-sm">
                  <span className="text-gray-500">
                    Lorem ipsum dolor sit amet consectetur,...
                  </span>
                </div>
              </div>
              <div className="p-1 rounded-sm">Rp. 15.000.000,00</div>
            </div>
          </div>
          <Divider />
          <div className="flex justify-between w-full px-2">
            <div className="flex items-center justify-between gap-8">
              <div className="">
                <h3 className="font-semibold">1 x Lenovo LOQ</h3>
                <div className="text-sm">
                  <span className="text-gray-500">
                    Lorem ipsum dolor sit amet consectetur,...
                  </span>
                </div>
              </div>
              <div className="p-1 rounded-sm">Rp. 15.000.000,00</div>
            </div>
          </div>
          <Divider />
        </div>
      </CardBody>
      <CardFooter>
        <div className="justify-end">
          <p>Sub total : Rp. 30.000.000,00</p>
        </div>
      </CardFooter>
    </Card>
  );
};

export default CartSummary;
