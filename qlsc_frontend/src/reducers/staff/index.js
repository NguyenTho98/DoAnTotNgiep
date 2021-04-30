import { combineReducers } from 'redux';
import filterInfo from './filterInfo';
import staff from './staff';
import ui from './ui';

const staffReducer = combineReducers({
  filterInfo,
  staff,
  ui,
});

export default staffReducer;
