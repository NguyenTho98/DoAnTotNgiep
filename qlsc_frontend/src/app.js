import React, { useEffect } from "react";
import {
  Router,
  Route,
  Switch,
  withRouter,
} from "react-router-dom";
import SockJsClient from 'react-stomp';
import { getCity } from "./pages/customer/actions/locationActions";
import { createBrowserHistory } from "history";
import { connect } from "react-redux";
import "react-toastify/dist/ReactToastify.css";
import { ToastContainer } from "react-toastify";
import Modals from "./components/modal/modal";
import "./styles/app.scss";
import LoginPage from "./pages/login/login";
import DashBoard from "pages/dashboard/DashBoard";
import NotFoundComponent from "./components/notfound/NotFoundComponent";
import storage from "./utils/storage";
import { checkInfoUser } from "./pages/login/actions/loginAction";
import pushstate from "utils/pushstate";
import PrivateRoute from "./components/router/PrivateRoute";
import { SOCKET_URL_V2 } from "./constants/api";
import { getStaffsByRepairman } from "./actions/commons";
import history from './utils/history';

function App(props) {
  const { showMenu } = props;
  useEffect(() => {
    const token = storage.get("token", false);
    console.log('token', token);
    if (token) {
      props.onCheckInfoUser(token).then((json) => {
        if (json && json.role) {
          if (window.location.pathname && window.location.pathname !== '/not-found') {
            history.push(window.location.pathname);
          } else {
            history.push("/maintenance-cards");
          }
        } else {
          history.push("/login");
        }
      });
    } else if (window.location.pathname !== '/login') {
      history.push("/not-found");
    } else history.push("/login");
    props.onGetCity();
    props.getStaffsByRepairman();
  }, []);

  const onConnected = () => {
    //window.alert("Connected!!")
  }

  const onMessageReceived = (msg) => {
    window.alert("check msg: ", msg);
  }

  const onDisconnect = () => {
    //window.alert("Disconnected!");
  }
  return (
    <Router history={history}>
      <SockJsClient
        url={SOCKET_URL_V2}
        topics={['/topic/message']}
        onConnect={onConnected}
        onDisconnect={onDisconnect}
        onMessage={msg => onMessageReceived(msg)}
        debug={false}
      />
      <ToastContainer
        position="top-center"
        autoClose={2000}
        hideProgressBar
        newestOnTop={false}
        closeOnClick={false}
        rtl={false}
        pauseOnFocusLoss={false}
        draggable={false}
        pauseOnHover={false}
        closeButton={false}
        limit={1}
      />
      <Modals />
      <Switch>
        <Route path="/not-found" component={NotFoundComponent} />
        <Route path="/login" component={LoginPage} />
        <PrivateRoute path="/" component={() => <DashBoard showMenu={showMenu} />} />
      </Switch>
    </Router>
  );
}

const mapStateToProps = (state) => {
  const {
    globalUI: { showMenuTopBar },
  } = state;
  const showMenu = showMenuTopBar;
  return {
    showMenu,
  };
};
const mapDispatchToProps = (dispatch) => ({
  onCheckInfoUser: (token) => dispatch(checkInfoUser(token)),
  onGetCity: () => dispatch(getCity()),
  getStaffsByRepairman: () => dispatch(getStaffsByRepairman()),
});
export default withRouter(connect(mapStateToProps, mapDispatchToProps)(App));
