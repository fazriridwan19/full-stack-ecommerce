import { cookies } from "next/headers";

export const getCookie = (name: string) => {
  return cookies().get(name)?.value;
};

export const setCookie = (name: string, value: string) => {
  cookies().set(name, value, {
    httpOnly: true,
    secure: true,
    expires: Date.now() + 1000 * 60 * 60,
  });
};

export const hasCookie = async () => {
  return cookies().has("token") && !!cookies().get("token")?.value;
};

export const deleteCookie = () => {
  cookies().delete("token");
};
