import { cookies } from "next/headers";

export const getCookie = (name: string) => {
  return cookies().get(name)?.value;
};

export const setCookie = (name: string, value: string) => {
  cookies().set(name, value, {
    httpOnly: true,
    secure: true,
  });
};

export const hasCookie = () => {
  return cookies().has("token");
};

export const deleteCookie = () => {
  cookies().delete("token");
};
