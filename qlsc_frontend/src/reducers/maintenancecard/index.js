import { combineReducers } from 'redux';
import filterInfo from './filterInfo';
import mainCard from './mainCard';
import ui from './ui';

const deliveryCollationReducer = combineReducers({
  filterInfo,
  mainCard,
  ui,
});

export default deliveryCollationReducer;
