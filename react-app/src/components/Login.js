import React, { useState } from "react";
import { Redirect } from "react-router";
import UserIcon from "../images/login-form-icon.png";
import AuthService from "../services/AuthService";
import UserService from "../services/UserService";

const Login = ({ user, setUser }) => {
  const [error, setError] = useState(null);
  const [loginInfo, setLoginInfo] = useState({
    email: "",
    password: "",
  });

  const updateLoggedUser = () => {
    UserService.getUserByUserId(localStorage.getItem("userId"))
      .then((res) => {
        setUser(res.data);
      })
      .catch((err) => {
        console.log(err.response.data.errors);
      });
  };

  const submit = (e) => {
    e.preventDefault();
    AuthService.signin(loginInfo)
      .then((res) => {
        localStorage.setItem("token", res.data.accessToken);
        localStorage.setItem("tokenType", res.data.tokenType);
        localStorage.setItem("userId", res.data.userId);
        updateLoggedUser();
      })
      .catch((err) => {
        setError(err.response.data.message);
      });
  };

  return user ? (
    <Redirect to="/" />
  ) : (
    <div className="wrapper fadeInDown">
      <div id="formContent">
        <div className="fadeIn first">
          <img src={UserIcon} id="icon" alt="User Icon" />
        </div>
        {error && (<div className="alert alert-danger mx-4" role="alert">
            {error}
        </div>)}

        <form onSubmit={submit}  >
          <input
            type="email"
            id="login"
            className="fadeIn second"
            name="login"
            placeholder="Email"
            required
            onChange={(e) =>
              setLoginInfo({
                ...loginInfo,
                email: e.target.value,
              })
            }
          />
          <input
            type="password"
            id="password"
            className="fadeIn third"
            name="password"
            placeholder="Password"
            required
            onChange={(e) =>
              setLoginInfo({
                ...loginInfo,
                password: e.target.value,
              })
            }
          />
          <input type="submit" className="fadeIn fourth" value="Log In" />
        </form>

        <div id="formFooter">
          <a className="underlineHover" href="./login">
            Forgot Password?
          </a>
        </div>
      </div>
    </div>
  );
};

export default Login;
