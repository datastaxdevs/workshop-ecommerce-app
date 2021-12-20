import React, { useState } from "react";
import { useProduct } from "../../hooks";
import { useParams } from "react-router-dom";
import { RadioGroup } from "@headlessui/react";
import { CurrencyDollarIcon, GlobeIcon } from "@heroicons/react/outline";
import { classNames } from "../../utils";
import NotFound from "../../components/NotFound";
import Error from "../../components/Error";

const defaultSpecifications = {
  colors: [
    {
      name: "Black",
      bgColor: "bg-gray-900",
      selectedColor: "ring-gray-900",
    },
    {
      name: "Heather Grey",
      bgColor: "bg-gray-400",
      selectedColor: "ring-gray-400",
    },
  ],
  sizes: [
    { name: "XXS", inStock: true },
    { name: "XS", inStock: true },
    { name: "S", inStock: true },
    { name: "M", inStock: true },
    { name: "L", inStock: true },
    { name: "XL", inStock: false },
  ],
};

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
  const [selectedColor, setSelectedColor] = useState(
    defaultSpecifications.colors[0]
  );
  const [selectedSize, setSelectedSize] = useState(
    defaultSpecifications.sizes[2]
  );
  const { data, error } = useProduct(params.productId);

  if (error) return <Error />;
  if (!data) return <div>loading...</div>;

  const product = data;
  if (!product) return <NotFound />;

  return (
    <main className="mt-8 max-w-2xl mx-auto pb-16 px-4 sm:pb-24 sm:px-6 lg:max-w-7xl lg:px-8">
      <div className="lg:grid lg:grid-cols-12 lg:auto-rows-min lg:gap-x-8">
        <div className="lg:col-start-8 lg:col-span-5">
          <div className="flex justify-between">
            <h1 className="text-xl font-medium text-gray-900">
              {product.name}
            </h1>
            <p className="text-xl font-medium text-gray-900">
              ${product.price}
            </p>
          </div>
          <div className="mt-4 prose prose-sm text-gray-500">
            {product.short_desc}
          </div>
        </div>

        {/* Image gallery */}
        <div className="mt-8 lg:mt-0 lg:col-start-1 lg:col-span-7 lg:row-start-1 lg:row-span-3">
          <h2 className="sr-only">Images</h2>

          <div className="grid grid-cols-1 lg:grid-cols-2 lg:grid-rows-3 lg:gap-8">
            {product.images.map((image, index) => (
              <img
                key={index}
                src={image}
                alt={product.name}
                className={classNames(
                  image.primary
                    ? "lg:col-span-2 lg:row-span-2"
                    : "hidden lg:block",
                  "rounded-lg"
                )}
              />
            ))}
          </div>
        </div>

        <div className="mt-8 lg:col-span-5">
          <form>
            {/* Color picker */}
            <div>
              <h2 className="text-sm font-medium text-gray-900">Color</h2>

              <RadioGroup
                value={selectedColor}
                onChange={setSelectedColor}
                className="mt-2"
              >
                <RadioGroup.Label className="sr-only">
                  Choose a color
                </RadioGroup.Label>
                <div className="flex items-center space-x-3">
                  {product.specifications.colors.map((color) => (
                    <RadioGroup.Option
                      key={color.name}
                      value={color}
                      className={({ active, checked }) =>
                        classNames(
                          color.selectedColor,
                          active && checked ? "ring ring-offset-1" : "",
                          !active && checked ? "ring-2" : "",
                          "-m-0.5 relative p-0.5 rounded-full flex items-center justify-center cursor-pointer focus:outline-none"
                        )
                      }
                    >
                      <RadioGroup.Label as="p" className="sr-only">
                        {color.name}
                      </RadioGroup.Label>
                      <span
                        aria-hidden="true"
                        className={classNames(
                          color.bgColor,
                          "h-8 w-8 border border-black border-opacity-10 rounded-full"
                        )}
                      />
                    </RadioGroup.Option>
                  ))}
                </div>
              </RadioGroup>
            </div>

            {/* Size picker */}
            <div className="mt-8">
              <div className="flex items-center justify-between">
                <h2 className="text-sm font-medium text-gray-900">Size</h2>
                <a
                  href="/"
                  className="text-sm font-medium text-indigo-600 hover:text-indigo-500"
                >
                  See sizing chart
                </a>
              </div>

              <RadioGroup
                value={selectedSize}
                onChange={setSelectedSize}
                className="mt-2"
              >
                <RadioGroup.Label className="sr-only">
                  Choose a size
                </RadioGroup.Label>
                <div className="grid grid-cols-3 gap-3 sm:grid-cols-6">
                  {product.specifications.sizes.map((size) => (
                    <RadioGroup.Option
                      key={size.name}
                      value={size}
                      className={({ active, checked }) =>
                        classNames(
                          size.inStock
                            ? "cursor-pointer focus:outline-none"
                            : "opacity-25 cursor-not-allowed",
                          active ? "ring-2 ring-offset-2 ring-indigo-500" : "",
                          checked
                            ? "bg-indigo-600 border-transparent text-white hover:bg-indigo-700"
                            : "bg-white border-gray-200 text-gray-900 hover:bg-gray-50",
                          "border rounded-md py-3 px-3 flex items-center justify-center text-sm font-medium uppercase sm:flex-1"
                        )
                      }
                      disabled={!size.inStock}
                    >
                      <RadioGroup.Label as="p">{size.name}</RadioGroup.Label>
                    </RadioGroup.Option>
                  ))}
                </div>
              </RadioGroup>
            </div>

            <button
              type="submit"
              className="mt-8 w-full bg-indigo-600 border border-transparent rounded-md py-3 px-8 flex items-center justify-center text-base font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
            >
              Add to cart
            </button>
          </form>

          {/* Product details */}
          <div className="mt-10">
            <h2 className="text-sm font-medium text-gray-900">Description</h2>

            <div className="mt-4 prose prose-sm text-gray-500">
              {product.long_desc}
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
