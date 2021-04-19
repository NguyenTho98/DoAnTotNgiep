import * as actionTypes from "actions/actionTypes";
import { fetch } from "utils/fetchMiddleware";

export const getListCustomer = (options = {}) => (dispatch, getState) => {
  return dispatch(fetch(`http://localhost:8084/admin/customers`))
    .then((json) => {
      if (json && json.customers) {
        const { customers, currentPage, totalItems, totalPages } = json;
        dispatch(getCustomers(customers, currentPage, totalItems, totalPages));
      }
    })
    .catch((e) => {
      return e;
    });
};

export const getCustomers = (customers, currentPage, totalItems, totalPages) => ({
  type: actionTypes.GET_CUSTOMERS,
  customers,
  currentPage,
  totalItems,
  totalPages,
});
