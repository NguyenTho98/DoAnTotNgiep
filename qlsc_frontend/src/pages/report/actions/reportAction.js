import { API_MAINTENANCECARD } from "constants/api";
import { fetch } from "utils/fetchMiddleware";
import { toastError } from "../../../utils/toast";

export const getDataForReport = (from = "01/05/2021", to = "12/05/2021") => (dispatch, getState) => {
    return dispatch(
      fetch(`${API_MAINTENANCECARD}/business/report?from=${from}&to=${to}`, {
        method: "GET",
      })
    )
      .then((json) => {
        return json;
      })
      .catch((e) => {
        toastError("Có lỗi xảy ra khi lấy danh sách khách hàng");
      });
  };

