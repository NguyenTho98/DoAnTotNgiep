import * as actionTypes from "actions/actionTypes";
import { API_USER } from "constants/api";
import { fetch } from "utils/fetchMiddleware";

export const getListStaff = (search = '', option = {}) => (dispatch, getState) => {
    const filter = processOption(search, option);
    return dispatch(fetch(`${API_USER}/users${filter}`, {
      method: 'GET',
    }))
      .then((json) => {
        if (json && json.users) {
          const { users, currentPage, totalElement, totalPage } = json;
          dispatch(getStaffs(users, currentPage, totalElement, totalPage));
        }
      })
      .catch((e) => {
        return e;
      });
  };
  
  export const getStaffById = (id) => (dispatch, getState) => {
    return dispatch(fetch(`${API_USER}/users/${id}`))
      .then((json) => {
        if (json) {
          dispatch(getStaff(json));
        }
        return json;
      })
      .catch((e) => {
        return e;
      });
  };
  
  export const updateStaff = (id, staff = {}) => (dispatch, getState) => {
    const endpoint = `${API_USER}/users/${id}`;
    return dispatch(
      fetch(endpoint, {
        method: "PUT",
        body: JSON.stringify(staff),
      })
    )
      .then((json) => {
        return json;
      })
      .catch((e) => {
        console.error(e);
        return e;
      });
  };
  
  export const saveStaff = (staff = {}) => (dispatch, getState) => {
    const endpoint = `${API_USER}/users`;
    return dispatch(
      fetch(endpoint, {
        method: "POST",
        body: JSON.stringify(staff),
      })
    )
      .then((json) => {
        return json;
      })
      .catch((e) => {
        console.error(e);
        return e;
      });
  };
  
  const processOption = (search, option) => {
    let filter = '?';
    if (search) {
      filter += `search=${search}`
    }
    if (option && option.page) {
      filter += `&page=${option.page}`;
    }
    return filter;
  }
  
  export const getStaff = (staff) => ({
    type: actionTypes.RECEIVE_STAFF,
    staff,
  });
  
  export const getStaffs = (
    users,
    currentPage,
    totalElement,
    totalPage
  ) => ({
    type: actionTypes.RECEIVE_STAFFS,
    users,
    currentPage,
    totalElement,
    totalPage,
  });