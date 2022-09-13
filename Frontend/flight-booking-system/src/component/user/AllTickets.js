import { Button } from '@mui/material';
import Box from '@mui/material/Box';
import { DataGrid, GridToolbar } from '@mui/x-data-grid';
import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

import Navbar from '../Navbar';
export const AllTickets = () => {
  const user = JSON.parse(sessionStorage.getItem("user"));
  const navigate = useNavigate();
  const headers = {
    'Content-Type': 'application/json',
    "Access-Control-Allow-Origin": "*",
  }

  const columns =
    [{ field: 'prnNumber', headerName: 'PRN Nuber' },
    { field: 'bookingId', headerName: 'BookingId' },
    { field: 'seatNumber', headerName: 'seatNumber' },
    { field: 'flightName', headerName: 'flightName' },
    { field: 'flightNumber', headerName: 'flightNumber' },
    { field: 'source', headerName: 'Source' },
    { field: 'destination', headerName: 'Destination', },
    { field: 'departureDate', headerName: 'DepartureDate' },
    { field: 'arrivalDate', headerName: 'ArrivalDate', },
    { field: 'departureTime', headerName: 'DepartureTime', },
    { field: 'flightClass', headerName: 'flightClass' },
    { field: 'firstName', headerName: 'firstName' },
    { field: 'lastName', headerName: 'lastName' },
    { field: 'gender', headerName: 'gender' },

    { field: 'age', headerName: 'age' },
    {
      field: "passengerId", headerName: 'CancelTicket',
      renderCell: (cellValue) => {
        return (
          <> {(new Date(cellValue.row.departureDate) > (new Date())) &&
            <Button
              variant="contained"
              color="error"
              onClick={() => {
                handleClick(cellValue);
              }}
            >
              cancel
            </Button>}</>
        );

      }

    },
    {
      field: "flightScheduleId", headerName: 'FeedBack',
      renderCell: (cellValue) => {
        return (
          <> {(new Date(cellValue.row.departureDate) < (new Date())) &&
            <Button
              variant="contained"
              color="success"
              onClick={() => {
                feedBack(cellValue);
              }}
            >
              FeedBack
            </Button>}</>
        );

      }

    }


    ]
  const handleCellClick = (event) => {
    event.stopPropagation();
  };

  const handleRowClick = (event) => {
    event.stopPropagation();
  };
  const handleClick = (id) => {
    console.log(id.row.passengerId);


    axios.delete("http://localhost:8091/api/passenger/cancelTicket/" + id.row.passengerId, { headers: headers })

      .then((response) => {
        console.log(response.data);
      })
      .catch(error => console.log(error))
    window.location.reload();
  }

  const [tableData, setTableData] = useState([]);
  useEffect(
    () => {
      axios.get("http://localhost:8091/api/passenger/getAllTickets/" + user.userId, { headers: headers })

        .then((response) => {
          console.log(response.data);
          setTableData(response.data)
        })
        .catch(error => console.log(error))

    }, []
  )
  const feedBack = (id) => {
    console.log(id.row.flightScheduleId);
    sessionStorage.setItem("flightScheduleId", JSON.parse(id.row.flightScheduleId));
    navigate("/feedback");
  }


  return (
    <><Navbar /><br />



      <Box
        sx={{
          height: 700,
          width: '100%',
          '& .super-app-theme--header': {
            backgroundColor: 'black',
          },
        }}
      >
        <DataGrid sx={{ m: 3 }}
          rows={tableData}
          {...tableData}
          columns={columns}
          rowsPerPageOptions={[10, 15, 20, 50]}
          pageSize={10}
          getRowId={() => Math.random()}
          components={{ Toolbar: GridToolbar }}

          onCellClick={(e) => handleCellClick}

          onRowClick={(e) => handleRowClick}

        /></Box>

    </>
  )
}
