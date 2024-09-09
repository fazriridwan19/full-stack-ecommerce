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

const OrderedProducts = () => {
  const { isOpen, onOpen, onClose } = useDisclosure(); // Modal
  return (
    <>
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
                <TableCell className="font-light">
                  Variasi: Hitam - SSD 512 GB
                </TableCell>
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
                <TableCell className="font-light">
                  Variasi: Hitam - SSD 512 GB
                </TableCell>
                <TableCell>Rp. 15.000.000,00</TableCell>
                <TableCell>1</TableCell>
                <TableCell>Rp. 15.000.000,00</TableCell>
              </TableRow>
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
            <span className="text-custom">Rp. 30.000.000,00</span>
          </div>
        </CardFooter>
      </Card>
      <ShipmentModal isOpen={isOpen} onClose={() => onClose()} />
    </>
  );
};

export default OrderedProducts;
