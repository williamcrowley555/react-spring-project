import { BrowserRouter, Route, Switch } from "react-router-dom";
import "./App.css";
import Nav from "./components/Nav";
import Home from "./components/Home";
import Admin from "./components/Admin";
import Login from "./components/Login";
import Register from "./components/Register";
import React, { useState, useEffect, useRef } from "react";
import UserService from "./services/UserService";
import io from 'socket.io-client';

const App = () => {
  const [user, setUser] = useState({});
  const socketRef = io('http://localhost:9092/chat?token=abc123', {
      transports: ['polling', 'websocket']
    });

    if(!socketRef){
      console.log('im hể')
      
    } else if (!socketRef.connected){
      console.log("Connection lost, connecting again...");
    
    }
    console.log(socketRef.connected)  
  useEffect(() => {
    
  //   socket.on('connect', function () {
  //     console.log('Connected')
  // });
  
  
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
