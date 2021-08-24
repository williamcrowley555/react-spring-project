import React, { useState, useEffect } from "react";
import Table from './Table'
import UserService from "../services/UserService";
import Modal from "./Modal"
const Users = () => {
    const [userList, setUserList] = useState([
        {
            id:"",
            firstName:"",
            lastName:"",
            email:""
        }
    ]);
    const [selectedData, setSelectedData] = useState({});
    const [showModal, setShowModal] = useState(false)
    console.log(selectedData);
    useEffect(() => {
        const getUserList = (page, limit) => {
          if (localStorage.getItem("userId") && localStorage.getItem("token")) {
            UserService.getUsers(page, limit)
              .then((res) => {
                setUserList(res.data)      
              })
              .catch((err) => {
                console.log(err.response.data);
              });
          }
        };
        getUserList(1, 10)
    }, []);
    
    
    const onEdit = (record) => {
        setSelectedData(record)
        openModal("edit")    
    };
    const columns = [
    {
        name: 'First Name',
        selector: 'firstName',
        sortable: true,
    },
    {
        name: 'Last Name',
        selector: 'lastName',
        sortable: true,
    },
    {
        name: 'Email',
        selector: 'email',
        sortable: true,
    },
    {
        key: "action",
        text: "Action",
        className: "action",
        width: 100,
        align: "left",
        sortable: false,
        cell: (record) => {
          return (
            <>
              <button
                className="btn btn-primary btn-sm"
                onClick={() => {
                  onEdit(record);
                }}
              >
                Edit
              </button>
            </>
          );
         },
    },
    ];

    const openModal = (type) => {
      if (type==="edit")
      setShowModal(prev => !prev)
      else{
        setSelectedData({})
        setShowModal(prev => !prev)
      } 
    }

    return (
        <div id="content">
                <div className="container-fluid">
                <h1>User Manager</h1>
                  <button type="button" className="btn btn-success mt-2" onClick={openModal}>
                    <i className="fas fa-plus"></i> Add An User
                  </button>
                  <Modal id='Modal' showModal={showModal} setShowModal={setShowModal} selectedData={selectedData} />
                </div>
                <Table data={userList} columns={columns}/>
        </div>
    )
}

export default Users

