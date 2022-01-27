import React, { Fragment } from "react";
import { Popover, Transition } from "@headlessui/react";
import { useAllCategories } from "../../hooks";
import { Link } from "react-router-dom";
import {
  SearchIcon,
  ShoppingBagIcon,
  UserIcon,
} from "@heroicons/react/outline";
import { navigation } from "../../config";
import { classNames } from "../../utils";

const Navigation = () => {
  const { categories } = useAllCategories();

  return (
    <header className="relative bg-white">
      <nav aria-label="Top" className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="border-b border-gray-200">
          <div className="h-16 flex items-center justify-between">
            <div className="flex-1 flex items-center lg:hidden">
              <a
                href="/"
                className="ml-2 p-2 text-gray-400 hover:text-gray-500"
              >
                <span className="sr-only">Search</span>
                <SearchIcon className="w-6 h-6" aria-hidden="true" />
              </a>
            </div>

            {/* Flyout menus */}
            <Popover.Group className="hidden lg:flex-1 lg:block lg:self-stretch">
              <div className="h-full flex space-x-8">
                {navigation.categories.map((category) => (
                  <Popover key={category.name} className="flex">
                    {({ open }) => (
                      <Fragment>
                        <div className="relative flex">
                          <Popover.Button
                            className={classNames(
                              open
                                ? "text-indigo-600"
                                : "text-gray-700 hover:text-gray-800",
                              "relative z-10 flex items-center justify-center transition-colors ease-out duration-200 text-sm font-medium"
                            )}
                          >
                            {category.name}
                            <span
                              className={classNames(
                                open ? "bg-indigo-600" : "",
                                "absolute bottom-0 inset-x-0 h-0.5 transition-colors ease-out duration-200 sm:mt-5 sm:transform sm:translate-y-px"
                              )}
                              aria-hidden="true"
                            />
                          </Popover.Button>
                        </div>

                        <Transition
                          as={Fragment}
                          enter="transition ease-out duration-200"
                          enterFrom="opacity-0"
                          enterTo="opacity-100"
                          leave="transition ease-in duration-150"
                          leaveFrom="opacity-100"
                          leaveTo="opacity-0"
                        >
                          <Popover.Panel className="absolute z-10 top-full inset-x-0">
                            {/* Presentational element used to render the bottom shadow, if we put the shadow on the actual panel it pokes out the top, so we use this shorter element to hide the top of the shadow */}
                            <div
                              className="absolute inset-0 top-1/2 bg-white shadow"
                              aria-hidden="true"
                            />

                            <div className="relative bg-white">
                              <div className="max-w-7xl mx-auto px-8">
                                <div className="grid grid-cols-2 gap-y-10 gap-x-8 py-16">
                                  <div className="grid grid-rows-1 grid-cols-2 gap-8 text-sm">
                                    {category.featured.map((item, itemIdx) => (
                                      <div
                                        key={item.name}
                                        className={classNames(
                                          itemIdx === 0
                                            ? "col-span-2 aspect-w-2"
                                            : "",
                                          "group relative aspect-w-1 aspect-h-1 rounded-md bg-gray-100 overflow-hidden"
                                        )}
                                      >
                                        <img
                                          src={item.imageSrc}
                                          alt={item.imageAlt}
                                          className="object-center object-cover group-hover:opacity-75"
                                        />
                                        <div className="flex flex-col justify-end">
                                          <div className="p-4 bg-white bg-opacity-60 text-sm">
                                            <a
                                              href={item.href}
                                              className="font-medium text-gray-900"
                                            >
                                              <span
                                                className="absolute inset-0"
                                                aria-hidden="true"
                                              />
                                              {item.name}
                                            </a>
                                            <p
                                              aria-hidden="true"
                                              className="mt-0.5 text-gray-700 sm:mt-1"
                                            >
                                              Shop now
                                            </p>
                                          </div>
                                        </div>
                                      </div>
                                    ))}
                                  </div>
                                  <div className="grid grid-cols-3 gap-y-10 gap-x-8 text-sm text-gray-500">
                                    {categories &&
                                      categories.map((category) => (
                                        <div key={category.categoryId}>
                                          <p className="font-medium text-gray-900">
                                            {category.name}
                                          </p>
                                          <ul className="mt-4 space-y-4">
                                            {category.children.map((child) => (
                                              <li
                                                key={child.name}
                                                className="flex"
                                              >
                                                <Popover.Button
                                                  as={Link}
                                                  to={`/categories/${child.categoryId}/${child.name}`}
                                                  className="hover:text-gray-800"
                                                >
                                                  {child.name}
                                                </Popover.Button>
                                              </li>
                                            ))}
                                          </ul>
                                        </div>
                                      ))}
                                  </div>
                                </div>
                              </div>
                            </div>
                          </Popover.Panel>
                        </Transition>
                      </Fragment>
                    )}
                  </Popover>
                ))}

                {navigation.pages.map((page) => (
                  <a
                    key={page.name}
                    href={page.href}
                    className="flex items-center text-sm font-medium text-gray-700 hover:text-gray-800"
                  >
                    {page.name}
                  </a>
                ))}
              </div>
            </Popover.Group>

            {/* Logo */}
            <Link to="/" className="flex">
              <span className="sr-only">Astra E-Commerce</span>
              <img className="h-8 w-auto" src="/favicon.ico" alt="" />
            </Link>

            <div className="flex-1 flex items-center justify-end">
              <a
                href="/"
                className="hidden text-gray-700 hover:text-gray-800 lg:flex lg:items-center"
              >
                <img
                  src="/images/flag-usa.svg"
                  alt=""
                  className="w-6 h-auto block flex-shrink-0"
                />
                <span className="ml-3 block text-sm font-medium">USD</span>
                <span className="sr-only">, change currency</span>
              </a>

              {/* Search */}
              <a
                href="/"
                className="hidden ml-6 p-2 text-gray-400 hover:text-gray-500 lg:block"
              >
                <span className="sr-only">Search</span>
                <SearchIcon className="w-6 h-6" aria-hidden="true" />
              </a>

              {/* Account */}
              <a
                href="/"
                className="p-2 text-gray-400 hover:text-gray-500 lg:ml-4"
              >
                <span className="sr-only">Account</span>
                <UserIcon className="w-6 h-6" aria-hidden="true" />
              </a>

              {/* Cart */}
              <div className="ml-4 flow-root lg:ml-6">
                <Link to="/cart" className="group -m-2 p-2 flex items-center">
                  <ShoppingBagIcon
                    className="flex-shrink-0 h-6 w-6 text-gray-400 group-hover:text-gray-500"
                    aria-hidden="true"
                  />
                  <span className="sr-only">items in cart, view bag</span>
                </Link>
              </div>
            </div>
          </div>
        </div>
      </nav>
    </header>
  );
};

Navigation.propTypes = {};

Navigation.defaultProps = {};

export default Navigation;
