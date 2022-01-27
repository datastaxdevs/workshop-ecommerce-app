import React from "react";
import Cart from "./Cart";

export default {
  title: "Components/Cart",
  component: Cart,
};

const Template = (args) => <Cart {...args} />;

export const Primary = Template.bind({});
Primary.args = {};
