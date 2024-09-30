import { AddressResponse } from "./AddressResponse";

export interface ProfileResponse {
  id: number;
  name: string;
  email: string;
  avatar: string;
  phoneNumber: string;
  addresses: AddressResponse[];
}
