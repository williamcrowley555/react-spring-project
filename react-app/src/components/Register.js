import React, { useState } from "react";
import { Link } from "react-router-dom";
import UserIcon from "../images/login-form-icon.png";
import successfullIcon from "../images/green-tick.png";
import AuthService from "../services/AuthService";

const Register = () => {
  const [error, setError] = useState(null);
  const [isRegistered, setisRegistered] = useState(false);
  const [registerInfo, setRegisterInfo] = useState({
    firstName: "",
    lastName: "",
    email: "",
    password: "",
  });

  const submit = async (e) => {
    e.preventDefault();

    AuthService.signup(registerInfo)
      .then((res) => {
        console.log(res);
        setisRegistered(true);
      })
      .catch((err) => {
        setError(err.response.data.message);
      });
  };

  return isRegistered ? (
    <div className="wrapper fadeInDown">
      <div id="formContent">
        <div className="fadeIn first">
          <img
            className="mt-4"
            src={successfullIcon}
            id="success-icon"
            alt="User Icon"
          />
          <h2 className="text-success py-2"> Registered successfully </h2>
        </div>
      </div>
    </div>
  ) : (
    <div className="wrapper fadeInDown">
      <div id="formContent">
        <div className="fadeIn first">
          <img src={UserIcon} id="icon" alt="User Icon" />
        </div>
        {error && (
          <div className="alert alert-danger mx-4" role="alert">
            {error}
          </div>
        )}

        <form onSubmit={submit}>
          <div className="row px-3">
            <div className="col-md-6">
              <div className="form-group">
                <input
                  type="text"
                  id="firstName"
                  className="fadeIn second"
                  name="firstName"
                  placeholder="First Name"
                  required
                  title="Special characters and numbers are not allowed"
                  //onChange={e => setFirstName(e.target.value)}
                  onChange={(e) =>
                    setRegisterInfo({
                      ...registerInfo,
                      firstName: e.target.value,
                    })
                  }
                />
              </div>
            </div>
            <div className="col-md-6">
              <div className="form-group">
                <input
                  type="text"
                  id="lastName"
                  className="fadeIn second"
                  name="lastName"
                  placeholder="Last Name"
                  required
                  title="Special characters and numbers are not allowed"
                  //onChange={e => setLastName(e.target.value)}
                  onChange={(e) =>
                    setRegisterInfo({
                      ...registerInfo,
                      lastName: e.target.value,
                    })
                  }
                />
              </div>
            </div>
          </div>

          <input
            type="email"
            id="login"
            className="fadeIn second"
            name="login"
            placeholder="Email"
            required
            //onChange={e => setEmail(e.target.value)}
            onChange={(e) =>
              setRegisterInfo({
                ...registerInfo,
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
            //onChange={e => setPassword(e.target.value)}
            onChange={(e) =>
              setRegisterInfo({
                ...registerInfo,
                password: e.target.value,
              })
            }
          />
          <input type="submit" className="fadeIn fourth" value="Submit" />
        </form>

        <div id="formFooter">
          <Link to="/login" className="underlineHover">
            Already have an account? Login now !
          </Link>
        </div>
      </div>
    </div>
  );
};

export default Register;
