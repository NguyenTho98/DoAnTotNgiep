import { combineReducers } from 'redux';
import filterInfo from './filterInfo';
import mainCard from './mainCard';
import hasOrderCollations from './hasOrderCollations';
import orderCollations from './orderCollations';
import ui from './ui';

const deliveryCollationReducer = combineReducers({
  filterInfo,
  mainCard,
  ui,
});

export default deliveryCollationReducer;
