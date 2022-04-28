import React from "react";
import OrderDetail from "./OrderDetail";

export default {
  title: "Components/OrderDetail",
  component: OrderDetail,
};

const Template = (args) => <OrderDetail {...args} />;

export const Primary = Template.bind({});
Primary.args = {};
