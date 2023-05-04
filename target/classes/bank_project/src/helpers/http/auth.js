import axios from "axios";
import { authorization } from "../func";

const AUTH = "http://localhost:9000";
export const getToken = async (obj) => {
  return await axios.post(AUTH + "/authenticate", obj);
};
