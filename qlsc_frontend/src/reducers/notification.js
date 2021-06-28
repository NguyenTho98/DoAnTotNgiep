const initState = {
  notifications: [],
  messages: [],
  currentPage: 0,
  totalItems: 0,
  totalPages: 0,
};
let lastPlay = Date.now();
const audio = new Audio('https://s3-ap-southeast-1.amazonaws.com/fbm.dktcdn.net/fpage/static/audio/noti.mp3');
export default (state = initState, action) => {
  switch (action.type) {
    case "RECEIVE_MESSAGES":
      if (action.currentPage > 1) {
        const newMessage = state.messages.concat(action.messages);
        return {
          ...state,
          messages: newMessage,
          currentPage: action.currentPage,
          totalItems: action.totalItems,
          totalPages: action.totalPages,
        };
      }
      return {
        ...state,
        messages: action.messages,
        currentPage: action.currentPage,
        totalItems: action.totalItems,
        totalPages: action.totalPages,
      };
    case "LIVE_NOTIFICATION_MESSAGE":
      const { messages, totalItems } = state;
      const newMessages = messages;
      const message = messages.find(
        (item) => item.id === action.notification.id
      );
      if (
        !message &&
        action.notification.user &&
        action.user.id === action.notification.user.id
      ) {
        playNotiSoundIfNeed();
        return {
          ...state,
          totalItems: totalItems + 1,
          messages: [action.notification].concat(newMessages),
        };
      }
      return {
        ...state,
        messages: newMessages,
      };
    case "MARK_READ_MESSAGE":
      const objIndex = state.messages.findIndex((obj) => obj.id === action.id);
      const updatedObj = { ...state.messages[objIndex], unRead: 0 };
      const updatedMessages = [
        ...state.messages.slice(0, objIndex),
        updatedObj,
        ...state.messages.slice(objIndex + 1),
      ];
      return {
        ...state,
        messages: updatedMessages,
        totalItems: state.totalItems - 1,
      };
      case "REMOVE_MESSAGE":
        let newMessage = state.messages.filter(item => item.id != action.id);
        return {
          ...state,
          messages: newMessage,
          totalItems: state.totalItems - 1,
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


const playNotiSoundIfNeed = () => {
  const now = Date.now();
  const thresold = 2000;
  if (now - lastPlay > thresold) {
    audio.play();
    lastPlay = now;
  }
};