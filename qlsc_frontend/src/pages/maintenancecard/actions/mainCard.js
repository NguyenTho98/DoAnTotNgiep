/* eslint-disable use-isnan */
import * as actionTypes from 'common/actionTypes';
import { normalize, schema } from 'normalizr';
import callApi from 'common/callApi';

export const orderCollationsSchema = new schema.Entity('items');
const tenant = JSON.parse(sessionStorage.getItem('tenant')) || {};
export const showFilter = (show) => ({
  type: actionTypes.SHOW_DELIVERY_COLLATIONS_FILTER,
  show,
});

export const changeFilter = (filterInfo) => ({
  type: actionTypes.CHANGE_DELIVERY_COLLATIONS_FILTER_INFO,
  filterInfo,
});

export const changeShowByStore = (show) => ({
  type: actionTypes.SHOW_DELIVERY_COLLATIONS_FILTER_BY_STORE,
  show,
});

export const changeShowTable = (show) => ({
  type: actionTypes.SHOW_COLLATIONS_TABLE,
  show,
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
  dispatch(fetchOrderCollation(filterInfo));
};

export const getOrderCollationsIds = (itemIds) => (dispatch) => {
  dispatch({
    type: actionTypes.GET_ORDER_COLLATIONS_IDS,
    itemIds,
  });
};

export const updateFetchingDeliveryCollation = (bool) => ({
  type: actionTypes.UPDATE_DELIVERY_COLLATIONS_FETCHING,
  bool,
});

export const updateIsEmpty = (bool) => ({
  type: actionTypes.UPDATE_DELIVERY_COLLATIONS_IS_EMPTY,
  bool,
});

export const fetchOrderCollation = (_filterInfo, page) => (dispatch, getState) => {
  const { deliveryCollation: { filterInfo } } = getState();
  const filter = _filterInfo || filterInfo;
  dispatch(updateFetchingDeliveryCollation(true));
  dispatch(filterOrderCollation(filter, page))
    .then((json) => {
      if (json && json.channel_order_collation && json.metadata) {
        const { channel_order_collation } = json;
        const normalized = normalize(channel_order_collation, [orderCollationsSchema]);

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
        dispatch(getOrderCollations(data));
        dispatch(getOrderCollationsIds(itemIds));
        dispatch(updateFetchingDeliveryCollation(false));
      } else {
        dispatch(getOrderCollations([]));
        dispatch(getOrderCollationsIds([]));
        dispatch(updateFetchingDeliveryCollation(false));
        dispatch(updateIsEmpty(true));
      }
    });
};

export const filterOrderCollation = (filterInfo, page) => (dispatch) => {
  dispatch({
    type: actionTypes.CHANGE_DELIVERY_COLLATIONS_FILTER_INFO,
    filterInfo
  });
  dispatch(showFilter(false));
  return dispatch(fetchFilterOrderCollation(filterInfo, page))
    .then((json) => {
      return json;
    });
};

export const fetchFilterOrderCollation = (filterInfo, page = 1) => () => {
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

export const getOrderCollations = (data) => (dispatch) => {
  dispatch({
    type: actionTypes.GET_ORDER_COLLATIONS,
    data,
  });
};

export const selectedOrderCollationIds = (ids) => (dispatch) => {
  dispatch({
    type: actionTypes.SELECTED_ORDER_COLLATION_IDS,
    ids,
  });
};

export const updateOrderCollation = (list) => (dispatch) => {
  dispatch({
    type: actionTypes.UPDATE_ORDER_COLLATION,
    list,
  });
};

export const addItemOrderCollationImportFile = (list) => (dispatch) => {
  dispatch({
    type: actionTypes.ADD_ITEM_ORDER_COLLATION_IMPORT_FILE,
    list,
  });
};

export const addItemOrderCollation = (item) => (dispatch) => {
  dispatch({
    type: actionTypes.ADD_ITEM_ORDER_COLLATION,
    item,
  });
};

export const showModalOrderCollation = (show) => (dispatch) => {
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
