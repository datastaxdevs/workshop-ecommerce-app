import React from "react";
import { useOrders } from "../../hooks";
import Error from "../../components/Error";
import Loading from "../../components/Loading";
import { Link } from "react-router-dom";
const { DateTime } = require("luxon");

const OrderHistory = () => {
  const { data, loading, error } = useOrders();

  if (error) return <Error />;
  if (loading) return <Loading />;

  const orders = data ? data[0] : [];

  return (
    <div className="py-16 sm:py-24">
      <div className="max-w-7xl mx-auto sm:px-2 lg:px-8">
        <div className="max-w-2xl mx-auto px-4 lg:max-w-4xl lg:px-0">
          <h1 className="text-2xl font-extrabold tracking-tight text-gray-900 sm:text-3xl">
            Order history
          </h1>
          <p className="mt-2 text-sm text-gray-500">
            Check the status of recent orders, manage returns, and discover
            similar products.
          </p>
        </div>
      </div>

      <div className="mt-16">
        <div className="max-w-7xl mx-auto sm:px-2 lg:px-8">
          <div className="max-w-2xl mx-auto space-y-8 sm:px-4 lg:max-w-4xl lg:px-0">
            {orders.length !== 0 &&
              orders.map((order) => (
                <div
                  key={order.order_id}
                  className="bg-white border-t border-b border-gray-200 shadow-sm sm:rounded-lg sm:border"
                >
                  <div className="flex items-center p-4 border-b border-gray-200 sm:p-6 sm:grid sm:grid-cols-4 sm:gap-x-6">
                    <dl className="flex-1 grid grid-cols-2 gap-x-6 text-sm sm:col-span-3 sm:grid-cols-4 lg:col-span-3">
                      <div>
                        <dt className="font-medium text-gray-900">Order ID</dt>
                        <dd className="mt-1 text-gray-500">
                          {order.order_id.substring(0, 8)}
                        </dd>
                      </div>
                      <div className="hidden sm:block">
                        <dt className="font-medium text-gray-900">
                          Date placed
                        </dt>
                        <dd className="mt-1 text-gray-500">
                          <time dateTime={order.order_timestamp}>
                            {DateTime.fromISO(
                              order.order_timestamp
                            ).toLocaleString(DateTime.DATETIME_MED)}
                          </time>
                        </dd>
                      </div>
                      <div>
                        <dt className="font-medium text-gray-900">
                          Order Status
                        </dt>
                        <dd className="mt-1 text-gray-500">
                          {order.order_status}
                        </dd>
                      </div>
                      <div>
                        <dt className="font-medium text-gray-900">
                          Total amount
                        </dt>
                        <dd className="mt-1 font-medium text-gray-900">
                          {order.order_total}
                        </dd>
                      </div>
                    </dl>

                    <div className="hidden lg:col-span-1 lg:flex lg:items-center lg:justify-end lg:space-x-4">
                      <Link
                        to={`/orders/${order.order_id}`}
                        className="flex items-center justify-center bg-white py-2 px-2.5 border border-gray-300 rounded-md shadow-sm text-sm font-medium text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                      >
                        <span>View Order</span>
                      </Link>
                    </div>
                  </div>
                </div>
              ))}
            {orders.length === 0 && (
              <p className="mt-2 text-sm text-gray-500">No orders yet.</p>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

OrderHistory.propTypes = {};

OrderHistory.defaultProps = {};

export default OrderHistory;
