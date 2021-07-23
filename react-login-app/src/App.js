import { BrowserRouter, Route, Switch } from 'react-router-dom';
import './App.css';
import Nav from './components/Nav';
import Home from './pages/Home';
import Login from './pages/Login';
import Register from './pages/Register';
import React, { useState, useEffect} from 'react'
import axios from 'axios';

const App = () => {

    const [user, setUser] = useState({});

    useEffect(() => {
        axios.get('api/users/'+localStorage.getItem('userId'))
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
    }, []);     

  return (
    <div className="App">
    <BrowserRouter> 
      <Nav user={user} setUser={setUser}/>
      <main className="form-signin">       
      <Switch>
          <Route path="/" exact component={() => <Home user = {user}/>}/>
          <Route path="/login" component={() => <Login setUser = {setUser}/>}/>
          <Route path="/register" component={Register}/>
      </Switch>
      </main>
    </BrowserRouter>
    </div>
  )
}

export default App

// function App() {
//   return (
//     <div className="App">
//     <BrowserRouter> 
//       <Nav />
//       <main className="form-signin">       
//       <Switch>
//           <Route path="/" exact component={Home}/>
//           <Route path="/login" component={Login}/>
//           <Route path="/register" component={Register}/>
//       </Switch>
//       </main>
//       </BrowserRouter>
//     </div>
//   );
// }
// //note
// export default App;
