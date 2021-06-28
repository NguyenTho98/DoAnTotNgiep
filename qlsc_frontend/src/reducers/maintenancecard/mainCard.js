import * as actionTypes from 'actions/actionTypes';

const initState = {
  currentPage: 0,
  mainCardList: [],
  totalItems: 0,
  totalPages: 0,
  mainCard: {},
};

export default (state = initState, action) => {
  switch (action.type) {
    case actionTypes.RECEIVE_MAIN_CARDS:
      return {
        ...state,
        mainCardList: action.maintenancecards,
        currentPage: action.currentPage,
        totalItems: action.totalItems,
        totalPages: action.totalPages,
      };
    case actionTypes.RECEIVE_MAIN_CARD:
      return {
        ...state,
        mainCard: action.mainCard,
      };
    case actionTypes.ADD_MAIN_CARD_SOCKET: {
      const arr = [...state.mainCardList];
      if(arr.length === 0) {
        arr.unshift(action.item)
        return {
          ...state,
          mainCardList: arr,
        };
      }
      const tmp = arr.find((i)=>i.id === action.item.id)
      if(!tmp){
        arr.unshift(action.item)
        return {
          ...state,
          mainCardList: arr,
        };
      }
    }
    default:
      return state;
  }
};

