import React from 'react'

const Register = () => {
    return (
        //     <form>
        //     <h1 className="h3 mb-3 fw-normal">Please register</h1>
        //     <input className="form-control" placeholder="Name" required />
        //     <input type="email" className="form-control" placeholder="Email" required />
        //     <input type="password" className="form-control" placeholder="Password" required />
        //     <button className="w-100 btn btn-lg btn-primary" type="submit">Submit</button>
        //   </form>
        <div className="wrapper fadeInDown">
            <div id="formContent">

                <div className="fadeIn first">
                    <img src="https://www.pngarts.com/files/5/User-Avatar-PNG-Transparent-Image.png" id="icon" alt="User Icon" />
                </div>

                <form>
                    <div className="row px-3">
                        <div className="col-md-6">
                            <div className="form-group">
                                <input type="text" id="firstName" className="fadeIn second" name="firstName" placeholder="First Name" required
                                    pattern="^([A-Za-z]+[,.]?[ ]?|[A-Za-z]+['-]?)+$"
                                    title="Special characters and numbers are not allowed"
                                />
                            </div>
                        </div>
                        <div className="col-md-6">
                            <div className="form-group">
                                <input type="text" id="lastName" className="fadeIn second" name="lastName" placeholder="Last Name" required
                                    pattern="^([A-Za-z]+[,.]?[ ]?|[A-Za-z]+['-]?)+$"
                                    title="Special characters and numbers are not allowed"
                                />
                            </div>
                        </div>
                    </div>


                    <input type="email" id="login" className="fadeIn second" name="login" placeholder="Email" required />
                    <input type="password" id="password" className="fadeIn third" name="password" placeholder="Password" required />
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
