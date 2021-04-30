import { combineReducers } from 'redux';
import filterInfo from './filterInfo';
import mainCard from './mainCard';
import ui from './ui';

const mainCardReducer = combineReducers({
  filterInfo,
  mainCard,
  ui,
});

export default mainCardReducer;
