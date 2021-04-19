/* global window */
function setStorage(key, value, expireTimeInSeconds) {
  if (expireTimeInSeconds === undefined || expireTimeInSeconds === null) {
    expireTimeInSeconds = 24 * 60 * 60;
  } else {
    expireTimeInSeconds = Math.abs(expireTimeInSeconds);
  }

  const now = Date.now();
  const schedule = now + expireTimeInSeconds * 1000;
  const data = {
    data: value,
    expireTimeInMilliseconds: schedule,
  };
  return localStorage.setItem(key, JSON.stringify(data));
}

function getStorage(key) {
  const data = localStorage.getItem(key);
  if (!data) {
    return null;
  }
  if (new Date().getTime() > JSON.parse(data).expireTimeInMilliseconds) {
    return null;
  }
  return JSON.parse(data).data;
}

function removeStorage(name) {
  try {
    localStorage.removeItem(name);
  } catch (e) {
    return false;
  }
  return true;
}

/* global window */
const storage = {
  get(item, session = true) {
    if (session) {
      return window.sessionStorage.getItem(item);
    }
    return window.localStorage.getItem(item);
  },
  getExactType(item, session = true) {
    if (session) {
      const val1 = window.sessionStorage.getItem(item);
      try {
        return JSON.parse(val1);
      } catch (error) {
        return val1;
      }
    }
    const val2 = window.localStorage.getItem(item);
    try {
      return JSON.parse(val2);
    } catch (error) {
      return val2;
    }
  },
  set(item, value, session = true) {
    if (session) {
      return window.sessionStorage.setItem(item, value);
    }
    return window.localStorage.setItem(item, value);
  },
  clear() {
    sessionStorage.clear();
    localStorage.clear();
    storage.removeItem("token");
    storage.removeItem("userIdId");
  },
  removeItem(item) {
    return removeStorage(item);
  },
};

export default storage;
