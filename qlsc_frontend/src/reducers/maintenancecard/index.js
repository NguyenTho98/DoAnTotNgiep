import { combineReducers } from 'redux';
import filterInfo from './filterInfo';
import mainCards from './mainCard';
import ui from './ui';
import validate from './validate';

const mainCardReducer = combineReducers({
  filterInfo,
  validate,
  mainCards,
  ui,
});

export default mainCardReducer;
