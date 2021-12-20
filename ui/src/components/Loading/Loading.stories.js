import React from "react";
import Loading from "./Loading";

export default {
  title: "Components/Loading",
  component: Loading,
};

const Template = (args) => <Loading {...args} />;

export const Primary = Template.bind({});
Primary.args = {};
