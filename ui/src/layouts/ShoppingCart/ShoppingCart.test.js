import "@testing-library/jest-dom";
import { render } from "@testing-library/react";
import ShoppingCart from "./ShoppingCart";

describe("ShoppingCart", () => {
  it("renders without crashing", () => {
    render(<ShoppingCart />);
  });
});
