import React, { useState, useEffect } from "react";
import Table from './Table'
import UserService from "../services/UserService";
const Users = () => {
    const title =""
    const data = [{ id: 1, title: 'Conan the Barbarian', year: '1982' }, { id: 2, title: 'Conan the Barbarian', year: '1982' }];
    const [userList, setUserList] = useState([
        {
            id:"",
            firstName:"",
            lastName:"",
            email:""
        }
    ]);
    const [selectedData, setSelectedData] = useState({});

    useEffect(() => {
        getUserList(1, 10)
    }, []);
    const getUserList = (page, limit) => {
        if (localStorage.getItem("userId") && localStorage.getItem("token")) {
          UserService.getUsers(page, limit)
            .then((res) => {
               setUserList(res.data) 
               console.log(userList)       
            })
            .catch((err) => {
              console.log(err.response.data);
            });
        }
      };
    
    const onEdit = (record) => {
        setSelectedData(record)
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
    // {
    //     name: 'Title',
    //     selector: 'title',
    //     sortable: true,
    // },
    // {
    //     name: 'Year',
    //     selector: 'year',
    //     sortable: true,
    //     right: true,
    // },
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
                <h1>User Manager</h1>
                </div>
                <Table title={title} data={userList} columns={columns}/>
        </div>
    )
}

export default Users

