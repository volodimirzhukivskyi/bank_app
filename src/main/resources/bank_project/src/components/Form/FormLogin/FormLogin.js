import {
  addShopsFailure,
  addShopsSuccess,
  isLoadingFunc,
} from "../../../Redux/Reducer/AppReducer/appActions";

import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getToken } from "../../../helpers/http/auth";
import { fetchCustomers } from "../../../helpers/http/customer";

const FormLogin = (props) => {
  const dispatch = useDispatch();
  const { countPagination } = useSelector((store) => ({
    countPagination: store.countPage,
  }));
  const [form, setForm] = useState({
    userName: "",
    password: "",
  });
  const { userName, password } = form;

  const onChange = (e) => {
    setForm({ ...form, [e.target.id]: e.target.value });
  };

  const onSubmit = (e) => {
    e.preventDefault();
    fetch("http://localhost:9000/authenticate", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        userName,
        password,
      }),
    })
      .then((data) => {
        setForm({ userName: "", password: "" });

        localStorage.setItem("token", data.token);
        (async function getTokenUser() {
          try {
            const token = await getToken({
              userName: "Vladimir",
              password: "password",
            });
            console.log(token);
            localStorage.setItem("token", token.data);
            const customers = await fetchCustomers(countPagination, 3);
            dispatch(addShopsSuccess(customers));
          } catch (e) {
            dispatch(addShopsFailure("Could not fetch expenses!"));
          }
          dispatch(isLoadingFunc(false));
        })();
      })
      .catch((err) => console.log(err));
  };

  return (
    <div className="container">
      <div style={{ marginTop: "4rem" }}>
        <div>
          <div style={{ paddingLeft: "11.250px" }}>
            <h4>
              <b>Login</b>
            </h4>
          </div>
          <form noValidate onSubmit={onSubmit}>
            <div>
              <input
                onChange={onChange}
                value={form.email}
                id="userName"
                type="text"
              />
              <label htmlFor="userName">userName</label>
            </div>
            <div>
              <input
                onChange={onChange}
                value={form.password}
                id="password"
                type="password"
              />
              <label htmlFor="password">Password</label>
            </div>
            <div style={{ paddingLeft: "11.250px" }}>
              <button
                style={{
                  width: "150px",
                  borderRadius: "3px",
                  letterSpacing: "1.5px",
                  marginTop: "1rem",
                }}
                type="submit"
              >
                Login
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};
export default FormLogin;
