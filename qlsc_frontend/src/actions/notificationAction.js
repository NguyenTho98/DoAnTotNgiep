import { API_USER } from "constants/api";
import { fetch } from "utils/fetchMiddleware";

export const getMessages =
  (size = 5, page = 1) =>
  (dispatch) => {
    return dispatch(
      fetch(`${API_USER}/messages?size=${size}&page=${page}`, {
        method: "GET",
      })
    )
      .then((json) => {
        if (json) {
          const { messages, currentPage, totalItems, totalPages } = json;
          dispatch({
            type: "RECEIVE_MESSAGES",
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

export const markRead = (id) => (dispatch) => {
  return dispatch(
    fetch(`${API_USER}/messages/${id}`, {
      method: "PUT",
    })
  )
    .then((json) => {
      if (json && json.success) {
        dispatch({
          type: "MARK_READ_MESSAGE",
          id,
        });
      }
    })
    .catch((e) => {
      console.log("e", e);
    });
};

export const removeMessage = (id) => (dispatch) => {
  return dispatch(
    fetch(`${API_USER}/messages/${id}`, {
      method: "DELETE",
    })
  )
    .then((json) => {
      if (json && json.success) {
        dispatch({
          type: "REMOVE_MESSAGE",
          id,
        });
      }
    })
    .catch((e) => {
      console.log("e", e);
    });
};

export const notificationMaintenanceCard = (notification, user) => ({
  type: "LIVE_NOTIFICATION_MESSAGE",
  notification,
  user,
});

export const closeProgressBar = () => ({
  type: "CLOSE_PROGRESS_BAR",
});
