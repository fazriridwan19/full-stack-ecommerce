"use client";
import { EyeFilledIcon } from "@/components/EyeFilledIcon";
import { EyeSlashFilledIcon } from "@/components/EyeSlashFilled";
import { LoginRequest } from "@/dto/requests/LoginRequest";
import { ErrorResponse } from "@/error/ErrorResponse";
import { login } from "@/services/AuthService";
import {
  Button,
  Card,
  CardBody,
  CardFooter,
  CardHeader,
  CircularProgress,
  Input,
} from "@nextui-org/react";
import { AxiosError } from "axios";
import Link from "next/link";
import { useRouter, useSearchParams } from "next/navigation";
import { FormEvent, useMemo, useState } from "react";
import Swal from "sweetalert2";

export default function LoginPage() {
  const [loading, setLoading] = useState(false);
  const [loginRequest, setLoginRequest] = useState<LoginRequest | null>(null);
  const [isVisible, setIsVisible] = useState(false);
  const toggleVisibility = () => setIsVisible(!isVisible);
  const router = useRouter();
  const error =
    useSearchParams().has("error") && !!useSearchParams().get("error");
  const handleLogin = async (e: FormEvent<HTMLButtonElement>) => {
    e.preventDefault();
    if (loginRequest && loginRequest.username && loginRequest.password) {
      try {
        setLoading(true);
        await login(loginRequest);
        setLoading(false);
        router.push("/");
      } catch (error: any) {
        setLoading(false);
        Swal.fire({
          icon: "error",
          title: "Oops...",
          text: `${AxiosError.from(error).message}`,
        });
      }
    }
  };
  const isInvalid: boolean = useMemo(() => {
    if (!loginRequest) return false;
    return !loginRequest.username || !loginRequest.password;
  }, [loginRequest]) as boolean;

  return (
    <div className="flex flex-col h-screen justify-center items-center bg-gray-100 dark:bg-gray-900">
      {error && (
        <div
          className="p-4 mb-4 text-sm text-red-800 rounded-lg bg-red-50 dark:bg-gray-800 dark:text-red-400"
          role="alert"
        >
          <span className="font-medium">Login required !</span> Login before
          processing yout request.
        </div>
      )}
      <Card className="w-full max-w-md">
        <CardHeader className="justify-center">
          <h3 className="text-2xl text-center">Login</h3>
        </CardHeader>
        <CardBody className="space-y-4">
          <div className="space-y-2">
            <Input
              required
              isClearable
              type="text"
              label="Username"
              variant="bordered"
              isInvalid={isInvalid}
              errorMessage="Username cannot be empty"
              onChange={(e) =>
                setLoginRequest({ ...loginRequest, username: e.target.value })
              }
            />
          </div>
          <div className="space-y-2">
            <Input
              required
              label="Password"
              variant="bordered"
              endContent={
                <button
                  className="focus:outline-none"
                  type="button"
                  onClick={toggleVisibility}
                  aria-label="toggle password visibility"
                >
                  {isVisible ? (
                    <EyeFilledIcon className="text-2xl text-default-400 pointer-events-none" />
                  ) : (
                    <EyeSlashFilledIcon className="text-2xl text-default-400 pointer-events-none" />
                  )}
                </button>
              }
              type={isVisible ? "text" : "password"}
              onChange={(e) =>
                setLoginRequest({ ...loginRequest, password: e.target.value })
              }
              isInvalid={isInvalid}
              errorMessage="Password cannot be empty"
            />
          </div>
        </CardBody>
        <CardFooter className="flex flex-col space-y-2">
          <Button
            className="w-full bg-black text-white"
            onClick={(e) => handleLogin(e)}
          >
            {loading ? <CircularProgress /> : "Login"}
          </Button>
          <Link href="#" className="text-sm text-center" prefetch={false}>
            Forgot Password?
          </Link>
        </CardFooter>
      </Card>
      <div className="mt-4">
        <span className="text-sm">Don't have an account? </span>
        <Link
          href="/registration"
          className="text-sm text-blue-500"
          prefetch={false}
        >
          Registration
        </Link>
      </div>
    </div>
  );
}
