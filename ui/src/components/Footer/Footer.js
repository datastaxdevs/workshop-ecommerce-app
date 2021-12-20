import React from "react";
import { Link } from "react-router-dom";
import { useAllCategories } from "../../hooks";

const Footer = () => {
  const { categories } = useAllCategories();

  return (
    <footer aria-labelledby="footer-heading">
      <h2 id="footer-heading" className="sr-only">
        Footer
      </h2>
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="border-t border-gray-200 py-20">
          <div className="grid grid-cols-1 md:grid-cols-12 md:grid-flow-col md:gap-x-8 md:gap-y-16 md:auto-rows-min">
            {/* Image section */}
            <div className="col-span-1 md:col-span-2 lg:row-start-1 lg:col-start-1">
              <img src="/favicon.ico" alt="" className="h-8 w-auto" />
            </div>

            {/* Sitemap sections */}
            <div className="mt-10 col-span-11 grid grid-cols-4 gap-8 sm:grid-cols-4 md:mt-0 md:row-start-1 md:col-start-3 md:col-span-11 lg:col-start-2 lg:col-span-11">
              {categories &&
                categories.map((parent) => (
                  <div key={parent.categoryId}>
                    <h3 className="text-sm font-medium text-gray-900">
                      {parent.name}
                    </h3>
                    <ul className="mt-6 space-y-6">
                      {parent.children.map((child) => (
                        <li key={child.name} className="text-sm">
                          <Link
                            to={`/categories/${child.categoryId}/${child.name}`}
                            className="text-gray-500 hover:text-gray-600"
                          >
                            {child.name}
                          </Link>
                        </li>
                      ))}
                    </ul>
                  </div>
                ))}
            </div>
          </div>
        </div>

        <div className="border-t border-gray-100 py-10 text-center">
          <p className="text-sm text-gray-500">
            &copy; {new Date().getFullYear()} Astra E-Commerce, Inc. All rights
            reserved.
          </p>
        </div>
      </div>
    </footer>
  );
};

Footer.propTypes = {};

Footer.defaultProps = {};

export default Footer;
