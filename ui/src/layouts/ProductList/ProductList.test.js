import "@testing-library/jest-dom";
import { render } from "@testing-library/react";
import ProductList from "./ProductList";

describe("ProductList", () => {
  it("renders without crashing", () => {
    render(<ProductList />);
  });
});
