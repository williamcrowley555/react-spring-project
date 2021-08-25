import React, { useRef, useState, useEffect, useCallback } from "react";
import styled from "styled-components";
import { MdClose } from "react-icons/md";
import { useSpring, animated } from "react-spring";
import UserService from "../services/UserService";

const Background = styled.div`
  width: 100%;
  height: 100%;
  position: fixed;
  transform: translate(-10%, -20%);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 9999;
`;
const ModalWrapper = styled.div`
  width: 400px;
  height: 500px;
  box-shadow: 0 5px 16px rgba(0, 0, 0, 0.2);
  background: #fff;
  color: #000;
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  z-index: 10;
  border-radius: 10px;
  text-align: center;
`;

const ModalContent = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  line-height: 1.8;
  color: #141414;
  p {
    margin-bottom: 1rem;
  }
  button {
    padding: 10px 40px;
    background: #141414;
    color: #fff;
    border: none;
    margin-top: 30px;
  }
`;

const CloseModalButton = styled(MdClose)`
  cursor: pointer;
  position: absolute;
  top: 20px;
  right: 20px;
  width: 32px;
  height: 32px;
  padding: 0;
  z-index: 10;
`;

const Modal = ({ showModal, setShowModal, selectedData }) => {
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);
  const [staffData, setStaffData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    roles: ["staff"],
  });
  let button = "Edit";
  let message = null;

  if (Object.keys(selectedData).length === 0) button = "Add";

  const modalRef = useRef();
  const animation = useSpring({
    config: {
      duration: 250,
    },
    opacity: showModal ? 1 : 0,
    transform: showModal ? `translateY(0%)` : `translateY(-100%)`,
  });

  const closeModal = (e) => {
    if (modalRef.current === e.target) {
      setShowModal(false);
    }
  };

  const keyPress = useCallback(
    (e) => {
      if (e.key === "Escape" && showModal) {
        setShowModal(false);
        console.log("I pressed");
      }
    },
    [setShowModal, showModal]
  );

  useEffect(() => {
    document.addEventListener("keydown", keyPress);
    return () => document.removeEventListener("keydown", keyPress);
  }, [keyPress]);

  useEffect(() => {
    setStaffData(selectedData);
    if (Object.keys(selectedData).length === 0)
      setStaffData({
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        roles: ["staff"],
      });
    setError(null);
    setSuccess(null);
  }, [selectedData]);

  const submit = async (e) => {
    e.preventDefault();

    if (button === "Add") {
      UserService.addUser(staffData)
        .then((res) => {
          setSuccess("Staff Added Successfully");
        })
        .catch((err) => {
          setError(err.response.data.message);
        });
    } else if (button === "Edit") {
      console.log(
        "Edit data: " +
          staffData.firstName +
          " " +
          staffData.lastName +
          " " +
          staffData.email
      );
    }

    setShowModal(true);
  };
  if (error) {
    message = (
      <div className="alert alert-danger mx-4" role="alert">
        {error}
      </div>
    );
  }
  if (success) {
    message = (
      <div className="alert alert-success mx-4" role="alert">
        {success}
      </div>
    );
  }

  return showModal ? (
    <Background onClick={closeModal} ref={modalRef}>
      <animated.div style={animation}>
        <ModalWrapper showModal={showModal}>
          <ModalContent>
            <div>
              <h1> Staff Info </h1>
              {message}
              <form onSubmit={submit}>
                <div className="row px-3">
                  <div className="col-md-6">
                    <div className="form-group">
                      <input
                        type="text"
                        id="firstName"
                        name="firstName"
                        value={staffData.firstName}
                        placeholder="First Name"
                        required
                        title="Special characters and numbers are not allowed"
                        onChange={(e) =>
                          setStaffData({
                            ...staffData,
                            firstName: e.target.value,
                          })
                        }
                      />
                    </div>
                  </div>
                  <div className="col-md-6">
                    <div className="form-group">
                      <input
                        type="text"
                        id="lastName"
                        name="lastName"
                        value={staffData.lastName}
                        placeholder="Last Name"
                        required
                        title="Special characters and numbers are not allowed"
                        //onChange={e => setLastName(e.target.value)}
                        onChange={(e) =>
                          setStaffData({
                            ...staffData,
                            lastName: e.target.value,
                          })
                        }
                      />
                    </div>
                  </div>
                </div>

                <input
                  type="email"
                  id="login"
                  name="login"
                  value={staffData.email}
                  placeholder="Email"
                  required
                  //onChange={e => setEmail(e.target.value)}
                  onChange={(e) =>
                    setStaffData({
                      ...staffData,
                      email: e.target.value,
                    })
                  }
                />

                <input
                  type="password"
                  id="password"
                  name="password"
                  placeholder="Password"
                  required
                  //onChange={e => setPassword(e.target.value)}
                  onChange={(e) =>
                    setStaffData({
                      ...staffData,
                      password: e.target.value,
                    })
                  }
                />

                <button type="submit">{button}</button>
              </form>
            </div>
          </ModalContent>
          <CloseModalButton
            aria-label="Close modal"
            onClick={() => setShowModal((prev) => !prev)}
          />
        </ModalWrapper>
      </animated.div>
    </Background>
  ) : null;
};

export default Modal;
