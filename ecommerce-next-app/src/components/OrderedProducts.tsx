import {
  Card,
  CardBody,
  CardFooter,
  Input,
  Table,
  TableBody,
  TableCell,
  TableColumn,
  TableHeader,
  TableRow,
  useDisclosure,
  User,
} from "@nextui-org/react";
import ShipmentModal from "./ShipmentModal";
import { CartResponse } from "@/dto/responses/CartResponse";
import React from "react";

const OrderedProducts = ({
  cartResponses,
}: {
  cartResponses: CartResponse[];
}) => {
  const { isOpen, onOpen, onClose } = useDisclosure(); // Modal
  let idrFormatter = new Intl.NumberFormat("id-ID", {
    style: "currency",
    currency: "IDR",
  });
  return (
    <>
      <Card className="p-3 w-full">
        <CardBody>
          <Table
            removeWrapper
            color="default"
            aria-label="Example static collection table"
          >
            <TableHeader>
              <TableColumn className="w-[30%]">Produk Dipesan</TableColumn>
              <TableColumn className="w-[20%]">{""}</TableColumn>
              <TableColumn className="w-[20%]">Harga Satuan</TableColumn>
              <TableColumn className="w-[10%]">Kuantitas</TableColumn>
              <TableColumn className="w-[20%]">Subtotal Produk</TableColumn>
            </TableHeader>
            <TableBody>
              {cartResponses
                .filter((item) => item.isSelected)
                .map((item, index) => {
                  return (
                    <TableRow key={index}>
                      <TableCell className="w-[30%]">
                        <div className="flex justify-start gap-x-4 w-full">
                          <User
                            name={item.product.name}
                            avatarProps={{
                              src: "https://images.unsplash.com/photo-1553880414-5fe13d83ddb6?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                            }}
                          />
                        </div>
                      </TableCell>
                      <TableCell className="font-light">
                        Variasi: Hitam - SSD 512 GB
                      </TableCell>
                      <TableCell>
                        {idrFormatter.format(item.product.price)}
                      </TableCell>
                      <TableCell>{item.quantity}</TableCell>
                      <TableCell>
                        {idrFormatter.format(item.totalPrice)}
                      </TableCell>
                    </TableRow>
                  );
                })}
            </TableBody>
          </Table>
          <div className="flex justify-between w-full mt-3">
            <div className="px-2 w-[40%]">
              <Input
                variant="underlined"
                type="text"
                label="Pesan:"
                labelPlacement="outside-left"
                placeholder="(Optional) Tinggalkan pesan ke penjual"
              />
            </div>
            <div className="flex justify-between p-2 w-[60%]">
              <span>Opsi pengiriman:</span>
              <div className="flex flex-col gap-2">
                <span>JNE Reguler</span>
                <span className="text-sm font-light">
                  Estimasi tiba: 10 - 13 Sep
                </span>
              </div>
              <span
                onClick={() => onOpen()}
                className="text-custom cursor-pointer"
              >
                Ubah
              </span>
              <span>Rp. 45.000,00</span>
            </div>
          </div>
        </CardBody>
        <CardFooter className="flex justify-end">
          <div>
            <span className="font-light text-sm">
              Total Pesanan (2 produk) :{" "}
            </span>
            <span className="text-custom">
              {idrFormatter.format(
                cartResponses.reduce(
                  (acumulator, item) => acumulator + item.totalPrice,
                  0
                )
              )}
            </span>
          </div>
        </CardFooter>
      </Card>
      <ShipmentModal isOpen={isOpen} onClose={() => onClose()} />
    </>
  );
};

export default OrderedProducts;
