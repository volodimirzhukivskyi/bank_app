import React from "react";
import { Field, Formik } from "formik";
import * as yup from "yup";
import styles from "./FormAccount.module.css";
import { useDispatch, useSelector } from "react-redux";
import { createAccount } from "../../../helpers/http/account";
import {
  clearItem,
  isReload,
} from "../../../Redux/Reducer/AppReducer/appActions";
import { uuidv4 } from "../../../helpers/func";

const FormAccount = ({ text, customerId }) => {
  const { reload } = useSelector((store) => ({
    reload: store.isReload,
  }));
  const dispatch = useDispatch();
  const validationSchema = yup.object().shape({
    currency: yup
      .string()
      .min(2, "Слишком короткий email!")
      .max(50, "Слишком длинный email!")
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
          currency: "",
          balance: "",
        }}
        validateOnBlur
        onSubmit={(values, actions) => {
          createAccount({
            ...values,
            number: uuidv4(),
            customer: { id: customerId },
          })
            .then((res) => {
              if (res.status === 200) {
                dispatch(isReload(!reload));
                dispatch(clearItem());
                text();
              }
            })
            .catch((e) => {
              console.log(e);
            });

          actions.resetForm({
            values: {
              currency: "",
              balance: "",
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
            <form
              className={styles.form}
              action="src/components/FormShop/FormShop"
            >
              <div className="form-fields">
                <div className="form-field">
                  <Field className={styles.input} name="currency" as="select">
                    <option value="Выбрать">Выбрать</option>
                    <option value="EUR">EUR</option>
                    <option value="UAH">UAH</option>
                    <option value="USD">USD</option>
                    <option value="CHF">CHF</option>
                    <option value="GBP">GBP</option>
                  </Field>
                </div>
                <div className="form-field">
                  <input
                    className={styles.input}
                    type="number"
                    placeholder="balance"
                    name={"balance"}
                    onChange={handleChange}
                    onBlur={handleBlur}
                    value={values.balance}
                  />
                  {touched.balance && errors.balance && (
                    <p className={styles.error}>{errors.balance}</p>
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
                  Создать Аккаунт
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
export default FormAccount;
