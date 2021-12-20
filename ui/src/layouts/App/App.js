import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import ProductList from "../ProductList";
import ProductDetail from "../ProductDetail";
import Navigation from "../../components/Navigation";
import Footer from "../../components/Footer";

const App = () => {
  return (
    <BrowserRouter>
      <Navigation />
      <Routes>
        <Route path="/" element={<ProductList />} />
        <Route
          path="/categories/:categoryId/:categoryName"
          element={<ProductList />}
        />
        <Route
          path="/products/:parentId/:categoryId"
          element={<ProductDetail />}
        />
      </Routes>
      <Footer />
    </BrowserRouter>
  );
};

App.propTypes = {};

App.defaultProps = {};

export default App;
