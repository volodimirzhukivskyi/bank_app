import React from "react";
import { Formik } from "formik";
import * as yup from "yup";
import styles from "./FormWithDrawMoney.module.css";
import { useDispatch, useSelector } from "react-redux";

import { withDrawMoney } from "../../../helpers/http/account";

const FormWithDrawMoney = ({ click }) => {
  const validationSchema = yup.object().shape({
    number: yup
      .string()
      .min(2, "Слишком короткий номер!")
      .max(50, "Слишком длинный номер!")
      .required("Обязательно"),

    balance: yup
      .number()
      .typeError("Этот текст не является числом")
      .required("Обязательно"),
  });
  return (
    <>
      <Formik
        initialValues={{
          number: "",
          balance: "",
        }}
        validateOnBlur
        onSubmit={(values, { resetForm }) => {
          withDrawMoney(values).then((response) => {
            click(
              response.data
                ? "Деньги успешно сняты  "
                : "Проверти данные или посмотрите на количество денег на счету"
            );
          });

          resetForm({
            number: "",
            balance: "",
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
                    placeholder="Number"
                    name={"number"}
                    onChange={handleChange}
                    onBlur={handleBlur}
                    value={values.number}
                  />{" "}
                  {touched.number && errors.number && (
                    <p className="error">{errors.number}</p>
                  )}
                </div>

                <div className="form-field">
                  <input
                    className={styles.input}
                    type="number"
                    placeholder="Balance"
                    name={"balance"}
                    onChange={handleChange}
                    onBlur={handleBlur}
                    value={values.balance}
                  />
                  {touched.balance && errors.balance && (
                    <p className="error">{errors.balance}</p>
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
export default FormWithDrawMoney;
