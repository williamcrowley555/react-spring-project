import React from 'react'
import DataTable from 'react-data-table-component';

const Table = ({data, columns }) => {
    return (
        <div>
           <DataTable
            className="dataTables_wrapper"
            columns={columns}
            data={data}
            Clicked
            pagination
            /> 
        </div>
    )
}

export default Table
