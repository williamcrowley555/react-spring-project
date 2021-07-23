import axios from 'axios';
import React, {useState} from 'react'


const Register = () => {

    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    
    const submit = async (e) => {
        e.preventDefault();
        const data = {
            firstName: firstName,
            lastName: lastName,
            email: email,
            password: password
        }

        axios.post("api/auth/signup", data)
        .then(
            res => {
                console.log(res);
            }
        )
        .catch(
            err => {
                console.log(err);
            }
        )
    }
    return (
         
        <div className="wrapper fadeInDown">
            <div id="formContent">

                <div className="fadeIn first">
                    <img src="https://www.pngarts.com/files/5/User-Avatar-PNG-Transparent-Image.png" id="icon" alt="User Icon" />
                </div>

                <form onSubmit={submit}>
                    <div className="row px-3">
                        <div className="col-md-6">
                            <div className="form-group">
                                <input type="text" id="firstName" className="fadeIn second" name="firstName" placeholder="First Name" required
                                    pattern="^([A-Za-z]+[,.]?[ ]?|[A-Za-z]+['-]?)+$"
                                    title="Special characters and numbers are not allowed"
                                    onChange={e => setFirstName(e.target.value)}
                                />
                            </div>
                        </div>
                        <div className="col-md-6">
                            <div className="form-group">
                                <input type="text" id="lastName" className="fadeIn second" name="lastName" placeholder="Last Name" required
                                    pattern="^([A-Za-z]+[,.]?[ ]?|[A-Za-z]+['-]?)+$"
                                    title="Special characters and numbers are not allowed"
                                    onChange={e => setLastName(e.target.value)}
                                />
                            </div>
                        </div>
                    </div>


                    <input type="email" id="login" className="fadeIn second" name="login" placeholder="Email" required 
                            onChange={e => setEmail(e.target.value)}
                    />
                    <input type="password" id="password" className="fadeIn third" name="password" placeholder="Password" required 
                            onChange={e => setPassword(e.target.value)}
                    />
                    <input type="submit" className="fadeIn fourth" value="Submit" />
                </form>

                <div id="formFooter">
                    <a className="underlineHover" href="./login">Already have an account? Login now !</a>
                </div>

            </div>
        </div>
    )
}

export default Register
