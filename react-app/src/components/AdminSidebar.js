import React from 'react'

const AdminSidebar = () => {
    return (
        <div id="sidebar">
                <header>
                <a href="/Admin">My App</a>
                </header>
                <ul className="nav">
                <li>
                    <a href="/Admin">
                    Dashboard
                    </a>
                </li>
                <li>
                    <a href="/Admin/Users">
                    Users Management
                    </a>
                </li>
                 
                </ul>
            </div>
    )
}

export default AdminSidebar
