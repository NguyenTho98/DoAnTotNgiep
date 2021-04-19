import * as actionTypes from "actions/actionTypes";
import { fetch } from "utils/fetchMiddleware";

export const getCity = (countryId) => (dispatch) => {
  let endpoint = `${TENANT_SERVICE_URL}/address/city-district`;
  if (countryId && countryId > 0) {
    endpoint += `?countryId=${countryId}`;
  }
  dispatch(fetch(endpoint)).then((json) => {
    dispatch(receiveCity(json));
  });
};

export const getWard = (cityId, districtId, countryId) => (dispatch) => {
  let endPoint = `${TENANT_SERVICE_URL}/address/ward?cityId=${cityId}&districtId=${districtId}`;
  if (countryId && countryId > 0) {
    endPoint += `&countryId=${countryId}`;
  }
  dispatch(fetch(endPoint)).then((res) => {
    dispatch(receiveWard(res));
  });
};

// export const receiveCity = (data) => ({
//   type: actionTypes.LOCATION_RECEIVE_CITY,
//   data,
// });

// export const receiveWard = (data) => ({
//   type: actionTypes.LOCATION_RECEIVE_WARD,
//   data,
// });
