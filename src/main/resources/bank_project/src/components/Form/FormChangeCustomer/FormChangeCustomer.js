import React from "react";
import { Formik } from "formik";
import * as yup from "yup";
import styles from "./FormChangeCustomer.module.css";
import { useDispatch, useSelector } from "react-redux";

import {
  clearItem,
  isReload,
} from "../../../Redux/Reducer/AppReducer/appActions";
import {
  createCustomer,
  updateCustomerInfo,
} from "../../../helpers/http/customer";

const FormChangeCustomer = ({ customer, type }) => {
  const { reload } = useSelector((store) => ({
    reload: store.isReload,
  }));
  const dispatch = useDispatch();
  const validationSchema = yup.object().shape({
    name: yup
      .string()
      .matches(/^[a-zA-Z]+$/, "Это поле должно быть строкой")
      .min(2, "Слишком короткое имя!")
      .max(50, "Слишком длинное имя!")
      .required("Обязательно"),
    email: yup
      .string()
      .min(2, "Слишком короткий email!")
      .max(50, "Слишком длинный email!")
      .required("Обязательно"),
    age: yup
      .number()
      .typeError("Этот текст не является числом")
      .required("Обязательно"),
  });
  return (
    <>
      <Formik
        initialValues={{
          name: type === "update" ? customer.name : "",
          email: type === "update" ? customer.email : "",
          age: type === "update" ? customer.age : "",
        }}
        validateOnBlur
        onSubmit={(values, actions) => {
          const updateObj = { ...customer, ...values };
          const createObj = {
            ...values,
            employers: [],
            accounts: [],
          };
          type === "update"
            ? updateCustomerInfo(updateObj)
                .then((response) => {
                  if (response.status === 200) {
                    dispatch(isReload(!reload));
                    dispatch(clearItem());
                  }
                })
                .catch((error) => {
                  console.log(error);
                })
            : createCustomer(createObj)
                .then((response) => {
                  if (response.status === 200) {
                    dispatch(isReload(!reload));
                    dispatch(clearItem());
                  }
                })
                .catch((error) => {
                  console.log(error);
                });

          actions.resetForm({
            values: {
              name: "",
              email: "",
              age: "",
            },
          });
        }}
        validationSchema={validationSchema}
      >
        {({
          values,
          errors,
          touched,
          handleChange,
          isValid,
          handleSubmit,
          handleReset,
          handleBlur,
          dirty,
        }) => (
          <div
            className="register-form-container"
            onClick={(e) => e.stopPropagation()}
          >
            <form action="src/components/FormShop/FormShop">
              <div className="form-fields">
                <div className="form-field">
                  <input
                    className={styles.input}
                    type="text"
                    placeholder="Имя"
                    name={"name"}
                    onChange={handleChange}
                    onBlur={handleBlur}
                    value={values.name}
                  />{" "}
                  {touched.name && errors.name && (
                    <p className="error">{errors.name}</p>
                  )}
                </div>

                <div className="form-field">
                  <input
                    className={styles.input}
                    type="email"
                    placeholder="email"
                    name={"email"}
                    onChange={handleChange}
                    onBlur={handleBlur}
                    value={values.email}
                  />
                  {touched.email && errors.email && (
                    <p className="error">{errors.email}</p>
                  )}
                </div>
                <div className="form-field">
                  <input
                    className={styles.input}
                    type="number"
                    placeholder="Возраст пользователя"
                    name={"age"}
                    onChange={handleChange}
                    onBlur={handleBlur}
                    value={values.age}
                  />
                  {touched.age && errors.age && (
                    <p className="error">{errors.age}</p>
                  )}
                </div>
              </div>
              <div className="form-buttons">
                <button
                  className={styles.button}
                  disabled={!isValid && !dirty}
                  onClick={handleSubmit}
                  type="submit"
                >
                  Отправить заявку
                </button>
                <div className="divider"></div>
              </div>
            </form>
          </div>
        )}
      </Formik>
    </>
  );
};
export default FormChangeCustomer;
