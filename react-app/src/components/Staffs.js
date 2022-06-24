import React, { useState, useEffect, useRef } from "react";
import Table from "./Table";
import UserService from "../services/UserService";
import Modal from "./Modal";
import socket from "../socketIO/socket";


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

  console.log(selectedData);
  

  useEffect(() => {
    let isUpdated = false
    socket.on("updateUserList", (user) => {
      setStaffList((prevStaffList) => [...prevStaffList, user]);
    });

    getUserList("staff");
    return () => {
      isUpdated = true;
    }
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
