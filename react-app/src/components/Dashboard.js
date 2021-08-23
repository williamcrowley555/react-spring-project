import React from 'react'

const Dashboard = () => {
    return (
        <div id="content">
                <nav className="navbar navbar-default">
                <div className="container-fluid">
                    <ul className="nav navbar-nav navbar-right">
                    <li>
                        <a href="./"><i className="zmdi zmdi-notifications text-danger"></i>
                        </a>
                    </li>
                    </ul>
                </div>
                </nav>
                <div className="container-fluid mt-4">
                <h1>Admin Dashboard</h1>
                <p>
                    Make sure to keep all page content within the 
                    <code>#content</code>.
                </p>
                </div>
        </div>
    )
}

export default Dashboard
