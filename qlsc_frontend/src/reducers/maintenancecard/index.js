import { combineReducers } from 'redux';
import filterInfo from './filterInfo';
import mainCards from './mainCard';
import ui from './ui';

const mainCardReducer = combineReducers({
  filterInfo,
  mainCards,
  ui,
});

export default mainCardReducer;
