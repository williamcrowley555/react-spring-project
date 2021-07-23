import { BrowserRouter, Route, Switch } from 'react-router-dom';
import './App.css';
import Nav from './components/Nav';
import Home from './pages/Home';
import Login from './pages/Login';
import Register from './pages/Register';
import React, { useState, useEffect } from 'react'
import axios from 'axios';



const App = () => {

    const [user, setUser] = useState({
        
    });

    const updateUserState = () => {
        if (localStorage.getItem('userId') || localStorage.getItem('token')) {
            axios.get('api/users/' + localStorage.getItem('userId'))
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
        } else setUser(null)
    }

    useEffect(() => {
        updateUserState()
    }, []);
   
    return (
        <div className="App">
            <BrowserRouter>
                <Nav user={user} setUser={setUser} />
                <main className="form-signin">
                    <Switch>
                        <Route path="/" exact component={() => <Home user={user} setUser={setUser}/>} />
                        <Route path="/login" component={() => <Login setUser={setUser} />} />
                        <Route path="/register" component={Register} />
                    </Switch>
                </main>
            </BrowserRouter>
        </div>
    )
}

export default App

