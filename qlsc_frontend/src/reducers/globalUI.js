// sản phẩm
import * as actionTypes from 'actions/actionTypes'
const initState = {
  showFeedback: false,
}

export default (state = initState, action) => {
  switch (action.type) {
    case actionTypes.SHOW_FEED_BACK:
      return {
        ...state, showFeedback: action.show
      }
    default:
      return state
  }
}
