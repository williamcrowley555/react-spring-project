import React from 'react'

const AdminSidebar = () => {
    return (
        <div id="sidebar">
                <header>
                <a href="/admin">My App</a>
                </header>
                <ul className="nav">
                <li>
                    <a href="/admin">
                    Dashboard
                    </a>
                </li>
                <li>
                    <a href="/admin/users">
                    Users Management
                    </a>
                </li>
                 
                </ul>
            </div>
    )
}

export default AdminSidebar
