import React, { useState ,useEffect} from "react";
import { connect } from "react-redux";
import { login, checkInfoUser, logout } from "./actions/loginAction";
import "./login.scss";
import logo from "../../images/KIOMO.png";
import { getMessages } from "../../actions/notificationAction";
import pushstate from "utils/pushstate";

function LoginPage(props) {
  const [user, setUser] = useState({
    email: "",
    password: "",
  });
  const [error, setError] = useState({})
  const handleChange = (event) => {
    const { name, value } = event.target;
    setUser({ ...user, [name]: value });
  };
  useEffect(() => {
    props.onLogout();
  }, []);
  const handleSubmit = () => {
    props.onLogin(user).then((res) => {
      if (!res) {
        setError({isError: true, message: '* Thông tin đăng nhập không chính xác'});
        return;
      }
      props.onGetMessages();
      props.onCheckInfoUser(res).then((json) => {
        if (json) pushstate(props.history, "/maintenance-cards");
      });
    });
  };
  return (
    <div className="root">
      <div maxWidth="lg" className="container">
        <div className="row">
          <div className="col-10 login-warpper">
            <div className="row area-login">
              <div className="col-6">
                <div className="form-login">
                  <div className="div-logo">
                    <img src={logo} alt="" className="logo"></img>
                  </div>
                  <input
                    onChange={(e) => handleChange(e)}
                    className="input"
                    name="email"
                    placeholder="Địa chỉ email"
                    type="email"
                    style={{ width: "100%", margin: "20px 0px", borderRadius: 3 }}
                  />
                  <input
                    onChange={(e) => handleChange(e)}
                    className="input"
                    placeholder="Mật khẩu"
                    type="password"
                    name="password"
                    style={{ width: "100%", margin: "20px 0px", borderRadius: 3 }}
                  />
                  {
                    <div style={{color:'#a94442'}}>
                      {error && error.isError ? error.message : ''}
                    </div>
                  }
                  <div
                    className="div-action-login"
                    onClick={() => handleSubmit()}
                  >
                    <button className="btn btn-login" type="submit">
                      Đăng nhập 
                    </button>
                  </div>
                  <div
                    className="div-action-register"
                    onClick={() => pushstate(props.history, '/register')}
                  >
                    <button className="btn btn-register" type="submit">
                      Đăng ký
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

const mapDispatchToProps = (dispatch) => ({
  onLogin: (data) => dispatch(login(data)),
  onLogout: () => dispatch(logout()),
  onCheckInfoUser: (token) => dispatch(checkInfoUser(token)),
  onGetMessages: () => dispatch(getMessages()),
});
export default connect(null, mapDispatchToProps)(LoginPage);
