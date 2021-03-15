import { combineReducers } from 'redux'
import product from './product/products'
import globalUI from './globalUI'
export default combineReducers({
  product,
  globalUI
})
