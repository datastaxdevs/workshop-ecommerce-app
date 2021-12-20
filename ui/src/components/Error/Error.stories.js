import React from "react";
import Error from "./Error";

export default {
  title: "Components/Error",
  component: Error,
};

const Template = (args) => <Error {...args} />;

export const Primary = Template.bind({});
Primary.args = {};
