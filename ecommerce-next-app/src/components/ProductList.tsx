"use client";
import { Product } from "@/models/Product";
import { PropModel } from "@/models/PropModel";
import Image from "next/image";
import Link from "next/link";

const ProductList = ({ data: products }: PropModel<Product[]>) => {
  console.log(products);

  return (
    <div className="mt-12 flex gap-x-8 gap-y-16 justify-start flex-wrap">
      {products.map((product, index) => {
        return (
          <Link
            href={`/${product.code}`}
            className="w-full flex flex-col gap-4 sm:w-[45%] lg:w-[22%]"
          >
            <div className="relative w-full h-80">
              <Image
                src="https://images.unsplash.com/photo-1505740420928-5e560c06d30e?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                alt=""
                fill
                sizes="25vw"
                className="absolute object-cover rounded-md z-10 hover:opacity-0 transition-opacity easy duration-500"
              />
              <Image
                src="https://images.unsplash.com/photo-1553880414-5fe13d83ddb6?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                alt=""
                fill
                sizes="25vw"
                className="absolute object-cover rounded-md"
              />
            </div>
            <div className="flex justify-between">
              <span className="font-medium">{product.name}</span>
            </div>
            <span className="font-semibold">Rp. {product.price}</span>
            {/* TODO: It should be short description */}
            <div className="text-sm text-gray-500">{product.description}</div>
            <button className="rounded-2xl ring-1 ring-custom text-custom py-2 px-4 text-xs hover:bg-custom hover:text-white w-max">
              Add to cart
            </button>
          </Link>
        );
      })}
    </div>
  );
};

export default ProductList;
