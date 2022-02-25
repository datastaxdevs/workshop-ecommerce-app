import "@testing-library/jest-dom";
import { render } from "@testing-library/react";
import User from "./User";

describe("User", () => {
  it("renders without crashing", () => {
    render(<User />);
  });
});
