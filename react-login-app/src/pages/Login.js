import React from 'react'

const Login = () => {
  return (
    // <form>
    //   <h1 className="h3 mb-3 fw-normal">Please sign in</h1>
    //   <input type="email" className="form-control" placeholder="Email" required />
    //   <input type="password" className="form-control" placeholder="Password" required />
    //   <button className="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
    // </form>
    <div className="wrapper fadeInDown">
      <div id="formContent">
       
        <div className="fadeIn first">
          <img src="https://www.pngarts.com/files/5/User-Avatar-PNG-Transparent-Image.png" id="icon" alt="User Icon" />
        </div>

        <form>
          <input type="email" id="login" className="fadeIn second" name="login" placeholder="Email" required/>
            <input type="password" id="password" className="fadeIn third" name="password" placeholder="Password" required/>
              <input type="submit" className="fadeIn fourth" value="Log In"/>
        </form>
     
        <div id="formFooter">
          <a className="underlineHover" href="./login">Forgot Password?</a>
        </div>
  
    </div>
  </div>
          )
}

          export default Login
