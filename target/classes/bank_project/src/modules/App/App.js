import "./App.css";
import Menu from "../../components/Menu/Menu";
import React, { useEffect, useState } from "react";
import Button from "../../components/Button/Button";
import Modal from "../../components/Modal/Modal";
import FormChangeCustomer from "../../components/Form/FormChangeCustomer/FormChangeCustomer";
import { useDispatch, useSelector } from "react-redux";
import styles from "./App.module.css";
import stylesButton from "../../components/Button/Button.module.scss";
import {
  addShopsFailure,
  addShopsSuccess,
  countPage,
  isCreateAccountFunc,
  isLoadingFunc,
  isOpenAccount,
  isOpenFunc,
  isReload,
} from "../../Redux/Reducer/AppReducer/appActions";
import FormAccount from "../../components/Form/FormAccount/FormAccount";
import { deleteCustomer, fetchCustomers } from "../../helpers/http/customer";
import AccountCard from "../../components/AccountCard/AccountCard";
import FormLogin from "../../components/Form/FormLogin/FormLogin";
import FormWithDrawMoney from "../../components/Form/FormWithDrawMoney/FormWithDrawMoney";

const App = () => {
  const {
    isOpen,
    isCreateAccount,
    isLoading,
    customers,
    error,
    openAccount,
    reload,
    countPagination,
  } = useSelector((store) => ({
    customers: store.shops,
    error: store.error,
    isLoading: store.isLoading,
    openAccount: store.openAccount,
    reload: store.isReload,
    isOpen: store.isOpen,
    isCreateAccount: store.isCreateAccount,
    countPagination: store.countPage,
  }));
  const dispatch = useDispatch();
  const ws = new WebSocket("ws://localhost:9000/name");
  const [message, setMessage] = useState("");
  const updateMessage = (event) => {
    const json = JSON.parse(event.data);
    setMessage(json.data);
  };
  useEffect(() => {
    ws.onmessage = updateMessage;
  });
  useEffect(() => {
    if (localStorage.getItem("token")) {
      (async function getCustomers() {
        try {
          const customers = await fetchCustomers(countPagination, 3);
          dispatch(addShopsSuccess(customers));
        } catch (e) {
          dispatch(addShopsFailure("Could not fetch expenses!"));
        }
        dispatch(isLoadingFunc(false));
      })();
    }
  }, [!reload, reload]);
  return (
    <div>
      <Menu />
      {isLoading && (
        <div
          style={{ fontSize: "64px", textAlign: "center", paddingTop: "15%" }}
        >
          Loading...
        </div>
      )}
      {error && <div className="error">{error}</div>}
      {!localStorage.getItem("token") && <FormLogin />}
      {customers && (
        <div>
          <h1>CUSTOMERS INFO</h1>
          <table>
            <thead>
              <tr>
                <th>name</th>
                <th>email</th>
                <th>age</th>
                <th className={styles.big}>accounts</th>
                <th>select item</th>
              </tr>
            </thead>
            <tbody>
              {customers.map((user, i) => (
                <tr className="users-block" key={user.id}>
                  <td>{user.name}</td>
                  <td>{user.email}</td>
                  <td>{user.age}</td>
                  <td key={user.id}>
                    {user.id === openAccount ? (
                      <>
                        {user.accounts.map((account) => (
                          <AccountCard key={account.id} account={account} />
                        ))}
                        <Button
                          onClick={() =>
                            dispatch(isCreateAccountFunc(!isCreateAccount))
                          }
                        >
                          {!isCreateAccount
                            ? "Создать акаунт"
                            : "Cкрыть создание"}
                        </Button>
                        {isCreateAccount && (
                          <FormAccount customerId={user.id} />
                        )}
                      </>
                    ) : (
                      "Количество акаунтов - " + user.accounts.length
                    )}
                    <div>
                      <Button
                        onClick={() =>
                          dispatch(isOpenAccount(openAccount ? null : user.id))
                        }
                      >
                        {openAccount === user.id
                          ? "скрыть акаунты"
                          : "показать акаунты"}
                      </Button>
                    </div>
                  </td>
                  <td>
                    <Button
                      onClick={() => {
                        dispatch(
                          isOpenFunc({ boolean: true, activeId: user.id })
                        );
                      }}
                    >
                      Change Item
                    </Button>
                    <Button
                      onClick={() => {
                        deleteCustomer(user.id)
                          .then((res) => {
                            res.status === 200 && dispatch(isReload(!reload));
                          })
                          .catch((e) => {
                            console.log(e);
                          });
                      }}
                    >
                      x
                    </Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          <div className={styles.buttons}>
            <Button
              className={stylesButton.button}
              disabled={countPagination <= 0 && true}
              onClick={() => {
                if (countPagination > 0) {
                  dispatch(countPage(countPagination - 1));
                  dispatch(isReload(!reload));
                }
              }}
            >
              Предыдущая страница Customer
            </Button>
            <Button
              className={stylesButton.button}
              disabled={customers.length < 3 && true}
              onClick={() => {
                if (customers.length === 3) {
                  dispatch(countPage(countPagination + 1));
                  dispatch(isReload(!reload));
                }
              }}
            >
              Следующая страница Customer
            </Button>{" "}
          </div>
        </div>
      )}{" "}
      <div className={styles.addCustomer}>
        <h1>Добавить Customer</h1>
        <FormChangeCustomer type={"create"} />
      </div>
      {isOpen.boolean && (
        <Modal
          click={() => dispatch(isOpenFunc({ boolean: false, activeId: null }))}
          header={"Change Customer"}
          closeButton={true}
          text={
            <FormChangeCustomer
              type={"update"}
              customer={customers.find(
                (customer) => customer.id === isOpen.activeId
              )}
            />
          }
        />
      )}
      <div className={styles.addCustomer}>
        <h1>Снять деньги с аккаунта!</h1>
        <FormWithDrawMoney click={(text) => ws.send(text)} />
      </div>
      {message !== "" && (
        <Modal
          header="Спасибо за сотрудничество!!! "
          closeButton={true}
          click={() => setMessage("")}
          text={message}
          actions={
            <div className="containerButton">
              <Button
                className="modalButtonOne"
                backgroundColor={"#b3382c"}
                onClick={() => {
                  setMessage("");
                  dispatch(isReload(!reload));
                }}
              >
                Ok
              </Button>
            </div>
          }
        />
      )}
    </div>
  );
};

export default App;
