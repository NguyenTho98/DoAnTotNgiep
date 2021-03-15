// sản phẩm
import * as actionTypes from 'actions/actionTypes'
const initState = {
  items: {},
  total: 0,
  page: 1
}

export default (state = initState, action) => {
  switch (action.type) {
    case actionTypes.TEST:
      return {
        ...state
      }
    default:
      return state
  }
}
