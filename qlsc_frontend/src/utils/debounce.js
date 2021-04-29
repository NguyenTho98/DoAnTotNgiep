let interval;

const debounce = (callback, time = 250) => {
  return (...args) => {
    clearTimeout(interval);
    return new Promise(((resolve, reject) => {
      interval = setTimeout(() => {
        interval = null;
        resolve(callback(...args));
      }, time);
    }));
  };
};

export default debounce;
