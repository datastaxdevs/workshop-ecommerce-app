import React from "react";
import Signup from "./Signup";

export default {
  title: "Components/Signup",
  component: Signup,
};

const Template = (args) => <Signup {...args} />;

export const Primary = Template.bind({});
Primary.args = {};
