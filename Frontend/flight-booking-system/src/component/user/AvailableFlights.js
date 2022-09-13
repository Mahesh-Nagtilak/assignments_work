import CurrencyRupeeIcon from '@mui/icons-material/CurrencyRupee';
import DensitySmallIcon from '@mui/icons-material/DensitySmall';
import FlareIcon from '@mui/icons-material/Flare';
import LightModeIcon from '@mui/icons-material/LightMode';
import NightlightRoundIcon from '@mui/icons-material/NightlightRound';
import ReplyAllIcon from '@mui/icons-material/ReplyAll';
import WbTwilightIcon from '@mui/icons-material/WbTwilight';
import { Button, ButtonGroup } from "@mui/material";
import Box from '@mui/material/Box';
import { DataGrid, GridToolbar } from '@mui/x-data-grid';
import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useNavigate } from "react-router-dom";
import Navbar from '../Navbar';

export const AvailableFlights = () => {
 
  const headers = {
    'Content-Type': 'application/json',
     "Access-Control-Allow-Origin": "*",
  
  }
    const navigate=useNavigate();
  
    const checkAvailbilityObj= JSON.parse(sessionStorage.getItem("checkAvailbilityObj"));
    const columns=
    [
        {field:'flightFlightNumber', headerName:'FlightFlightNumber',  width: 140,},
        {field:'flightFlightName', headerName:'FlightFlightName',  width: 140,},
        {field:'source', headerName:'Source'},
        {field:'destination', headerName:'Destination',  width: 140,},
        {field:'departureDate', headerName:'DepartureDate',  width: 140,},
        {field:'arrivalDate', headerName:'ArrivalDate' ,  width: 140,},
        {field:'departureTime', headerName:'DepartureTime',  width: 140,},
        {field:'fare', headerName:'Fare',  },
        {field:'stops', headerName:'Stops', },
       
        {
            field: "id",headerName:'Book',
            renderCell: (cellValue) => {
              return (  
            
                <Button
                  variant="contained"
                  color="warning"
                  onClick={() => {
                    handleClick(cellValue);
                  }}
                >
                  Book
                </Button>
              );
            }
          
    }
       
     
    ]
    const handleCellClick = (event) => {
        event.stopPropagation();
      };
      
      const handleRowClick = ( event) => {
        event.stopPropagation();
      };
    const handleClick=(id)=>
       {  console.log(id.row.id); 
       
          const flightScheduleId=  id.row.id;
          const flightScheduleFare=  id.row.fare;
             sessionStorage.setItem("flightScheduleId",JSON.stringify(flightScheduleId));
             sessionStorage.setItem("flightScheduleFare",JSON.stringify(flightScheduleFare));
             navigate("/addPassenger");
       }
  
 
    const[tableData,setTableData]=useState([]);
    useEffect(
        () => {
            axios.post("http://localhost:8091/api/search/searchFlight",checkAvailbilityObj, { headers: headers })
                
                .then((response)=>{   console.log(response.data);
                    setTableData(response.data)})
                .catch(error => console.log(error))

        }, []
    )
   
    const flightSortByFare=()=>{
        axios.post("http://localhost:8091/api/search/flightSortByFare",checkAvailbilityObj, { headers:headers })
        .then(response => setTableData(response.data))
        .catch(error => alert("error while retriving data:" + error.message))

    }
    
    const flightByRefundableFare=()=>{
        axios.post("http://localhost:8091/api/search/flightByRefundableFare",checkAvailbilityObj, { headers:headers } )
        .then(response =>  setTableData(response.data))
        .catch(error => alert("error while retriving data:" + error.message))

    }
    
    const flightByMorning=()=>{
        axios.post("http://localhost:8091/api/search/flightByMorning",checkAvailbilityObj, { headers:headers })
        .then(response =>  setTableData(response.data))
        .catch(error => alert("error while retriving data:" + error.message))

    }
    const flightByAfterNoon=()=>{
        axios.post("http://localhost:8091/api/search/flightByAfterNoon",checkAvailbilityObj, { headers:headers })
        .then(response =>  setTableData(response.data))
        .catch(error => alert("error while retriving data:" + error.message))

    }
    const flightByEvening=()=>{
        axios.post("http://localhost:8091/api/search/flightByEvening",checkAvailbilityObj,{ headers:headers })
        .then(response =>  setTableData(response.data))
        .catch(error => alert("error while retriving data:" + error.message))

    }
    const flightByNight=()=>{
        axios.post("http://localhost:8091/api/search/flightByNight",checkAvailbilityObj, { headers:headers })
        .then(response =>  setTableData(response.data))
        .catch(error => alert("error while retriving data:" + error.message))
    }
    
  return (
    <><Navbar/><br/>
   
   <ButtonGroup variant="outlined" aria-label="outlined button group">
  <Button startIcon={< WbTwilightIcon/>} sx={{height:70}} onClick={()=>flightByMorning()}>Morning Flights</Button>
  <Button  startIcon={< LightModeIcon />} onClick={()=>flightByAfterNoon()}>AfterNoon Flights</Button>
  <Button  startIcon={< FlareIcon />} onClick={()=>flightByEvening()} >Evening Flights</Button>
  <Button  startIcon={< NightlightRoundIcon />} onClick={()=>flightByNight()} >Night Flights</Button>
  <Button  startIcon={< CurrencyRupeeIcon />} onClick={()=>flightSortByFare()} >Sort By Fare</Button>
  <Button startIcon={ <ReplyAllIcon/> } onClick={()=>flightByRefundableFare()} >Refundable Fare Flights</Button>
  <Button startIcon={ <DensitySmallIcon/> } onClick={()=>window.location.reload()} >  All Flights</Button>
</ButtonGroup>

 
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
    rowsPerPageOptions={[10,15, 20,50]}
    pageSize={10}
    getRowId={() => Math.random()}
    components={{ Toolbar: GridToolbar  }}
    onCellClick={(e)=>handleCellClick}
    
    onRowClick={(e)=>handleRowClick}
    /></Box> 
    
      </>
  )
}
