/* eslint-disable use-isnan */
import * as actionTypes from 'actions/actionTypes';
// import callApi from 'common/callApi';



export const showFilter = (show) => ({
  type: actionTypes.SHOW_MAIN_CARD_FILTER,
  show,
});

export const changeFilter = (filterInfo) => ({
  type: actionTypes.CHANGE_MAIN_CARD_FILTER_INFO,
  filterInfo,
});


export const resetFilter = () => (dispatch) => {
  const filterInfo = {
    channelType: 1,
    selectedStore: [],
    showFilterByStore: false,
    showFilter: false,
    filterText: '',
    selectedLocation: [],
    selectedFilter: [],
    status: ''
  };
  dispatch(fetchMainCard(filterInfo));
};

export const getMainCardsIds = (itemIds) => (dispatch) => {
  dispatch({
    type: actionTypes.GET_MAIN_CARD_IDS,
    itemIds,
  });
};

export const updateFetchingDeliveryCollation = (bool) => ({
  type: actionTypes.UPDATE_MAIN_CARD_FETCHING,
  bool,
});

export const updateIsEmpty = (bool) => ({
  type: actionTypes.UPDATE_MAIN_CARD_IS_EMPTY,
  bool,
});

export const fetchMainCard = (_filterInfo, page) => (dispatch, getState) => {
  const { deliveryCollation: { filterInfo } } = getState();
  const filter = _filterInfo || filterInfo;
  dispatch(updateFetchingDeliveryCollation(true));
  dispatch(filterMainCard(filter, page))
    .then((json) => {
      if (json && json.channel_order_collation && json.metadata) {
        const { channel_order_collation } = json;
        const normalized = normalize(channel_order_collation, [MainCardsSchema]);

        const data = normalized.entities;
        const itemIds = normalized.result;
        data.total = json.metadata.total;
        data.page = json.metadata.page;
        data.limit = json.metadata.limit;
        if (channel_order_collation.length === 0) {
          data.items = {};
          dispatch(updateIsEmpty(true));
        } else {
          dispatch(updateIsEmpty(false));
        }
        dispatch(getMainCards(data));
        dispatch(getMainCardsIds(itemIds));
        dispatch(updateFetchingDeliveryCollation(false));
      } else {
        dispatch(getMainCards([]));
        dispatch(getMainCardsIds([]));
        dispatch(updateFetchingDeliveryCollation(false));
        dispatch(updateIsEmpty(true));
      }
    });
};

export const filterMainCard = (filterInfo, page) => (dispatch) => {
  dispatch({
    type: actionTypes.CHANGE_MAIN_CARD_FILTER_INFO,
    filterInfo
  });
  dispatch(showFilter(false));
  return dispatch(fetchFilterMainCard(filterInfo, page))
    .then((json) => {
      return json;
    });
};

export const fetchFilterMainCard = (filterInfo, page = 1) => () => {
  let filterField = '';
  if (filterInfo.selectedStore && filterInfo.selectedStore.length > 0) {
    filterField = `${filterField}&ids=${filterInfo.selectedStore}`;
  } else {
    const selectedStore = [];
    if (tenant && tenant.connections && tenant.connections.length > 0) {
      for (let i = 0; i < tenant.connections.length; i++) {
        if (tenant.connections[i].channel_type === filterInfo.channelType) {
          selectedStore.push(tenant.connections[i].id);
        }
      }
    }
    filterField = `${filterField}&ids=${selectedStore}`;
  }
  filterField = `${filterField}&page=${page}`;
  filterField = `${filterField}&limit=20`;
  if (filterInfo.status) {
    filterField = `${filterField}&statuses=${filterInfo.status}`;
  }
  if (filterInfo.selectedLocation.length > 0) {
    filterField = `${filterField}&locationIds=${filterInfo.selectedLocation}`;
  }
  filterField = `${filterField}&query=${filterInfo.filterText ? encodeURIComponent(filterInfo.filterText.trim()) : ''}`;
  const options = {
    method: 'GET'
  };
  const url = `/api/order-collations?${filterField}`;
  return callApi(url, options).then((response) => {
    return response.data;
  }).catch(() => {
    SapoApp.flashError('Xảy ra lỗi khi lấy danh sách đơn');
  });
};

export const getMainCards = (data) => (dispatch) => {
  dispatch({
    type: actionTypes.GET_MAIN_CARD,
    data,
  });
};

export const selectedMainCardIds = (ids) => (dispatch) => {
  dispatch({
    type: actionTypes.SELECTED_ORDER_COLLATION_IDS,
    ids,
  });
};

export const updateMainCard = (list) => (dispatch) => {
  dispatch({
    type: actionTypes.UPDATE_ORDER_COLLATION,
    list,
  });
};

export const addItemMainCardImportFile = (list) => (dispatch) => {
  dispatch({
    type: actionTypes.ADD_ITEM_ORDER_COLLATION_IMPORT_FILE,
    list,
  });
};

export const addItemMainCard = (item) => (dispatch) => {
  dispatch({
    type: actionTypes.ADD_ITEM_ORDER_COLLATION,
    item,
  });
};

export const showModalMainCard = (show) => (dispatch) => {
  dispatch({
    type: actionTypes.SHOW_MODAL_ORDER_COLLATION,
    show,
  });
};

export const getDeliveryCollationDetail = (deliveryCollationId) => () => {
  const url = `/api/delivery-collations/${deliveryCollationId}`;
  const options = {
    method: 'GET',
  };
  return callApi(url, options).catch(() => {
    SapoApp.flashError('Xảy ra lỗi khi lấy chi tiết đơn đối soát');
  });
};
