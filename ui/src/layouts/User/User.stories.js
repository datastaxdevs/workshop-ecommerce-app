import React from "react";
import User from "./User";

export default {
  title: "Components/User",
  component: User,
};

const Template = (args) => <User {...args} />;

export const Primary = Template.bind({});
Primary.args = {};
