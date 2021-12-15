import React from "react";
import ProductList from "./ProductList";

export default {
  title: "Components/ProductList",
  component: ProductList,
};

const Template = (args) => <ProductList {...args} />;

export const Primary = Template.bind({});
Primary.args = {};
