import React from 'react'
import { Redirect } from "react-router";
import AdminSidebar from "./AdminSidebar";
import Dashboard from "./Dashboard";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import Staff from "./Staffs";

const Admin = ({ user, setUser }) => {
    if (localStorage.getItem("role") !== "ROLE_ADMIN")
    return <Redirect to="/" />
    return (
        <div id="viewport" className="">
            <AdminSidebar />
            <BrowserRouter>   
                <Switch>
                    <div className="px-4 pt-3">
                    <Route
                    path="/admin"
                    exact
                    component={() => <Dashboard user={user} setUser={setUser} />}
                    />

                    <Route
                    path="/admin/staffs"
                    exact
                    component={Staff}
                    />            
                    </div>  
                </Switch>             
            </BrowserRouter>  
        </div>
    )
}

export default Admin
