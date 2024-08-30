"use client";
import {
  Button,
  Table,
  TableBody,
  TableCell,
  TableColumn,
  TableHeader,
  TableRow,
} from "@nextui-org/react";
import Image from "next/image";
import Add from "./Add";

const CartTable = () => {
  return (
    <div className="flex flex-col gap-3">
      <Table
        color="default"
        selectionMode="multiple"
        defaultSelectedKeys={["2", "3"]}
        aria-label="Example static collection table"
      >
        <TableHeader>
          <TableColumn className="w-2/5">Product</TableColumn>
          <TableColumn className="w-1/5">{""}</TableColumn>
          <TableColumn className="w-1/5">Harga Product</TableColumn>
          <TableColumn className="w-1/2">Total Harga</TableColumn>
          <TableColumn className="w-1/5">Kuantitas</TableColumn>
          <TableColumn className="w-1/5">Aksi</TableColumn>
        </TableHeader>
        <TableBody>
          <TableRow key="1">
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
                    <h3 className="font-semibold">Lenovo LOQ i15l03</h3>
                    <span className="font-normal">
                      Intel core i5 13425h, RTX 3050 8gb, 16GB RAM, 512 SSD nvme
                    </span>
                  </div>
                </div>
              </div>
            </TableCell>
            <TableCell className="w-1/5">
              Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ex,
              ipsum.
            </TableCell>
            <TableCell className="w-1/5">Rp. 12.000.000, 00</TableCell>
            <TableCell className="w-1/2">Rp. 24.000.000, 00</TableCell>
            <TableCell className="w-1/5">
              <Add stockNumber={20} size="[5rem]" />
            </TableCell>
            <TableCell className="w-1/5 cursor-pointer">
              <Button className="bg-red-200">Hapus</Button>
            </TableCell>
          </TableRow>
        </TableBody>
      </Table>
    </div>
  );
};

export default CartTable;
