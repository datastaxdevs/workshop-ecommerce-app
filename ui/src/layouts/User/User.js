import React from "react";
import { Formik, Field, Form, ErrorMessage } from "formik";
import * as Yup from "yup";
import toast from "react-hot-toast";
import { useCartId, useCurrentUser } from "../../hooks";
import _ from "lodash";

const User = () => {
  const { data: currentUser } = useCurrentUser();
  const cartId = useCartId();

  return (
    <div className="max-w-2xl mx-auto py-16 px-4 sm:py-24 sm:px-6 lg:px-0">
      <Formik
        initialValues={{
          first_name: _.get(currentUser, "first_name", ""),
          last_name: _.get(currentUser, "last_name", ""),
          user_email: _.get(currentUser, "user_email", ""),
          addresses: [
            {
              street: _.get(currentUser, "addresses[0].street", ""),
              city: _.get(currentUser, "addresses[0].city", ""),
              country: _.get(currentUser, "addresses[0].country", ""),
              state_province: _.get(
                currentUser,
                "addresses[0].state_province",
                ""
              ),
              postal_code: _.get(currentUser, "addresses[0].postal_code", ""),
            },
          ],
        }}
        validationSchema={Yup.object({
          user_email: Yup.string()
            .email("Invalid email address")
            .required("Required"),
        })}
        onSubmit={async (values, { setSubmitting }) => {
          try {
            const res = await fetch(`/api/v1/users/${cartId}/update`, {
              method: "PUT",
              body: JSON.stringify(values),
            });
            const resJson = await res.json();
            console.log(resJson);
            setSubmitting(false);
            if (!res.ok) {
              return toast.error("Could not update profile.");
            }
            toast.success("Updated your profile!");
          } catch (e) {
            console.error(e);
            toast.error("Could not update profile.");
          }
        }}
      >
        <Form>
          <div className="space-y-8 divide-y divide-gray-200">
            <div className="pt-8">
              <div>
                <h3 className="text-lg leading-6 font-medium text-gray-900">
                  Personal Information
                </h3>
                <p className="mt-1 text-sm text-gray-500">
                  Use a permanent address where you can receive mail.
                </p>
              </div>
              <div className="mt-6 grid grid-cols-1 gap-y-6 gap-x-4 sm:grid-cols-6">
                <div className="sm:col-span-3">
                  <label
                    htmlFor="first_name"
                    className="block text-sm font-medium text-gray-700"
                  >
                    First name
                  </label>
                  <div className="mt-1">
                    <Field
                      name="first_name"
                      type="text"
                      className="shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md"
                    />
                    <ErrorMessage
                      name="first_name"
                      component="div"
                      className="block text-sm font-medium text-red-700"
                    />
                  </div>
                </div>

                <div className="sm:col-span-3">
                  <label
                    htmlFor="last_name"
                    className="block text-sm font-medium text-gray-700"
                  >
                    Last name
                  </label>
                  <div className="mt-1">
                    <Field
                      name="last_name"
                      type="text"
                      className="shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md"
                    />
                    <ErrorMessage
                      name="last_name"
                      component="div"
                      className="block text-sm font-medium text-red-700"
                    />
                  </div>
                </div>

                <div className="sm:col-span-4">
                  <label
                    htmlFor="user_email"
                    className="block text-sm font-medium text-gray-700"
                  >
                    Email address
                  </label>
                  <div className="mt-1">
                    <Field
                      name="user_email"
                      type="email"
                      className="shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md"
                    />
                    <ErrorMessage
                      name="user_email"
                      component="div"
                      className="block text-sm font-medium text-red-700"
                    />
                  </div>
                </div>

                <div className="sm:col-span-3">
                  <label
                    htmlFor="addresses[0].country"
                    className="block text-sm font-medium text-gray-700"
                  >
                    Country
                  </label>
                  <div className="mt-1">
                    <Field
                      name="addresses[0].country"
                      type="text"
                      className="shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md"
                    />
                    <ErrorMessage
                      name="addresses[0].country"
                      component="div"
                      className="block text-sm font-medium text-red-700"
                    />
                  </div>
                </div>

                <div className="sm:col-span-6">
                  <label
                    htmlFor="addresses[0].street"
                    className="block text-sm font-medium text-gray-700"
                  >
                    Street address
                  </label>
                  <div className="mt-1">
                    <Field
                      name="addresses[0].street"
                      type="text"
                      className="shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md"
                    />
                    <ErrorMessage
                      name="addresses[0].street"
                      component="div"
                      className="block text-sm font-medium text-red-700"
                    />
                  </div>
                </div>

                <div className="sm:col-span-2">
                  <label
                    htmlFor="addresses[0].city"
                    className="block text-sm font-medium text-gray-700"
                  >
                    City
                  </label>
                  <div className="mt-1">
                    <Field
                      name="addresses[0].city"
                      type="text"
                      className="shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md"
                    />
                    <ErrorMessage
                      name="addresses[0].city"
                      component="div"
                      className="block text-sm font-medium text-red-700"
                    />
                  </div>
                </div>

                <div className="sm:col-span-2">
                  <label
                    htmlFor="addresses[0].state_province"
                    className="block text-sm font-medium text-gray-700"
                  >
                    State / Province
                  </label>
                  <div className="mt-1">
                    <Field
                      name="addresses[0].state_province"
                      type="text"
                      className="shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md"
                    />
                    <ErrorMessage
                      name="addresses[0].state_province"
                      component="div"
                      className="block text-sm font-medium text-red-700"
                    />
                  </div>
                </div>

                <div className="sm:col-span-2">
                  <label
                    htmlFor="addresses[0].postal_code"
                    className="block text-sm font-medium text-gray-700"
                  >
                    ZIP / Postal code
                  </label>
                  <div className="mt-1">
                    <Field
                      name="addresses[0].postal_code"
                      type="text"
                      className="shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md"
                    />
                    <ErrorMessage
                      name="addresses[0].postal_code"
                      component="div"
                      className="block text-sm font-medium text-red-700"
                    />
                  </div>
                </div>
              </div>
            </div>
            <div className="pt-5">
              <div className="flex justify-end">
                <button
                  type="submit"
                  className="ml-3 inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                >
                  Save
                </button>
              </div>
            </div>
          </div>
        </Form>
      </Formik>
      <Formik
        initialValues={{
          password: "",
          newPassword: "",
          confirmNewPassword: "",
        }}
        validationSchema={Yup.object({
          password: Yup.string().required("Required"),
          newPassword: Yup.string().required("Required"),
          confirmNewPassword: Yup.string()
            .oneOf([Yup.ref("password"), null], "Passwords must match")
            .required("Required"),
        })}
        onSubmit={async (values, { setSubmitting }) => {
          try {
            const res = await fetch(`/api/v1/users/${cartId}/updatepassword`, {
              method: "POST",
              body: JSON.stringify(values),
            });
            const resJson = await res.json();
            console.log(resJson);
            setSubmitting(false);
            if (!res.ok) {
              return toast.error("Could not update password.");
            }
            toast.success("Updated your password!");
          } catch (e) {
            console.error(e);
            toast.error("Could not update password.");
          }
        }}
      >
        <Form>
          <div className="space-y-8 divide-y divide-gray-200">
            <div className="pt-8">
              <div>
                <h3 className="text-lg leading-6 font-medium text-gray-900">
                  Update password
                </h3>
                <p className="mt-1 text-sm text-gray-500">
                  Keep your password safe.
                </p>
              </div>
              <div className="mt-6 grid grid-cols-1 gap-y-6 gap-x-4 sm:grid-cols-6">
                <div className="sm:col-span-4">
                  <label
                    htmlFor="email"
                    className="block text-sm font-medium text-gray-700"
                  >
                    Old Password
                  </label>
                  <div className="mt-1">
                    <Field
                      name="password"
                      type="password"
                      className="shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md"
                    />
                    <ErrorMessage
                      name="password"
                      component="div"
                      className="block text-sm font-medium text-red-700"
                    />
                  </div>
                </div>
                <div className="sm:col-span-4">
                  <label
                    htmlFor="email"
                    className="block text-sm font-medium text-gray-700"
                  >
                    New Password
                  </label>
                  <div className="mt-1">
                    <Field
                      name="newPassword"
                      type="password"
                      className="shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md"
                    />
                    <ErrorMessage
                      name="newPassword"
                      component="div"
                      className="block text-sm font-medium text-red-700"
                    />
                  </div>
                </div>
                <div className="sm:col-span-4">
                  <label
                    htmlFor="email"
                    className="block text-sm font-medium text-gray-700"
                  >
                    Confirm New Password
                  </label>
                  <div className="mt-1">
                    <Field
                      name="confirmNewPassword"
                      type="password"
                      className="shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md"
                    />
                    <ErrorMessage
                      name="confirmNewPassword"
                      component="div"
                      className="block text-sm font-medium text-red-700"
                    />
                  </div>
                </div>
              </div>
            </div>
            <div className="pt-5">
              <div className="flex justify-end">
                <button
                  type="submit"
                  className="ml-3 inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                >
                  Save
                </button>
              </div>
            </div>
          </div>
        </Form>
      </Formik>
    </div>
  );
};

User.propTypes = {};

User.defaultProps = {};

export default User;
