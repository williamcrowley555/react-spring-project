import React, { useState } from 'react'
import axios from 'axios';
import { Redirect } from 'react-router'

const Login = ({ setUser }) => {

  const [loginInfo, setLoginInfo] = useState(
    {
      email: '',
      password: ''
    }
  )

  const GetAndUpdateUser = () => {
    const config = {
      headers: {
        Authorization: localStorage.getItem('tokenType') + localStorage.getItem('token')
      }
    };

    axios.get('api/users/' + localStorage.getItem('userId'), config)
      .then(
        res => {
          setUser(res.data)
        }
      )
      .catch(
        err => {
          console.log(err);
        }
      )
  }

  const [isLogedIn, setIslogedIn] = useState(false)

  const submit = async (e) => {
    e.preventDefault();
    axios.post("api/auth/signin", loginInfo)
      .then(
        res => {
          localStorage.setItem('token', res.data.accessToken)
          localStorage.setItem('tokenType', res.data.tokenType)
          localStorage.setItem('userId', res.data.userId)
          setIslogedIn(true)
          GetAndUpdateUser()
        }
      )
      .catch(
        err => {
          console.log(err);
        }
      )
  }

  if (isLogedIn) {
    return <Redirect to="/" />
  } else setUser(null)

  return (
    <div className="wrapper fadeInDown">
      <div id="formContent">

        <div className="fadeIn first">
          <img src="https://www.pngarts.com/files/5/User-Avatar-PNG-Transparent-Image.png" id="icon" alt="User Icon" />
        </div>

        <form onSubmit={submit} action="/">
          <input type="email" id="login" className="fadeIn second" name="login" placeholder="Email" required
            onChange={e =>
              setLoginInfo(
                prevState => (
                  {
                    ...prevState,
                    email: e.target.value,
                  }
                )
              )
            }
          />
          <input type="password" id="password" className="fadeIn third" name="password" placeholder="Password" required
            onChange={e =>
              setLoginInfo(
                prevState => (
                  {
                    ...prevState,
                    password: e.target.value
                  }
                )
              )
            }
          />
          <input type="submit" className="fadeIn fourth" value="Log In" />
        </form>

        <div id="formFooter">
          <a className="underlineHover" href="./login">Forgot Password?</a>
        </div>

      </div>
    </div>
  )
}

export default Login
