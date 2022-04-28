import "@testing-library/jest-dom";
import { render } from "@testing-library/react";
import OrderDetail from "./OrderDetail";

describe("OrderDetail", () => {
  it("renders without crashing", () => {
    render(<OrderDetail />);
  });
});
