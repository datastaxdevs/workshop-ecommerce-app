import "@testing-library/jest-dom";
import { render } from "@testing-library/react";
import SignIn from "./SignIn";

describe("SignIn", () => {
  it("renders without crashing", () => {
    render(<SignIn />);
  });
});
