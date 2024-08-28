"use client";

import CategoryList from "@/components/CategoryList";
import Footer from "@/components/Footer";
import Navbar from "@/components/Navbar";
import ProductList from "@/components/ProductList";
import Slider from "@/components/Slider";
import { Category } from "@/models/Category";
import { Product } from "@/models/Product";
import { getCategories } from "@/services/CategoryService";
import { getProducts } from "@/services/ProductService";
import { useEffect, useState } from "react";

const Homepage = () => {
  const [products, setProducts] = useState<Product[]>([]);
  const [categories, setCategories] = useState<Category[]>([]);
  const fetchData = async () => {
    try {
      const productsResponse = await getProducts();
      const categoriesResponse = await getCategories();
      setCategories(categoriesResponse.data);
      setProducts(productsResponse.data);
    } catch (error) {
      console.log(error);
    }
  };
  useEffect(() => {
    fetchData();
  }, []);
  return (
    <>
      <div className="">
        <Slider />
        <div className="mt-24 px-4 md:px-8 lg:px-16 xl:px-32 2xl:px-64">
          <h1 className="text-2xl">Featured Product</h1>
          <ProductList data={products} />
        </div>
        <div className="mt-24">
          <h1 className="text-2xl mt-24 px-4 md:px-8 lg:px-16 xl:px-32 2xl:px-64 mb-12">
            Categories
          </h1>
          <CategoryList data={categories} />
        </div>
        <div className="mt-24 px-4 md:px-8 lg:px-16 xl:px-32 2xl:px-64">
          <h1 className="text-2xl">New Product</h1>
          <ProductList data={products} />
        </div>
      </div>
    </>
  );
};

export default Homepage;
