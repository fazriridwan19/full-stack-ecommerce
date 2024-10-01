"use client";

import Add from "@/components/Add";
import CustomizeProducts from "@/components/CustomizeProducts";
import ProductImages from "@/components/ProductImages";
import { Product } from "@/models/Product";
import { getProductById } from "@/services/ProductService";
import React from "react";
import { useEffect, useState } from "react";

const productEx = {
  id: 1,
  code: "00000",
  name: "Product test 1",
  description:
    "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ex praesentium optio nostrum voluptate facilis, provident repellat molestiae cum velit placeat voluptatem omnis. Optio culpa voluptatibus maxime doloremque, quos enim odio.",
  pricing: {
    discountedPrice: 1000,
    price: 2000,
  },
  stock: {
    isInStock: true,
    quantity: 3,
  },
  media: {
    items: [
      {
        id: 1,
        image: {
          url: "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        },
      },
      {
        id: 2,
        image: {
          url: "https://images.unsplash.com/photo-1553880414-5fe13d83ddb6?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        },
      },
      {
        id: 3,
        image: {
          url: "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        },
      },
      {
        id: 4,
        image: {
          url: "https://images.unsplash.com/photo-1553880414-5fe13d83ddb6?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        },
      },
    ],
  },
  variants: null,
  productOptions: null,
  additionalInfoSections: [
    {
      title: "Product Info",
      description:
        "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Nobis esse suscipit doloribus harum earum! Vero est obcaecati, mollitia natus reiciendis laboriosam! Nemo mollitia iusto vero maxime amet ab ex architecto.",
    },
    {
      title: "Product Info 2",
      description:
        "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Nobis esse suscipit doloribus harum earum! Vero est obcaecati, mollitia natus reiciendis laboriosam! Nemo mollitia iusto vero maxime amet ab ex architecto.",
    },
    {
      title: "Product Info 3",
      description:
        "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Nobis esse suscipit doloribus harum earum! Vero est obcaecati, mollitia natus reiciendis laboriosam! Nemo mollitia iusto vero maxime amet ab ex architecto.",
    },
  ],
};
const SinglePage = ({ params }: { params: { slug: string } }) => {
  // TODO : Use custom hooks
  const [product, setProduct] = useState<Product | null>(null);
  const fetchData = async () => {
    try {
      const res = await getProductById(params.slug);
      if (res && res.status === 200) {
        setProduct(res.data);
      }
    } catch (error) {
      console.log(error);
    }
  };
  useEffect(() => {
    fetchData();
  }, []);
  let idrFormatter = new Intl.NumberFormat("id-ID", {
    style: "currency",
    currency: "IDR",
  });
  return (
    <div className="px-4 md:px-8 lg:px-16 xl:px-32 2xl:px-64 relative flex flex-col lg:flex-row gap-16">
      {/* IMG */}
      <div className="w-full lg:w-1/2 lg:sticky top-20 h-max">
        <ProductImages key={product?.code} items={productEx.media?.items} />
      </div>
      {/* TEXTS */}
      <div className="w-full lg:w-1/2 flex flex-col gap-6">
        <h1 className="text-4xl font-medium">{product?.name}</h1>
        <p className="text-gray-500">{product?.description}</p>
        <div className="h-[2px] bg-gray-100" />
        {product?.price === product?.discountedPrice ? (
          <h2 className="font-medium text-2xl">
            {idrFormatter.format(product?.price as number)}
          </h2>
        ) : (
          <div className="flex items-center gap-4">
            <h3 className="text-xl text-gray-500 line-through">
              {idrFormatter.format(product?.price as number)}
            </h3>
            <h2 className="font-medium text-2xl">
              {idrFormatter.format(product?.discountedPrice as number)}
            </h2>
          </div>
        )}
        <div className="h-[2px] bg-gray-100" />
        <div className="flex justify-between items-center">
          {productEx.variants && productEx.productOptions ? (
            <CustomizeProducts
              key={product?.code}
              productId={product?.code!}
              variants={productEx?.variants}
              productOptions={productEx?.productOptions}
            />
          ) : (
            <>
              <div className="flex flex-col gap-2">
                <h4 className="font-medium">Choose a Quantity</h4>
                <Add
                  key={product?.code}
                  stockNumber={product?.stock as number}
                />
              </div>
              {(product?.stock as number) < 1 ? (
                <div className="text-xs">Product is out of stock</div>
              ) : (
                <div className="text-xs">
                  Only{" "}
                  <span className="text-orange-500">
                    {product?.stock as number} items
                  </span>{" "}
                  left!
                  <br /> {"Don't"} miss it
                </div>
              )}
            </>
          )}
          <button className="h-10 w-36 text-sm rounded-3xl ring-1 ring-custom text-lama py-2 px-4 hover:bg-custom hover:ring-white disabled:cursor-not-allowed disabled:bg-pink-200 disabled:ring-0 disabled:text-white disabled:ring-none">
            Add to Cart
          </button>
        </div>
        <div className="h-[2px] bg-gray-100" />
        {productEx.additionalInfoSections?.map((section: any) => (
          <div className="text-sm" key={section.title}>
            <h4 className="font-medium mb-4">{section.title}</h4>
            <p>{section.description}</p>
          </div>
        ))}
        <div className="h-[2px] bg-gray-100" />
        {/* REVIEWS */}
        {/* <h1 className="text-2xl">User Reviews</h1>
        <Suspense fallback="Loading...">
          <Reviews productId={product.code!} />
        </Suspense> */}
      </div>
    </div>
  );
};

export default SinglePage;
