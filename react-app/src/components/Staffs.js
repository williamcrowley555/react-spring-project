import React, { useState, useEffect, useRef } from "react";
import Table from "./Table";
import UserService from "../services/UserService";
import Modal from "./Modal";
import socket from "../socketIO/socket";
import Swal from 'sweetalert2'

const Staffs = () => {
  const [staffList, setStaffList] = useState([
    {
      userId: "",
      firstName: "",
      lastName: "",
      email: "",
    },
  ]);

  const [selectedData, setSelectedData] = useState({});
  const [showModal, setShowModal] = useState(false);
  const userRoom = console.log(selectedData);

  useEffect(() => {
    let isUpdated = false;
    socket.on("connect", () => {
      socket.emit("join-user-room-topic", "");

      socket.on("user-topic", (user) => {
          // setStaffList((prevStaffList) => 
          // [...prevStaffList, user]);
          
          setStaffList((prevStaffList) => 
          {
            let found = false
            let oldList = prevStaffList
            let updatedList = prevStaffList.map(staff => {
              if(staff.userId === user.userId)
              {
                found = true
                staff = user
              }
              return staff;
            })
            if(found)
              return updatedList;
            else  
              return [...oldList, user];
            
          })
        }
      );
    });

    getUserList("staff");
    return () => {
      isUpdated = true;
    };
  }, []);

  const getUserList = (role) => {
    if (localStorage.getItem("userId") && localStorage.getItem("token")) {
      UserService.getUserByUserRoleName(role)
        .then((res) => {
          setStaffList(res.data);
        })
        .catch((err) => {
          console.log(err.response.data);
        });
    }
  };

  const onEdit = (record) => {
    setSelectedData(record);
    openModal("edit");
  };

  const onDelete = (record) => {
    setSelectedData(record);
    console.log("Delete: " + record)
    // handle delete function
    UserService.deleteUser(record.userId);
  };

  const columns = [
    {
      name: "First Name",
      selector: "firstName",
      sortable: true,
    },
    {
      name: "Last Name",
      selector: "lastName",
      sortable: true,
    },
    {
      name: "Email",
      selector: "email",
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

            <button
              className="btn btn-danger btn-sm m-2"
              onClick={() => {
                let selectedUserFullname = record.lastName + ' ' + record.firstName
                Swal.fire({
                  title: 'Confirm Delete',
                  text: 'Do you really want to Delete user ' + selectedUserFullname,
                  showCancelButton: true,  
                  confirmButtonColor: '#3085d6',  
                  cancelButtonColor: '#d33',  
                  confirmButtonText: 'OK'  
              }).then(result => {
                  // when confirmed 
                  onDelete(record)
              }).catch(error => {
                  // when canceled
              });
              }}
            >
              Delete
            </button>
          </>
        );
      },
    },
  ];

  const openModal = (action) => {
    if (action === "edit") setShowModal((prev) => !prev);
    else {
      setSelectedData({});
      setShowModal((prev) => !prev);
    }
  };

  return (
    <div id="content">
      <div className="container-fluid">
        <h1>Staff Management</h1>
        <button
          type="button"
          className="btn btn-success mt-2"
          onClick={openModal}
        >
          <i className="fas fa-plus"></i> Add Staff
        </button>

        <Modal
          id="Modal"
          showModal={showModal}
          setShowModal={setShowModal}
          selectedData={selectedData}
        />
      </div>

      <Table data={staffList} columns={columns} />
    </div>
  );
};

export default Staffs;
