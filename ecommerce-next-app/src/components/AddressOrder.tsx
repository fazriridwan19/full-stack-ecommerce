import {
  Card,
  CardHeader,
  CardBody,
  Button,
  useDisclosure,
} from "@nextui-org/react";
import AddressModal from "./AddressModal";

const AddressOrder = () => {
  const { isOpen, onOpen, onClose } = useDisclosure(); // Modal
  return (
    <>
      <Card className="p-3 w-full">
        <CardHeader>
          <div className="text-custom flex gap-2 text-lg items-center">
            <svg
              className="w-[15px] fill-custom"
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 384 512"
            >
              <path d="M215.7 499.2C267 435 384 279.4 384 192C384 86 298 0 192 0S0 86 0 192c0 87.4 117 243 168.3 307.2c12.3 15.3 35.1 15.3 47.4 0zM192 128a64 64 0 1 1 0 128 64 64 0 1 1 0-128z" />
            </svg>
            Alamat Pengirim
          </div>
        </CardHeader>
        <CardBody>
          {/* Alamat */}
          <div className="flex gap-2 justify-between">
            <span className="font-semibold">Fazri Ridwan (+62)5352307024</span>
            <span className="w-[60%]">
              Kost Pondok Damai 2, Jalan Paseban Timur Gang XV No. 300,
              RT.18/RW.3, Kelurahan Paseban, Senen (KOST PONDOK DAMAI 2), KOTA
              JAKARTA PUSAT - SENEN, DKI JAKARTA, ID 10440
            </span>
            <span className="border border-custom text-xs p-1 font-light size-min text-custom">
              Utama
            </span>
            <Button
              onPress={() => onOpen()}
              className="cursor-pointer bg-custom text-white"
            >
              Ubah
            </Button>
          </div>
        </CardBody>
      </Card>
      <AddressModal isOpen={isOpen} onClose={() => onClose()} />
    </>
  );
};

export default AddressOrder;
