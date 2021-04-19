import * as actionTypes from "actions/actionTypes";
import storage from "../utils/storage";

const initState = {
  accessToken: null,
  user: {},
};

const auth = (state = initState, action) => {
  switch (action.type) {
    case actionTypes.RECEIVE_ACCESS_TOKEN:
      storage.set("token", action.accessToken);
      return {
        ...state,
        accessToken: action.accessToken,
      };
    case actionTypes.RECEIVE_USER_ACCOUNT:
      return {
        ...state,
        account: {
          ...state.account,
          ...action.account,
          limit: action.limitInfo,
        },
      };
    default:
      return state;
  }
};

export default auth;
