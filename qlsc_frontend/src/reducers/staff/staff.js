import * as actionTypes from 'actions/actionTypes';

const initState = {
  currentPage: 0,
  staffs: [],
  totalItem: 0,
  totalPage: 0,
  staff: {}
};

export default (state = initState, action) => {
  switch (action.type) {
    case actionTypes.RECEIVE_STAFFS:
      return {
        ...state,
        staffs: action.users,
        currentPage: action.currentPage,
        totalItem: action.totalElement,
        totalPage: action.totalPage,
      }
    case actionTypes.RECEIVE_STAFF:
      return {
        ...state,
        staff: action.staff,
      }
    default:
      return state;
  }
};

