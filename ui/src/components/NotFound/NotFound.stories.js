import React from "react";
import NotFound from "./NotFound";

export default {
  title: "Components/NotFound",
  component: NotFound,
};

const Template = (args) => <NotFound {...args} />;

export const Primary = Template.bind({});
Primary.args = {};
