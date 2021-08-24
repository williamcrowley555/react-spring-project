import React, { useRef, useEffect, useCallback } from 'react';
import styled from "styled-components"
import { MdClose } from 'react-icons/md';
import { useSpring, animated } from 'react-spring';

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
    let button = "Edit"
    if (Object.keys(selectedData).length === 0) button = "Add"
    console.log(selectedData)
    if (selectedData === {}) button = "Add"
    const modalRef = useRef();
    const animation = useSpring({
        config: {
        duration: 250
        },
        opacity: showModal ? 1 : 0,
        transform: showModal ? `translateY(0%)` : `translateY(-100%)`
    });

    const closeModal = e => {
        if (modalRef.current === e.target) {
        setShowModal(false);
        }
    };

    const keyPress = useCallback(
        e => {
        if (e.key === 'Escape' && showModal) {
            setShowModal(false);
            console.log('I pressed');
        }
        },
        [setShowModal, showModal]
    );
    
    useEffect(
        () => {
          document.addEventListener('keydown', keyPress);
          return () => document.removeEventListener('keydown', keyPress);
        },
        [keyPress]
    );

    
   
    return showModal ? (
      
        <Background onClick={closeModal} ref={modalRef}>
          <animated.div style={animation}>
            <ModalWrapper showModal={showModal}>
            
            
              <ModalContent>
                {/* <h1>Are you ready?</h1>
                <p>Get exclusive access to our next launch.</p>
                <button>Join Now</button> */}
                <div >
                  <h1> Staff Info </h1>
                  <form>
                    <div className="row px-3">
                    <div className="col-md-6">
                      <div className="form-group">
                        <input
                          type="text"
                          id="firstName"
                          name="firstName"
                          value={selectedData.firstName}
                          placeholder="First Name"
                          required
                          title="Special characters and numbers are not allowed"
                          //onChange={e => setFirstName(e.target.value)}
                          
                        />
                      </div>
                    </div>
                    <div className="col-md-6">
                      <div className="form-group">
                        <input
                          type="text"
                          id="lastName"
                          name="lastName"
                          value={selectedData.lastName}
                          placeholder="Last Name"
                          required
                          title="Special characters and numbers are not allowed"
                          //onChange={e => setLastName(e.target.value)}
                          
                        />
                      </div>
                    </div>
                  </div>

                  <input
                    type="email"
                    id="login"
                    name="login"
                    value={selectedData.email}
                    placeholder="Email"
                    required
                    //onChange={e => setEmail(e.target.value)}
                    
                  />
                  
                  <button type="submit">{button}</button>
            </form>
                </div>     
              </ModalContent>
              <CloseModalButton
                aria-label='Close modal'
                onClick={() => setShowModal(prev => !prev)}
              />
            </ModalWrapper>
          </animated.div>
        </Background>
      ) : null
}

export default Modal
