import React from 'react'

const Home = ({ user }) => {
    
    let message;
    if (user) {
        message = (
            <div className="pt-5">
                <div className="pt-4">
                You are logged in welcome: {user.firstName} {user.email}
                </div>
            </div>
        )
    } else {
        message = (
            <div className="pt-5">
                <div className="pt-4">
                You are not logged in.
                </div>
            </div>
        )
    }
    return (
        <div>
            {message}
        </div>
    )
}

export default Home
