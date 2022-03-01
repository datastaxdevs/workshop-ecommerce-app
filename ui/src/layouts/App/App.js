import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import ProductList from "../ProductList";
import Cart from "../Cart";
import Login from "../Login";
import User from "../User";
import Signup from "../Signup";
import ProductDetail from "../ProductDetail";
import Navigation from "../../components/Navigation";
import Footer from "../../components/Footer";
import { Toaster } from "react-hot-toast";

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
        <Route path="/cart" element={<Cart />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/user" element={<User />} />
      </Routes>
      <Footer />
      <Toaster />
    </BrowserRouter>
  );
};

App.propTypes = {};

App.defaultProps = {};

export default App;
