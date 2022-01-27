import React from "react";
import { useProducts } from "../../hooks";
import { Link, useParams } from "react-router-dom";
import Error from "../../components/Error";
import Loading from "../../components/Loading";

const ProductList = () => {
  const params = useParams();
  const { data: products, error } = useProducts(params.categoryId);
  if (error) return <Error />;
  if (!products) return <Loading />;

  return (
    <div className="max-w-2xl mx-auto py-16 px-4 sm:py-24 sm:px-6 lg:max-w-7xl lg:px-8">
      <h2 className="text-2xl font-extrabold tracking-tight text-gray-900">
        {params.categoryName || "Featured Products"}
      </h2>

      <div className="mt-6 grid grid-cols-1 gap-y-10 gap-x-6 sm:grid-cols-2 lg:grid-cols-4 xl:gap-x-8">
        {products.map((product, index) => (
          <div key={index} className="group relative">
            <div className="w-full min-h-80 bg-gray-200 aspect-w-1 aspect-h-1 rounded-md overflow-hidden group-hover:opacity-75 lg:h-80 lg:aspect-none">
              <img
                src={`/images/${product.image}`}
                alt={product.name}
                className="w-full h-full object-center object-cover lg:w-full lg:h-full"
              />
            </div>
            <div className="mt-4 flex justify-between">
              <div>
                <h3 className="text-sm text-gray-700">
                  <Link
                    to={`/products/${product.parentId}/${product.categoryId}`}
                  >
                    <span aria-hidden="true" className="absolute inset-0" />
                    {product.name}
                  </Link>
                </h3>
                <p className="mt-1 text-sm text-gray-500">
                  {product.product_group}
                </p>
              </div>
              <p className="text-sm font-medium text-gray-900">
                {product.price && `$${product.price}`}
              </p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

ProductList.propTypes = {};

ProductList.defaultProps = {};

export default ProductList;
