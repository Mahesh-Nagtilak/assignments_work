import React, { useEffect, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import FlightService from '../services/FlightService'
import Footer from './Footer'
import Navbar from './Navbar'

const ListFlightsComponent = () => {

    const [flights, setFlights] = useState([])
    const navigate = useNavigate();
    let fileDownload = require('js-file-download');
    useEffect(() => {
        getAllFlights();
        sessionStorage.setItem("flightNumber",flights.flightNumber)
    }, )  

    const getAllFlights = () => {
        FlightService.getAllFlights().then((response) => {
            setFlights(response.data)
            console.log(response.data);
        }).catch(error =>{
            console.log(error);
        })
    }

    const deleteFlight = (flightNumber) => {
        FlightService.deleteFlight(flightNumber).then(() =>{
            getAllFlights();
 
        }).catch(error =>{
            console.log(error);
        })
         
     }

     const downloadFlightDetails=(e)=>{
        e.preventDefault();
        FlightService.exportFlightDetails().then((response)=>{
            fileDownload(response.data,"Flight Details.xlsx");
        }).catch(error=>{
            console.log(error);
        })
     }

  return (<><Navbar/>
    <div className = "container">
        
            <h2 className = "text-center"> Flights List </h2>
            <Link to = "/add-flight" className = "btn btn-primary mb-2" > Add Flight </Link>
            
            <button className = "btn btn-primary" onClick = {downloadFlightDetails}
            style = {{marginLeft:"10px",marginBottom:"10px"}}> Generate report</button>
            <table className="table table-bordered table-striped">
                <thead className='text-center'>
                    <tr>
                        <th> Flight Number </th>
                        <th> Flight Name </th>
                        <th> Flight Type Name </th>
                        <th> Total Seats</th>
                        {/* <th> Stops </th> */}
                        <th> Action </th>
                    </tr>
                </thead>
                <tbody className='text-center'>
                    {
                        flights.map(
                            flight =>
                            <tr key = {flight.flightNumber}> 
                                <td> {flight.flightNumber} </td>
                                <td> {flight.flightName} </td>
                                <td> {flight.flightType} </td>
                                <td>{flight.totalSeats}</td>
                                {/* <td>{flight.stops}</td> */}
                                <td>
                                    <Link className="btn btn-info" to={`/edit-flight/${flight.flightNumber}`} >Update</Link>
                                    <button className = "btn btn-danger" onClick = {() => deleteFlight(flight.flightNumber)}
                                    style = {{marginLeft:"10px"}}> Delete</button>
                                    <button className='btn btn-success' style = {{marginLeft:"10px"}} onClick={()=>{
                                        sessionStorage.setItem("flightNumber",flight.flightNumber)
                                        sessionStorage.setItem("flightName",flight.flightName)
                                        sessionStorage.setItem("flightType",flight.flightType)
                                        sessionStorage.setItem("totalSeats",flight.totalSeats)
                                        // sessionStorage.setItem("stops",flight.stops)
                                        navigate(`/view-flightSchedule/${flight.flightNumber}`)
                                    }}>View</button>
                                </td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div><br/><Footer/></>
  )
}

export default ListFlightsComponent