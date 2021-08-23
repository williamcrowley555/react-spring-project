import React from 'react'
import { Redirect } from "react-router";
import AdminSidebar from "./AdminSidebar";
import Dashboard from "./Dashboard";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import Users from "./Users";

const Admin = ({ user, setUser }) => {
    if (localStorage.getItem("role") !== "ROLE_ADMIN")
    return <Redirect to="/" />
    return (
        <div id="viewport">
        
            <AdminSidebar />

            <BrowserRouter>   
                <Switch>
                    <Route
                    path="/Admin"
                    exact
                    component={() => <Dashboard user={user} setUser={setUser} />}
                    />

                    <Route
                    path="/Admin/Users"
                    exact
                    component={Users}
                    />

                     

                     
                    
                </Switch>
               
            </BrowserRouter>
            
            
        </div>
    )
}

export default Admin
