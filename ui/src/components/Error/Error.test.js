import "@testing-library/jest-dom";
import { render } from "@testing-library/react";
import Error from "./Error";

describe("Error", () => {
  it("renders without crashing", () => {
    render(<Error />);
  });
});
