import React from "react";
import ProductDetail from "./ProductDetail";

export default {
  title: "Components/ProductDetail",
  component: ProductDetail,
};

const Template = (args) => <ProductDetail {...args} />;

export const Primary = Template.bind({});
Primary.args = {};
