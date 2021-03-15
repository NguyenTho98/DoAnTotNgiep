// sản phẩm
import * as actionTypes from 'actions/actionTypes'
const initState = {
  showMenuTopBar: false
}

export default (state = initState, action) => {
  switch (action.type) {
    case actionTypes.SHOW_MENU_TOPBAR:
      return {
        ...state, showMenuTopBar: !state.showMenuTopBar
      }
    default:
      return state
  }
}
