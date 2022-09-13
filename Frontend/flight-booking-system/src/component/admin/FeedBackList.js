import Box from '@mui/material/Box';
import { DataGrid, GridToolbar } from '@mui/x-data-grid';
import axios from 'axios';
import React, { useEffect, useState } from 'react';
import Footer from '../Footer';
import Navbar from '../Navbar';

export const FeedBackList = () => {

   
  const headers = {
    'Content-Type': 'application/json',
     "Access-Control-Allow-Origin": "*",
  
  }

    const columns=
    [
        {field:'feedback', headerName:'Feedback',  width:300,},
        {field:'rating', headerName:'Rating', },
        {field:'source', headerName:'Source'},
        {field:'destination', headerName:'Destination',  },
        {field:'departureDate', headerName:'DepartureDate'},
        {field:'flightNumber', headerName:'FlightNumber' },
        {field:'firstName', headerName:'FirstName'},
        {field:'lastName', headerName:'LastName' },
        {field:'email', headerName:'email', width:250, },
    ]
    
    const[tableData,setTableData]=useState([]);
    useEffect(
        () => {
            axios.get("http://localhost:8078/api/feedback/feedback-list", { headers: headers })
                
                .then((response)=>{   console.log(response.data);
                    setTableData(response.data)})
                .catch(error => console.log(error))

        }, []
    )
  return (
   <><Navbar/>
    <Box
    sx={{
      height: 500,
      width: '100%',
      '& .super-app-theme--header': {
        backgroundColor: 'black',
      },
    }}
  >
<DataGrid  sx={{ m: 3 }} 
     rows={tableData}
    {...tableData}
    columns={columns}
    rowsPerPageOptions={[10, 15,20, 25,50]}
    pageSize={10}
    getRowId={() => Math.random()}
    components={{ Toolbar: GridToolbar  }}
   
    /></Box> <Footer/></>
    
  )
}
