import "@testing-library/jest-dom";
import { render } from "@testing-library/react";
import OrderHistory from "./OrderHistory";

describe("OrderHistory", () => {
  it("renders without crashing", () => {
    render(<OrderHistory />);
  });
});
