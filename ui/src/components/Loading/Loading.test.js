import "@testing-library/jest-dom";
import { render } from "@testing-library/react";
import Loading from "./Loading";

describe("Loading", () => {
  it("renders without crashing", () => {
    render(<Loading />);
  });
});
