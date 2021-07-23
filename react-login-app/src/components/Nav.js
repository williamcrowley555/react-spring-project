import React from 'react'
import { Link } from 'react-router-dom'

const Nav = ({ user, setUser }) => {

  const haddleLogout = () => {
    localStorage.clear();
    setUser(null);
  };

  let buttons;
  if (user) {
    console.log('logedin')
    buttons = (
      <ul className="navbar-nav me-auto mb-2 mb-md-0">
        <li className="nav-item">
          <Link to="/" onClick={haddleLogout} className="nav-link active" aria-current="page">Logout</Link>
        </li>
      </ul>
    )
  } else {
    console.log('logedout')
    buttons = (
      
      <ul className="navbar-nav me-auto mb-2 mb-md-0">
        <li className="nav-item">
          <Link to="/login" className="nav-link active" aria-current="page">Login</Link>
        </li>
        <li className="nav-item">
          <Link to="/register" className="nav-link active" aria-current="page">Register</Link>
        </li>
      </ul>
    )
  }
  return (
    <nav className="navbar navbar-expand-md navbar-dark bg-dark mb-4">
      <div className="container-fluid">
        <Link to="/" className="navbar-brand"> <i class="fas fa-home"></i> Home</Link>
        <div>
          {buttons}
        </div>
      </div>
    </nav>
  )
}

export default Nav
