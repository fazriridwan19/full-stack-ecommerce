import { CartUpdateRequest } from "@/dto/requests/CartUpdateRequest";
import {
  Card,
  CardHeader,
  CardBody,
  Button,
  Checkbox,
  Table,
  TableBody,
  TableCell,
  TableColumn,
  TableHeader,
  TableRow,
  CardFooter,
  User,
} from "@nextui-org/react";
import Add from "./Add";
import Image from "next/image";

const OrderedProducts = () => {
  return (
    <Card className="p-3 w-full">
      <CardBody>
        <Table
          removeWrapper
          color="default"
          aria-label="Example static collection table"
          id="1"
          key={1}
        >
          <TableHeader>
            <TableColumn className="w-[30%]">Produk Dipesan</TableColumn>
            <TableColumn className="w-[20%]">{""}</TableColumn>
            <TableColumn className="w-[20%]">Harga Satuan</TableColumn>
            <TableColumn className="w-[10%]">Kuantitas</TableColumn>
            <TableColumn className="w-[20%]">Subtotal Produk</TableColumn>
          </TableHeader>
          <TableBody>
            <TableRow>
              <TableCell className="w-[30%]">
                <div className="flex justify-start gap-x-4 w-full">
                  <User
                    name="Lenovo LOQ"
                    avatarProps={{
                      src: "https://images.unsplash.com/photo-1553880414-5fe13d83ddb6?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    }}
                  />
                </div>
              </TableCell>
              <TableCell>Variasi: Hitam - SSD 512 GB</TableCell>
              <TableCell>Rp. 15.000.000,00</TableCell>
              <TableCell>1</TableCell>
              <TableCell>Rp. 15.000.000,00</TableCell>
            </TableRow>
            <TableRow>
              <TableCell className="w-[30%]">
                <div className="flex justify-start gap-x-4 w-full">
                  <User
                    name="Lenovo LOQ"
                    avatarProps={{
                      src: "https://images.unsplash.com/photo-1553880414-5fe13d83ddb6?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    }}
                  />
                </div>
              </TableCell>
              <TableCell>Variasi: Hitam - SSD 512 GB</TableCell>
              <TableCell>Rp. 15.000.000,00</TableCell>
              <TableCell>1</TableCell>
              <TableCell>Rp. 15.000.000,00</TableCell>
            </TableRow>
          </TableBody>
        </Table>
      </CardBody>
      <CardFooter className="flex justify-end">
        <div>
          <span className="font-light text-sm">
            Total Pesanan (2 produk) :{" "}
          </span>
          <span>Rp. 30.000.000,00</span>
        </div>
      </CardFooter>
    </Card>
  );
};

export default OrderedProducts;
