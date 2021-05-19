export const notificationMaintenanceCard = (notification, user) => ({
  type: "LIVE_NOTIFICATION",
  notification,
  user,
});

export const closeProgressBar = () => ({
  type: 'CLOSE_PROGRESS_BAR',
});
