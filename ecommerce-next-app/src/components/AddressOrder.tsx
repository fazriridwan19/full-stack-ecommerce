"use client";
import {
  Card,
  CardHeader,
  CardBody,
  Button,
  useDisclosure,
} from "@nextui-org/react";
import AddressModal from "./AddressModal";
import { ProfileResponse } from "@/dto/responses/ProfileResponse";
import { useEffect, useState } from "react";
import { AddressResponse } from "@/dto/responses/AddressResponse";

const AddressOrder = ({ profile }: { profile: ProfileResponse | null }) => {
  const [address, setAddress] = useState<AddressResponse | null | undefined>(
    null
  );
  const { isOpen, onOpen, onClose } = useDisclosure(); // Modal
  const fetchAddress = () => {
    setAddress(profile?.addresses.find((address) => address.defaultAddress));
  };
  useEffect(() => {
    fetchAddress();
  }, [profile]);
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
            <span className="font-semibold">
              {profile?.name} {profile?.phoneNumber}
            </span>
            <span className="w-[60%]">
              {address?.street}, {address?.city}, {address?.province}, ID{" "}
              {address?.code}
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
      <AddressModal
        profile={profile}
        isOpen={isOpen}
        onClose={() => onClose()}
      />
    </>
  );
};

export default AddressOrder;
