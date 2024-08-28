"use client";
import { EyeFilledIcon } from "@/components/EyeFilledIcon";
import { EyeSlashFilledIcon } from "@/components/EyeSlashFilled";
import {
  Card,
  CardHeader,
  Input,
  CardBody,
  CardFooter,
  Button,
  Textarea,
} from "@nextui-org/react";
import Link from "next/link";
import { useState } from "react";

export default function RegistrationPage() {
  const [isVisible, setIsVisible] = useState(false);
  const toggleVisibility = () => setIsVisible(!isVisible);
  return (
    <div className="flex flex-col h-screen justify-center items-center bg-gray-100 dark:bg-gray-900">
      <Card className="w-full max-w-md">
        <CardHeader className="justify-center">
          <h3 className="text-2xl text-center">Registration</h3>
        </CardHeader>
        <CardBody className="space-y-4">
          <div className="space-y-2">
            <Input isClearable type="text" label="Name" variant="bordered" />
          </div>
          <div className="space-y-2">
            <Input isClearable type="email" label="Email" variant="bordered" />
          </div>
          <div className="space-y-2">
            <Input
              isClearable
              type="text"
              label="Phone number"
              variant="bordered"
            />
          </div>
          <div className="space-y-2">
            <Textarea
              label="Address"
              variant="bordered"
              disableAnimation
              disableAutosize
              classNames={{
                input: "resize-y min-h-[40px]",
              }}
            />
          </div>
          <div className="space-y-2">
            <Input
              isClearable
              type="text"
              label="Username"
              variant="bordered"
            />
          </div>
          <div className="space-y-2">
            <Input
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
            />
          </div>
        </CardBody>
        <CardFooter className="flex flex-col space-y-2">
          <Button className="w-full bg-black text-white">Registration</Button>
        </CardFooter>
      </Card>
      <div className="mt-4">
        <span className="text-sm">Already have an account? </span>
        <Link href="/login" className="text-sm text-blue-500" prefetch={false}>
          Login
        </Link>
      </div>
    </div>
  );
}
