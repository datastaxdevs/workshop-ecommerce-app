import React, { useState } from "react";
import { useProduct, useCartId } from "../../hooks";
import { useParams } from "react-router-dom";
import { RadioGroup } from "@headlessui/react";
import { CurrencyDollarIcon, GlobeIcon } from "@heroicons/react/outline";
import { classNames } from "../../utils";
import NotFound from "../../components/NotFound";
import Error from "../../components/Error";
import Loading from "../../components/Loading";
import toast from "react-hot-toast";

const policies = [
  {
    name: "International delivery",
    icon: GlobeIcon,
    description: "Get your order in 2 years",
  },
  {
    name: "Loyalty rewards",
    icon: CurrencyDollarIcon,
    description: "Don't look at other tees",
  },
];

const ProductDetail = () => {
  const params = useParams();
  const [selectedProduct, setSelectedProduct] = useState(null);
  let { product, loading, error } = useProduct(
    params.parentId,
    params.categoryId
  );
  const cartId = useCartId();

  if (error) return <Error />;
  if (loading) return <Loading />;
  if (!product) return <NotFound />;

  if (!selectedProduct) {
    setSelectedProduct(product.products[0]);
    return <NotFound />;
  }

  const productImage = selectedProduct.images[0] ?? product.image;

  const addToCart = async () => {
    const res = await fetch(
      `/api/v1/carts/${cartId}/products/${selectedProduct.product_id}/`,
      {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          quantity: 1,
          cart_id: cartId,
          product_id: selectedProduct.product_id,
          product_description: selectedProduct.short_desc,
          product_name: selectedProduct.name,
        }),
      }
    );
    const resJson = await res.json();
    console.log(resJson);
    toast.success("Added to Cart");
  };

  return (
    <main className="mt-8 max-w-2xl mx-auto pb-16 px-4 sm:pb-24 sm:px-6 lg:max-w-7xl lg:px-8">
      <div className="lg:grid lg:grid-cols-12 lg:auto-rows-min lg:gap-x-8">
        <div className="lg:col-start-8 lg:col-span-5">
          <div className="flex justify-between">
            <h1 className="text-xl font-medium text-gray-900">
              {selectedProduct.name}
            </h1>
            <p className="text-xl font-medium text-gray-900">
              ${selectedProduct.price.value}
            </p>
          </div>
          <div className="mt-4 prose prose-sm text-gray-500">
            {selectedProduct.short_desc}
          </div>
        </div>

        {/* Image gallery */}
        <div className="mt-8 lg:mt-0 lg:col-start-1 lg:col-span-7 lg:row-start-1 lg:row-span-3">
          <h2 className="sr-only">Images</h2>

          <div className="grid grid-cols-1 lg:grid-cols-2 lg:grid-rows-3 lg:gap-8">
            <img
              src={`/images/${productImage}`}
              alt={product.name}
              className={classNames(
                "lg:col-span-2 lg:row-span-2",
                "rounded-lg"
              )}
            />
          </div>
        </div>

        <div className="mt-8 lg:col-span-5">
          <form>
            {/* Product Options */}
            <div className="mt-8">
              <div className="flex items-center justify-between">
                <h2 className="text-sm font-medium text-gray-900">Options</h2>
              </div>

              <RadioGroup
                value={selectedProduct}
                onChange={setSelectedProduct}
                className="mt-2"
              >
                <RadioGroup.Label className="sr-only">
                  Choose an option
                </RadioGroup.Label>
                <div className="grid grid-cols-3 gap-3 sm:grid-cols-6">
                  {product.products.map((option) => (
                    <RadioGroup.Option
                      key={option.product_id}
                      value={option}
                      className={({ active, checked }) =>
                        classNames(
                          "cursor-pointer focus:outline-none",
                          active ? "ring-2 ring-offset-2 ring-indigo-500" : "",
                          checked
                            ? "bg-indigo-600 border-transparent text-white hover:bg-indigo-700"
                            : "bg-white border-gray-200 text-gray-900 hover:bg-gray-50",
                          "border rounded-md py-3 px-3 flex items-center justify-center text-sm font-medium uppercase sm:flex-1 flex-wrap"
                        )
                      }
                    >
                      <RadioGroup.Label as="p">
                        {option.specifications.size}
                      </RadioGroup.Label>
                    </RadioGroup.Option>
                  ))}
                </div>
              </RadioGroup>
            </div>

            <button
              onClick={(e) => {
                e.preventDefault();
                addToCart();
              }}
              className="mt-8 w-full bg-indigo-600 border border-transparent rounded-md py-3 px-8 flex items-center justify-center text-base font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
            >
              Add to cart
            </button>
          </form>

          {/* Product details */}
          <div className="mt-10">
            <h2 className="text-sm font-medium text-gray-900">Description</h2>

            <div className="mt-4 prose prose-sm text-gray-500">
              {selectedProduct.long_desc}
            </div>
          </div>

          {/* Policies */}
          <section aria-labelledby="policies-heading" className="mt-10">
            <h2 id="policies-heading" className="sr-only">
              Our Policies
            </h2>

            <dl className="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-1 xl:grid-cols-2">
              {policies.map((policy) => (
                <div
                  key={policy.name}
                  className="bg-gray-50 border border-gray-200 rounded-lg p-6 text-center"
                >
                  <dt>
                    <policy.icon
                      className="mx-auto h-6 w-6 flex-shrink-0 text-gray-400"
                      aria-hidden="true"
                    />
                    <span className="mt-4 text-sm font-medium text-gray-900">
                      {policy.name}
                    </span>
                  </dt>
                  <dd className="mt-1 text-sm text-gray-500">
                    {policy.description}
                  </dd>
                </div>
              ))}
            </dl>
          </section>
        </div>
      </div>
    </main>
  );
};

ProductDetail.propTypes = {};

ProductDetail.defaultProps = {};

export default ProductDetail;
