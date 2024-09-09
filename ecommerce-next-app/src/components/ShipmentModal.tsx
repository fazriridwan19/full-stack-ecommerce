import {
  Button,
  Card,
  Modal,
  ModalBody,
  ModalContent,
  ModalFooter,
  ModalHeader,
  Radio,
  RadioGroup,
} from "@nextui-org/react";

const ShipmentModal = ({
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
              Pilih opsi pengiriman
            </ModalHeader>
            <ModalBody>
              <RadioGroup defaultValue={"tes"}>
                <div className="p-2">
                  <div className="flex justify-between">
                    <Radio value="tes" color="danger">
                      <span className="font-bold">JNE Reguler</span>
                    </Radio>
                    <div className="text-custom">Rp. 45.000,00</div>
                  </div>
                  <div className="ms-[28px]">
                    <p className="font-light  mt-1 w-[90%] text-sm">
                      Estimasi tiba: 10 - 13 Sep
                    </p>
                  </div>
                </div>
                <div className="p-2">
                  <div className="flex justify-between">
                    <Radio value="tes2" color="danger">
                      <span className="font-bold">JNE Express</span>
                    </Radio>
                    <div className="text-custom">Rp. 85.000,00</div>
                  </div>
                  <div className="ms-[28px]">
                    <p className="font-light  mt-1 w-[90%] text-sm">
                      Estimasi tiba: 09 - 11 Sep
                    </p>
                  </div>
                </div>
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

export default ShipmentModal;
