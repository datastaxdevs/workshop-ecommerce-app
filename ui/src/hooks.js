import { useState, useEffect } from "react";
import useSWR from "swr";
import { v4 as uuidv4 } from "uuid";
import _ from "lodash";

const TOP_LEVEL_CATEGORY_UUID = "ffdac25a-0244-4894-bb31-a0884bc82aa9";
const FEATURED_CATEGORY_ID = "202112";

const fetcher = (url) => fetch(url).then((res) => res.json());
let categoryCache = null;

export const useAllCategories = () => {
  const [categories, setCategories] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error] = useState(null);

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

export const useCategory = (parentId, categoryId) => {
  return useSWR(
    `/api/v1/categories/category/${parentId}/${categoryId}`,
    fetcher
  );
};

export const useProduct = (parentId, categoryId) => {
  const [product, setProduct] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      const category = await fetcher(
        `/api/v1/categories/category/${parentId}/${categoryId}`
      );
      if (category.products) {
        const products = await Promise.all(
          category.products.map((productId) =>
            fetcher(`/api/v1/products/product/${productId}`)
          )
        );
        const prices = await Promise.all(
          category.products.map((productId) =>
            fetcher(`/api/v1/prices/price/${productId}`)
          )
        );
        category.products = products.map((product) => {
          product.price = _.find(prices, { product_id: product.product_id });
          return product;
        });
      }
      setProduct(category);
      setLoading(false);
    };
    fetchData();
  }, [categoryId, parentId]);

  return { product, loading, error };
};

export const useCart = (cartId) => {
  const [cart, setCart] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      const newCart = await fetcher(`/api/v1/carts/${cartId}/`);
      if (newCart.length) {
        const cartItems = await Promise.all(
          newCart.map((cartItem) =>
            fetcher(`/api/v1/products/product/${cartItem.product_id}`)
          )
        );
        const prices = await Promise.all(
          newCart.map((cartItem) =>
            fetcher(`/api/v1/prices/price/${cartItem.product_id}`)
          )
        );
        cartItems.forEach((cartItem) => {
          cartItem.price = _.find(prices, { product_id: cartItem.product_id });
        });
        newCart.forEach((cartItem) => {
          cartItem.product = _.find(cartItems, {
            product_id: cartItem.product_id,
          });
        });
      }
      setCart(newCart);
      setLoading(false);
    };
    fetchData();
  }, [cartId]);

  return { cart, loading, error, setCart };
};

export const useCartId = () => {
  let cartId = localStorage.getItem("cartId");
  if (!cartId) {
    cartId = uuidv4();
    localStorage.setItem("cartId", cartId);
  }
  return cartId;
};
