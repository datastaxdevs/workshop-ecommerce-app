import "@testing-library/jest-dom";
import { render } from "@testing-library/react";
import NotFound from "./NotFound";

describe("NotFound", () => {
  it("renders without crashing", () => {
    render(<NotFound />);
  });
});
