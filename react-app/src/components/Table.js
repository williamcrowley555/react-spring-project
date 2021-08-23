import React from 'react'
import DataTable from 'react-data-table-component';

const Table = ({title, data, columns }) => {
    console.log("Data to table: " + data)
    return (
        <div>
           <DataTable
            className="dataTables_wrapper"
            title={title}
            columns={columns}
            data={data}
            Clicked
            pagination
            /> 
        </div>
    )
}

export default Table
