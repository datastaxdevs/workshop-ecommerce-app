import "@testing-library/jest-dom";
import { render } from "@testing-library/react";
import Navigation from "./Navigation";

describe("Navigation", () => {
  it("renders without crashing", () => {
    render(<Navigation />);
  });
});
