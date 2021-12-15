import "@testing-library/jest-dom";
import { render } from "@testing-library/react";
import ProductDetail from "./ProductDetail";

describe("ProductDetail", () => {
  it("renders without crashing", () => {
    render(<ProductDetail />);
  });
});
