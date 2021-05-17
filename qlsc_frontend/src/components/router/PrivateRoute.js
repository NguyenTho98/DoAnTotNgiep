import React from "react";
import { connect } from "react-redux";
import { Route, Redirect, withRouter } from "react-router-dom";

const repairStaff = ["/maintenance-cards", "/products"];
const coordinator = [
  "/maintenance-cards",
  "/maintenance-card/create",
  "/maintenance-card/detail",
  "/products",
  "/customers",
  "/customer/detail",
  "/customer/create",
  "/customer/update",
  "/staffs",
];

const handlePathname = (str) => {
  if (str.includes("/customer/detail")) return "/customer/detail";
  if (str.includes("/customer/update/")) return "/customer/update";
  if (str.includes("/maintenance-card/detail")) return "/maintenance-card/detail";
  return str;
};

export const isLogin = (user, location, props) => {
  if (!user.role && location.pathname !== '/login') return "not-found";
  if (user && user.role === 1 && !coordinator.includes(handlePathname(location.pathname))) {
    return "not-found";
  }
  if (user && user.role === 2 && !repairStaff.includes(location.pathname)) {
    return "not-found";
  }
  return user && user.role;
};

const PrivateRoute = ({ component: Component, auth, ...rest }) => {
  return (
    <Route
      {...rest}
      render={(props) =>
        isLogin(auth.user, props.location, props) === "not-found" ? (
          <Redirect
            to={{
              pathname: "/not-found",
              state: { from: props.location },
            }}
          />
        ) : isLogin(auth.user, props.location, props) ? (
          <Component {...props} />
        ) : (
          <Redirect
            to={{
              pathname: "/login",
              state: { from: props.location },
            }}
          />
        )
      }
    />
  );
};

const mapStateToProps = (state) => ({ auth: state.auth });
export default withRouter(connect(mapStateToProps)(PrivateRoute));
