import "@testing-library/jest-dom";
import { render } from "@testing-library/react";
import Signup from "./Signup";

describe("Signup", () => {
  it("renders without crashing", () => {
    render(<Signup />);
  });
});
