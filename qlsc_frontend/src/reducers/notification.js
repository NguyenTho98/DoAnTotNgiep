const initState = {
  notifications: [],
  messages: [],
  currentPage: 0,
  totalItems: 0,
  totalPages: 0,
};

export default (state = initState, action) => {
  switch (action.type) {
    case "RECEIVE_MESSAGES":
      return {
        ...state,
        messages: action.messages,
        currentPage: action.currentPage,
        totalItems: action.totalItems,
        totalPages: action.totalPages
      };
    case "LIVE_NOTIFICATION":
      const { notifications } = state;
      // const newNotifications = notifications.length ? notifications : [];
      // newNotifications.push(action.notification);
      return {
        ...state,
        notifications: [...notifications, action.notification],
      };
    case "CLOSE_PROGRESS_BAR":
      return {
        ...state,
        notifications: [],
      };
    default:
      return state;
  }
};
