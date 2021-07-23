
import React from 'react'

const Home = ({user}) => {
    let message;
    if (user === null)
    {
        message = (
            <div>
                You are not logged in.
            </div>
        )
    } else {
        message = (
            <div>
            You are logged in welcome: {user.firstName} {user.email}
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
