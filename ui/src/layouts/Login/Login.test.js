import "@testing-library/jest-dom";
import { render } from "@testing-library/react";
import Login from "./Login";

describe("Login", () => {
  it("renders without crashing", () => {
    render(<Login />);
  });
});
