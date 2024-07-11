"use client";

import Add from "@/components/Add";
import CustomizeProducts from "@/components/CustomizeProducts";
import ProductImages from "@/components/ProductImages";
import Reviews from "@/components/Reviews";
import { Suspense } from "react";
const product = {
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
const SinglePage = () => {
  return (
    <div className="px-4 md:px-8 lg:px-16 xl:px-32 2xl:px-64 relative flex flex-col lg:flex-row gap-16">
      {/* IMG */}
      <div className="w-full lg:w-1/2 lg:sticky top-20 h-max">
        <ProductImages key={product.code} items={product.media?.items} />
      </div>
      {/* TEXTS */}
      <div className="w-full lg:w-1/2 flex flex-col gap-6">
        <h1 className="text-4xl font-medium">{product.name}</h1>
        <p className="text-gray-500">{product.description}</p>
        <div className="h-[2px] bg-gray-100" />
        {product.pricing?.price === product.pricing?.discountedPrice ? (
          <h2 className="font-medium text-2xl">${product.pricing?.price}</h2>
        ) : (
          <div className="flex items-center gap-4">
            <h3 className="text-xl text-gray-500 line-through">
              ${product.pricing?.price}
            </h3>
            <h2 className="font-medium text-2xl">
              ${product.pricing?.discountedPrice}
            </h2>
          </div>
        )}
        <div className="h-[2px] bg-gray-100" />
        {product.variants && product.productOptions ? (
          <CustomizeProducts
            key={product.code}
            productId={product.code!}
            variants={product.variants}
            productOptions={product.productOptions}
          />
        ) : (
          <Add
            key={product.code}
            productId={product.code!}
            variantId="00000000-0000-0000-0000-000000000000"
            stockNumber={product.stock.quantity}
          />
        )}
        <div className="h-[2px] bg-gray-100" />
        {product.additionalInfoSections?.map((section: any) => (
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
