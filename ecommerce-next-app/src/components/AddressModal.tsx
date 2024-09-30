import { AddressResponse } from "@/dto/responses/AddressResponse";
import { ProfileResponse } from "@/dto/responses/ProfileResponse";
import {
  Button,
  Modal,
  ModalBody,
  ModalContent,
  ModalFooter,
  ModalHeader,
  Radio,
  RadioGroup,
} from "@nextui-org/react";

const AddressModal = ({
  isOpen,
  onClose,
  profile,
}: {
  isOpen: boolean;
  onClose: () => void;
  profile: ProfileResponse | null;
}) => {
  return (
    <Modal size="2xl" isOpen={isOpen} onClose={onClose} scrollBehavior="inside">
      <ModalContent>
        {(onClose) => (
          <>
            <ModalHeader className="flex flex-col gap-1">
              Alamat saya
            </ModalHeader>
            <ModalBody>
              <RadioGroup
                defaultValue={`address-${profile?.addresses
                  .filter((address) => address.defaultAddress)
                  .map((address) => address.id)}`}
              >
                {profile?.addresses.map((address, index) => {
                  return (
                    <div className="p-2" key={index}>
                      <div className="flex justify-between">
                        <Radio value={`address-${address.id}`} color="danger">
                          <div>
                            <span className="font-bold">{profile?.name}</span>
                            <span className="font-light text-sm">
                              {" "}
                              | {profile?.phoneNumber}
                            </span>
                          </div>
                        </Radio>
                        <div className="cursor-pointer text-custom">Ubah</div>
                      </div>
                      <div className="ms-[28px]">
                        <p className="font-light  mt-1 w-[90%] text-sm">
                          {address.street}, {address.city}, {address.province},
                          ID {address.code}
                        </p>
                        {/* Appear when addres is default */}
                        {address.defaultAddress && (
                          <span className="border border-custom text-xs p-1 font-light size-min text-custom">
                            Utama
                          </span>
                        )}
                      </div>
                    </div>
                  );
                })}
              </RadioGroup>
            </ModalBody>
            <ModalFooter>
              <Button color="danger" variant="light" onPress={onClose}>
                Close
              </Button>
              <Button className="bg-custom text-white" onPress={onClose}>
                Konfirmasi
              </Button>
            </ModalFooter>
          </>
        )}
      </ModalContent>
    </Modal>
  );
};

export default AddressModal;
