import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import ProductList from "../ProductList";
import ProductDetail from "../ProductDetail";
import ShoppingCart from "../ShoppingCart";
import Navigation from "../../components/Navigation";
import Footer from "../../components/Footer";

const App = () => {
  return (
    <BrowserRouter>
      <Navigation />
      <Routes>
        <Route path="/" element={<ProductList />} />
        <Route path="/products" element={<ProductList />} />
        <Route path="/products/:productId" element={<ProductDetail />} />
        <Route path="/cart" element={<ShoppingCart />} />
      </Routes>
      <Footer />
    </BrowserRouter>
  );
};

App.propTypes = {};

App.defaultProps = {};

export default App;
