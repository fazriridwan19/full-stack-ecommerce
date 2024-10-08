"use client";
import Filter from "@/components/Filter";
import ProductList from "@/components/ProductList";
import { Product } from "@/models/Product";
import { getProducts } from "@/services/ProductService";
import Image from "next/image";
import { useEffect, useState } from "react";

const ListPage = () => {
  const [products, setProducts] = useState<Product[]>([]);
  const fetchData = async () => {
    try {
      const res = await getProducts();
      setProducts(res.data);
    } catch (error) {
      console.log(error);
    }
  };
  useEffect(() => {
    fetchData();
  }, []);

  return (
    <div className="px-4 md:px-8 lg:px-16 xl:px-32 2xl:px-64 relative">
      {/* CAMPAIGN */}
      <div className="hidden bg-pink-50 px-4 sm:flex justify-between h-64">
        <div className="w-2/3 flex flex-col items-center justify-center gap-8">
          <h1 className="text-4xl font-semibold leading-[48px] text-gray-700">
            Dapatkan potongan harga
            <br />
            hingga 50%
          </h1>
          <button className="rounded-3xl bg-custom text-white w-max py-3 px-5 text-sm">
            Buy Now
          </button>
        </div>
        <div className="relative w-1/3">
          <Image src="/woman.png" alt="" fill className="object-contain" />
        </div>
      </div>

      {/* FILTER */}
      <Filter />

      {/* PRODUCTS */}
      <h1 className="mt-12 text-xl font-semibold">Shoes for you</h1>
      <ProductList data={products} />
    </div>
  );
};

export default ListPage;
