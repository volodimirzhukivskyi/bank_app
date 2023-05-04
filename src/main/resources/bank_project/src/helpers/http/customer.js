import axios from "axios";
import { authorization } from "../func";

const CUSTOMER_URL = "http://localhost:9000/customer";

export async function fetchCustomers(page, size) {
  const response = await axios({
    method: "GET",
    url: CUSTOMER_URL + `/${page}/size/${size}`,
    headers: authorization(),
  });
  return response.data;
}

export const updateCustomerInfo = async (customerUpdate) => {
  return axios({
    method: "PUT",
    url: CUSTOMER_URL + `/update`,
    data: customerUpdate,
    headers: authorization(),
  });
};
export const createCustomer = async (customer) => {
  return await axios({
    method: "POST",
    url: CUSTOMER_URL + `/create`,
    data: customer,
    headers: authorization(),
  });
};
export const deleteCustomer = async (id) => {
  return axios({
    method: "DELETE",
    url: CUSTOMER_URL + `/${id}`,
    headers: authorization(),
  });
};
