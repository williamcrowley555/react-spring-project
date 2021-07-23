import React, { useState } from 'react'
import axios from 'axios';
import { Redirect } from 'react-router'


const Login = ({setUser}) => {

  

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [isLogedIn, setIslogedIn] = useState(false)
  const submit = async (e) => {
    e.preventDefault();
    const data = {
      email: email,
      password: password
    }

    const config = {
      headers: {
        'Content-Type': 'application/json'
      }
    }
    

    axios.post("api/auth/signin", data, config)
      .then(
        res => {
          localStorage.setItem('token', res.data.accessToken)
          localStorage.setItem('tokenType', res.data.tokenType)
          //console.log(res.data)
          localStorage.setItem('userId', res.data.userId)
          setIslogedIn(true)
          setUser(res.data)
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
  }
  return (

    
    <div className="wrapper fadeInDown">
      <div id="formContent">

        <div className="fadeIn first">
          <img src="https://www.pngarts.com/files/5/User-Avatar-PNG-Transparent-Image.png" id="icon" alt="User Icon" />
        </div>

        <form onSubmit={submit} action="/">
          <input type="email" id="login" className="fadeIn second" name="login" placeholder="Email" required
            onChange={e => setEmail(e.target.value)}
          />
          <input type="password" id="password" className="fadeIn third" name="password" placeholder="Password" required
            onChange={e => setPassword(e.target.value)}
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
