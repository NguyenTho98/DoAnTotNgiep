import { combineReducers } from 'redux';
import filterInfo from './filterInfo';
import product from './product';
import ui from './ui';

const productReducer = combineReducers({
  filterInfo,
  product,
  ui,
});

export default productReducer;
