import React, { useEffect, useState } from 'react'
import FlightService from '../services/FlightService'
import { useParams, Link} from 'react-router-dom'
import fileDownload from 'js-file-download'

const ListFlightsScheduleComponent = () => {

    const [flightSchedule, setFlightSchedule] = useState([])
    const {flightNumberId} = useParams();
    let fileDownload = require('js-file-download');
   
    useEffect(() => {
        getAllFlightsSchedule();
    }, [])

    const getAllFlightsSchedule = () => {
        FlightService.getAllFlightsSchedule(flightNumberId).then((response) => {
            console.log(response.data.flightSchedules);
            setFlightSchedule(response.data.flightSchedules);
            console.log(flightSchedule);
            // sessionStorage.setItem("flightScheduleId",flightSchedule.flightScheduleId);
            sessionStorage.setItem("id",flightSchedule.id);
        }).catch(error =>{
            console.log(error);
        })
    }

    // const deleteFlightSchedule = (flightScheduleId) => {
    //     FlightService.deleteFlightSchedule(flightScheduleId).then((response) =>{
    //         console.log(flightNumberId)
    //         console.log(response.data)
        
    //         getAllFlightsSchedule();
 
    //     }).catch(error =>{
    //         console.log(error);
    //     })
         
    //  }

    const deleteFlightSchedule = (id) => {
        FlightService.deleteFlightSchedule(id).then((response) =>{
            console.log(flightNumberId)
            console.log(response.data)
        
            getAllFlightsSchedule();
 
        }).catch(error =>{
            console.log(error);
        })
         
     }

     const downloadFlightScheduleDetails=(e)=>{
        e.preventDefault();
        FlightService.exportFlightScheduleDetails().then((response)=>{
            fileDownload(response.data,"Flight Schedule Details.xlsx");
        }).catch(error=>{
            console.log(error);
        })
     }

  return (
    <div className = "container">
            <h2 className = "text-center"> Flights Schedule List </h2>
           <Link to = {`/add-flightSchedule/${flightNumberId}`} className = "btn btn-primary mb-2" > Add Flight Schedule </Link>
           <button className = "btn btn-primary" onClick = {downloadFlightScheduleDetails}
            style = {{marginLeft:"10px",marginBottom:"10px"}}> Generate report</button>
            <table className="table table-bordered table-striped">
                <thead className='text-center'>
                    <tr>
                        {/* <th>Index</th> */}
                        <th>Source</th>
                        <th>Destination</th>
                        <th>Departure Date</th>
                        <th>Arrival Date</th>
                        <th>Departure Time</th>
                        <th>Arrival Time</th>
                        <th>Available Seats</th>
                        <th>Stops</th>
                        <th>Fare</th>
                        <th>Is Refundable</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody className='text-center'>
                    {
                        flightSchedule&&
                        flightSchedule.map(
                            flight =>
                            // <tr key = {flight.flightScheduleId}>
                            <tr key = {flight.id}> 
                                {/* <td>{flight.flightScheduleId}</td> */}
                                <td>{flight.source}</td>
                                <td>{flight.destination}</td>
                                <td>{flight.departureDate}</td>
                                <td>{flight.arrivalDate}</td>
                                <td>{flight.departureTime}</td>
                                <td>{flight.arrivalTime}</td>
                                <td>{flight.availableSeats}</td>
                                <td>{flight.stops}</td>
                                <td>{flight.fare}</td>
                                {/* <td>{flight.isRefundable}</td> */}
                                <td>{(String(flight.isRefundable)==="true")?<>Yes</>:<>No</>}</td>
                                {/* <td>
                                    <Link className="btn btn-info" to={`/edit-flightSchedule/${flight.flightScheduleId}`} >Update</Link>
                                    <button className = "btn btn-danger" onClick = {() => deleteFlightSchedule(flight.flightScheduleId)}
                                    style = {{marginLeft:"10px"}}> Delete</button>       
                                </td> */}
                                <td>
                                    <Link className="btn btn-info" to={`/edit-flightSchedule/${flight.id}`} >Update</Link>
                                    <button className = "btn btn-danger" onClick = {() => deleteFlightSchedule(flight.id)}
                                    style = {{marginLeft:"10px"}}> Delete</button>       
                                </td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
  )
}

export default ListFlightsScheduleComponent