import { API_USER } from "constants/api";
import { fetch } from "utils/fetchMiddleware";

export const getMessages = (size = 10, page = 1) => (dispatch) => {
  return dispatch(
    fetch(`${API_USER}/messages?size=${size}&page=${page}`, {
      method: "GET",
    })
  )
    .then((json) => {
      if (json) {
        const { messages, currentPage, totalItems, totalPages } = json;
        dispatch({
          type: 'RECEIVE_MESSAGES',
          messages,
          currentPage,
          totalItems,
          totalPages,
        });
      }
    })
    .catch((e) => {
      console.log("e", e);
    });
};

export const notificationMaintenanceCard = (notification, user) => ({
  type: "LIVE_NOTIFICATION",
  notification,
  user,
});

export const closeProgressBar = () => ({
  type: "CLOSE_PROGRESS_BAR",
});
