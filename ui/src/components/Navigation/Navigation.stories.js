import React from "react";
import Navigation from "./Navigation";

export default {
  title: "Components/Navigation",
  component: Navigation,
};

const Template = (args) => <Navigation {...args} />;

export const Primary = Template.bind({});
Primary.args = {};
