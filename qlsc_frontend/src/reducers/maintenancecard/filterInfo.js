import * as actionTypes from '../../common/actionTypes';

const initState = {
  showFilter: false,
  filterText: '',
  slectedFilter: [],
  statusWork: '',
  statusPayment: '',
  endDate: '',
  startDate: '',
};

const filterInfo = (state = initState, action) => {
  switch (action.type) {
    case actionTypes.SHOW_DELIVERY_COLLATIONS_FILTER:
      return {
        ...state,
        showFilter: action.show,
      };
    case actionTypes.SHOW_DELIVERY_COLLATIONS_FILTER_BY_STORE:
      return {
        ...state,
        showFilterByStore: action.show,
      };
    case actionTypes.CHANGE_DELIVERY_COLLATIONS_FILTER_INFO:
      return {
        ...state,
        showFilter: action.filterInfo.showFilter,
        filterText: action.filterInfo.filterText,
        statusWork: action.filterInfo.statusWork,
        statusPayment: action.filterInfo.statusPayment,
        endDate: action.filterInfo.endDate,
        startDate: action.filterInfo.startDate,
      };
    default:
      return state;
  }
};

export default filterInfo;
