import React from "react";
import { Link } from "react-router-dom";
import { useCartId, useCart } from "../../hooks";
import { CheckIcon } from "@heroicons/react/solid";
import NotFound from "../../components/NotFound";
import Error from "../../components/Error";
import Loading from "../../components/Loading";
import toast from "react-hot-toast";
import _ from "lodash";

const Cart = () => {
  const cartId = useCartId();
  let { cart, loading, error, setCart } = useCart(cartId);

  if (error) return <Error />;
  if (loading) return <Loading />;
  if (!cart) return <NotFound />;

  const removeFromCart = async (productId) => {
    const res = await fetch(`/api/v1/carts/${cartId}/products/${productId}/`, {
      method: "DELETE",
    });
    const resJson = await res.json();
    console.log(resJson);
    toast.success("Removed from Cart");
    setCart(_.reject(cart, { product_id: productId }));
  };

  const getCartTotal = () => {
    let total = 0;
    cart.forEach((cartItem) => (total += cartItem.product.price.value));
    return total.toFixed(2);
  };

  return (
    <div className="max-w-2xl mx-auto py-16 px-4 sm:py-24 sm:px-6 lg:px-0">
      <h1 className="text-3xl font-extrabold text-center tracking-tight text-gray-900 sm:text-4xl">
        Shopping Cart
      </h1>

      <form className="mt-12">
        <section aria-labelledby="cart-heading">
          <h2 id="cart-heading" className="sr-only">
            Items in your shopping cart
          </h2>

          <ul className="border-t border-b border-gray-200 divide-y divide-gray-200">
            {cart.map((cartItem, index) => (
              <li key={index} className="flex py-6">
                <div className="flex-shrink-0">
                  <img
                    src={`/images/${cartItem.product.images[0]}`}
                    alt={cartItem.product.name}
                    className="w-24 h-24 rounded-md object-center object-cover sm:w-32 sm:h-32"
                  />
                </div>

                <div className="ml-4 flex-1 flex flex-col sm:ml-6">
                  <div>
                    <div className="flex justify-between">
                      <h4 className="text-sm">
                        <p className="font-medium text-gray-700 hover:text-gray-800">
                          {cartItem.product.name}
                        </p>
                      </h4>
                      <p className="ml-4 text-sm font-medium text-gray-900">
                        ${cartItem.product.price.value}
                      </p>
                    </div>
                    <p className="mt-1 text-sm text-gray-500">
                      {cartItem.product.specifications.color}
                    </p>
                    <p className="mt-1 text-sm text-gray-500">
                      {cartItem.product.specifications.size}
                    </p>
                  </div>

                  <div className="mt-4 flex-1 flex items-end justify-between">
                    <p className="flex items-center text-sm text-gray-700 space-x-2">
                      <CheckIcon
                        className="flex-shrink-0 h-5 w-5 text-green-500"
                        aria-hidden="true"
                      />
                      <span>In stock</span>
                    </p>
                    <div className="ml-4">
                      <button
                        type="button"
                        className="text-sm font-medium text-indigo-600 hover:text-indigo-500"
                        onClick={() => removeFromCart(cartItem.product_id)}
                      >
                        <span>Remove</span>
                      </button>
                    </div>
                  </div>
                </div>
              </li>
            ))}
          </ul>
        </section>

        {/* Order summary */}
        <section aria-labelledby="summary-heading" className="mt-10">
          <h2 id="summary-heading" className="sr-only">
            Order summary
          </h2>

          <div>
            <dl className="space-y-4">
              <div className="flex items-center justify-between">
                <dt className="text-base font-medium text-gray-900">
                  Subtotal
                </dt>
                <dd className="ml-4 text-base font-medium text-gray-900">
                  ${getCartTotal()}
                </dd>
              </div>
            </dl>
            <p className="mt-1 text-sm text-gray-500">
              Shipping and taxes will be calculated at checkout.
            </p>
          </div>

          <div className="mt-10">
            <button
              type="submit"
              className="w-full bg-indigo-600 border border-transparent rounded-md shadow-sm py-3 px-4 text-base font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-offset-gray-50 focus:ring-indigo-500"
            >
              Checkout
            </button>
          </div>

          <div className="mt-6 text-sm text-center text-gray-500">
            <p>
              or{" "}
              <Link
                to="/"
                className="text-indigo-600 font-medium hover:text-indigo-500"
              >
                Continue Shopping<span aria-hidden="true"> &rarr;</span>
              </Link>
            </p>
          </div>
        </section>
      </form>
    </div>
  );
};

Cart.propTypes = {};

Cart.defaultProps = {};

export default Cart;
