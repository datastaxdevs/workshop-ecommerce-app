import useSWR from "swr";

const TOP_LEVEL_CATEGORY_UUID = "ffdac25a-0244-4894-bb31-a0884bc82aa9";
const FEATURED_CATEGORY_ID = "202112";

const fetcher = (url) => fetch(url).then((res) => res.json());

export const useCategories = () => {
  return useSWR(`/api/v1/categories/${TOP_LEVEL_CATEGORY_UUID}`, fetcher);
};

export const useProducts = (categoryId) => {
  //   const path = categoryId
  //     ? "/mocks/products.json"
  //     : `/api/v1/featured/${FEATURED_CATEGORY_ID}`;
  const path = "/mocks/products.json";
  return useSWR(path, fetcher);
};

export const useProduct = (productId) => {
  return useSWR(`/api/v1/products/product/${productId}`, fetcher);
};
