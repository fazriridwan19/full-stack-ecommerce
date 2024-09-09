import {
  Button,
  Divider,
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
}: {
  isOpen: boolean;
  onClose: () => void;
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
              <RadioGroup defaultValue={"tes"}>
                <div className="p-2">
                  <div className="flex justify-between">
                    <Radio value="tes" color="danger">
                      <div>
                        <span className="font-bold">Fazri Ridwan</span>
                        <span className="font-light text-sm">
                          {" "}
                          | (+62) 853 5230 7024
                        </span>
                      </div>
                    </Radio>
                    <div className="cursor-pointer text-custom">Ubah</div>
                  </div>
                  <div className="ms-[28px]">
                    <p className="font-light  mt-1 w-[90%] text-sm">
                      Kost Pondok Damai 2, Jalan Paseban Timur Gang XV No. 300,
                      RT.18/RW.3, Kelurahan Paseban, Senen (KOST PONDOK DAMAI
                      2), KOTA JAKARTA PUSAT - SENEN, DKI JAKARTA, ID 10440
                    </p>
                    {/* Appear when addres is default */}
                    <span className="border border-custom w-min p-1 font-light text-sm text-custom">
                      Utama
                    </span>
                  </div>
                </div>
              </RadioGroup>
            </ModalBody>
            <ModalFooter>
              <Button color="danger" variant="light" onPress={onClose}>
                Close
              </Button>
              <Button className="bg-custom text-white" onPress={onClose}>
                Action
              </Button>
            </ModalFooter>
          </>
        )}
      </ModalContent>
    </Modal>
  );
};

export default AddressModal;
