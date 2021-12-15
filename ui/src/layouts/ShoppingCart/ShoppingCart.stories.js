import React from "react";
import ShoppingCart from "./ShoppingCart";

export default {
  title: "Components/ShoppingCart",
  component: ShoppingCart,
};

const Template = (args) => <ShoppingCart {...args} />;

export const Primary = Template.bind({});
Primary.args = {};
