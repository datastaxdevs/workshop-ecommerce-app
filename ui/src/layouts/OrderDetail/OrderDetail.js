import React from "react";
import { useOrder } from "../../hooks";
import { useParams } from "react-router-dom";
import Error from "../../components/Error";
import Loading from "../../components/Loading";
const { DateTime } = require("luxon");

const OrderDetail = () => {
  const { orderId } = useParams();
  const { data: order, loading, error } = useOrder(orderId);

  if (error) return <Error />;
  if (loading || !order) return <Loading />;

  console.log(order);

  return (
    <div className="py-16 sm:py-24">
      <div className="max-w-7xl mx-auto sm:px-2 lg:px-8">
        <div className="max-w-2xl mx-auto px-4 lg:max-w-4xl lg:px-0">
          <h1 className="text-2xl font-extrabold tracking-tight text-gray-900 sm:text-3xl">
            Order Detail
          </h1>
        </div>
      </div>

      <div className="mt-16">
        <div className="max-w-7xl mx-auto sm:px-2 lg:px-8">
          <div className="max-w-2xl mx-auto space-y-8 sm:px-4 lg:max-w-4xl lg:px-0">
            <div className="bg-white border-t border-b border-gray-200 shadow-sm sm:rounded-lg sm:border">
              <div className="flex items-center p-4 border-b border-gray-200 sm:p-6 sm:grid sm:grid-cols-4 sm:gap-x-6">
                <dl className="flex-1 grid grid-cols-2 gap-x-6 text-sm sm:col-span-3 sm:grid-cols-4 lg:col-span-4">
                  <div>
                    <dt className="font-medium text-gray-900">Order ID</dt>
                    <dd className="mt-1 text-gray-500">
                      {order.order_id.substring(0, 8)}
                    </dd>
                  </div>
                  <div className="hidden sm:block">
                    <dt className="font-medium text-gray-900">Date placed</dt>
                    <dd className="mt-1 text-gray-500">
                      <time dateTime={order.order_timestamp}>
                        {DateTime.fromISO(order.order_timestamp).toLocaleString(
                          DateTime.DATETIME_MED
                        )}
                      </time>
                    </dd>
                  </div>
                  <div>
                    <dt className="font-medium text-gray-900">Order Status</dt>
                    <dd className="mt-1 text-gray-500">{order.order_status}</dd>
                  </div>
                  <div>
                    <dt className="font-medium text-gray-900">Total amount</dt>
                    <dd className="mt-1 font-medium text-gray-900">
                      {order.order_total}
                    </dd>
                  </div>
                </dl>
              </div>
              <ul className="divide-y divide-gray-200">
                {order.product_list.map((product) => (
                  <li key={product.product_id} className="p-4 sm:p-6">
                    <div className="flex items-center sm:items-start">
                      <div className="flex-1 text-sm">
                        <div className="font-medium text-gray-900 sm:flex sm:justify-between">
                          <h5>{product.product_name}</h5>
                          <p className="mt-2 sm:mt-0">
                            {product.product_price}
                          </p>
                        </div>
                        <p className="hidden text-gray-500 sm:block sm:mt-2">
                          Quantity: {product.product_qty}
                        </p>
                      </div>
                    </div>
                  </li>
                ))}
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

OrderDetail.propTypes = {};

OrderDetail.defaultProps = {};

export default OrderDetail;
