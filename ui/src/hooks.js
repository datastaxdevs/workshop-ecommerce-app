import { useState, useEffect } from "react";
import useSWR from "swr";
import _ from "lodash";

const TOP_LEVEL_CATEGORY_UUID = "ffdac25a-0244-4894-bb31-a0884bc82aa9";
const FEATURED_CATEGORY_ID = "202112";

const fetcher = (url) => fetch(url).then((res) => res.json());
let categoryCache = null;

export const useAllCategories = () => {
  const [categories, setCategories] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      if (categoryCache) {
        setCategories(categoryCache);
        setLoading(false);
        return;
      }
      const parents = await fetcher(
        `/api/v1/categories/${TOP_LEVEL_CATEGORY_UUID}`
      );
      const children = await Promise.all(
        parents.map((category) =>
          fetcher(`/api/v1/categories/${category.categoryId}`)
        )
      );
      children.forEach((childrenCategories) =>
        childrenCategories.forEach((child) => {
          const parent = _.find(parents, { categoryId: child.parentId });
          parent.children = parent.children ?? [];
          parent.children.push(child);
        })
      );
      categoryCache = parents;
      setCategories(parents);
      setLoading(false);
    };
    fetchData();
  }, []);

  return { categories, loading, error };
};

export const useCategories = (category = TOP_LEVEL_CATEGORY_UUID) => {
  return useSWR(`/api/v1/categories/${category}`, fetcher);
};

export const useProducts = (categoryId) => {
  const path = categoryId
    ? `/api/v1/categories/${categoryId}`
    : `/api/v1/featured/${FEATURED_CATEGORY_ID}`;
  return useSWR(path, fetcher);
};

export const useProduct = (productId) => {
  return useSWR(`/api/v1/products/product/${productId}`, fetcher);
};
