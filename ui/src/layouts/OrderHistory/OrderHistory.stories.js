import React from "react";
import OrderHistory from "./OrderHistory";

export default {
  title: "Components/OrderHistory",
  component: OrderHistory,
};

const Template = (args) => <OrderHistory {...args} />;

export const Primary = Template.bind({});
Primary.args = {};
