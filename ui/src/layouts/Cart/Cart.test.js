import "@testing-library/jest-dom";
import { render } from "@testing-library/react";
import Cart from "./Cart";

describe("Cart", () => {
  it("renders without crashing", () => {
    render(<Cart />);
  });
});
