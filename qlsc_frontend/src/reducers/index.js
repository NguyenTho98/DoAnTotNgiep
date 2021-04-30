import { combineReducers } from 'redux';
import products from './product';
import globalUI from './globalUI';
import modal from './../components/modal/modalReducer';
import customer from './customer/customer';
import locations from './locations/locations';
import auth from './auth';
import mainCards from './maintenancecard';
import staffs from './staff';

export default combineReducers({
  auth,
  products,
  globalUI,
  modal,
  customer,
  locations,
  mainCards,
  staffs,
})
