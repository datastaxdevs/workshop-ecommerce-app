import _ from "lodash";

export const fetcher = (url) => fetch(url).then((res) => res.json());

const TOP_LEVEL_CATEGORY_UUID = "ffdac25a-0244-4894-bb31-a0884bc82aa9";

export const getCategories = async () => {
  const response = await fetch(`/api/v1/categories/${TOP_LEVEL_CATEGORY_UUID}`);
  return await response.json();
};

export const getProducts = async (categoryId) => {
  const response = await fetch(
    `/api/v1/categories/${TOP_LEVEL_CATEGORY_UUID}/${categoryId}`
  );
  return await response.json();
};

export const getProduct = async (productId) => {
  const response = await fetch(`/api/v1/products/product/${productId}`);
  return await response.json();
};

// // Mock Endpoints
//
// export const getProducts = async () => {
//   const response = await fetch("/mocks/products.json");
//   const data = await response.json();
//   return data.data;
// };
//
// export const getProduct = async (productId) => {
//   const response = await fetch("/mocks/products.json");
//   const data = await response.json();
//   return _.find(data.data, { product_id: productId });
// };
