import axios from "axios";
import { authorization } from "../func";

const ACCOUNT_URL = "http://localhost:9000/accounts";
export const createAccount = async (account) => {
  return axios({
    method: "POST",
    url: ACCOUNT_URL + "/create",
    data: account,
    headers: authorization(),
  });
};
export const deleteAccount = async (id) => {
  return axios({
    method: "DELETE",
    url: ACCOUNT_URL + `/${id}`,
    headers: authorization(),
  });
};
export const withDrawMoney = async (obj) => {
  return axios({
    method: "POST",
    url: ACCOUNT_URL + "/withdrawMoney",
    data: obj,
    headers: authorization(),
  });
};
