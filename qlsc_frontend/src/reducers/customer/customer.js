import * as actionTypes from 'actions/actionTypes';

const initialState = {
    currentPage: 0,
    customers: [],
    totalItems: 0,
    totalPages: 0,
    customerItem: {}
}

export default (state = initialState, action) => {
    switch (action.type) {
      case actionTypes.GET_CUSTOMERS:
        return {
          ...state,
          customers: action.customers,
          currentPage: action.currentPage,
          totalItems: action.totalItems,
          totalPages: action.totalPages,
        }
      default:
        return state
    }
  }