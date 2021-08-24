import { BrowserRouter, Route, Switch } from "react-router-dom";
import "./App.css";
import Nav from "./components/Nav";
import Home from "./components/Home";
import Admin from "./components/Admin";
import Login from "./components/Login";
import Register from "./components/Register";
import React, { useState, useEffect } from "react";
import UserService from "./services/UserService";


const App = () => {
  const [user, setUser] = useState({});
  
  useEffect(() => {
    updateUserState();
  }, []);

  const updateUserState = () => {
    if (localStorage.getItem("userId") && localStorage.getItem("token")) {
      UserService.getUserByUserId(localStorage.getItem("userId"))
        .then((res) => {
          setUser(res.data);
          
        })
        .catch((err) => {
          console.log(err.response.data);
        });
    } else setUser(null);
  };

  return (
    <div className="App">
      <BrowserRouter>
        <Nav user={user} setUser={setUser} />
        <main className="form-signin pt-5">
          <Switch>
            <Route
              path="/"
              exact
              component={() => <Home user={user} setUser={setUser} />}
            />
            <Route
              path="/login"
              component={() => <Login user={user} setUser={setUser} />}
            />
            <Route path="/register" component={Register} />
            <Route
              path="/admin"
              exact
              component={() => <Admin user={user} setUser={setUser} />}
              
            />
            <Route
              path="/admin/staffs"
              exact
              component={() => <Admin user={user} setUser={setUser} />}
              
            />
          </Switch>
        </main>
      </BrowserRouter>
    </div>
  );
};

export default App;
