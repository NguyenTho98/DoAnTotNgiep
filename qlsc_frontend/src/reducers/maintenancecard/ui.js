import * as actionTypes from 'actions/actionTypes';

const initState = {
  fetching: false,
  isEmpty: false,
};
export default (state = initState, action) => {
  switch (action.type) {
    case actionTypes.MAIN_CARD_FETCHING:
      return {
        ...state,
        fetching: action.bool,
      };
    case actionTypes.MAIN_CARD_IS_EMPTY:
      return {
        ...state,
        isEmpty: action.bool,
      };
    default:
      return state;
  }
};
