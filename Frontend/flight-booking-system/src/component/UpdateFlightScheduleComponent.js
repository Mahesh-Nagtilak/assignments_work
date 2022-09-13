import React, {useState} from 'react'
import {Link, useParams } from 'react-router-dom';
import { useNavigate } from 'react-router';
import FlightService from '../services/FlightService';


const UpdateFlightScheduleComponent = () => {

   
    const [source, setSource] = useState('')
    const [destination, setDestination] = useState('')
    const [departureDate, setDepartureDate] = useState('')
    const [arrivalDate, setArrivalDate] = useState('')
    const [departureTime, setDepartureTime] = useState('')
    const [arrivalTime, setArrivalTime] = useState('')
    const [availableSeats, setAvailableSeats] = useState('')
    const [stops, setStops] = useState('')
    const [fare, setFare] = useState('')
    const [isRefundable, setIsRefundable] = useState('')
    // const {flightScheduleId} = useParams();
    const {id} = useParams();

    const flightNumberId = sessionStorage.getItem("flightNumber");
    

    const navigate = useNavigate();
   

    const addFlight = (e) => {
        e.preventDefault();
       
        let originInput = document.getElementById("source").value;
        let destinationInput = document.getElementById("destination").value;
        if (originInput == destinationInput) {
            alert("source and destination can't be the same")
        }

        // const flight = {flightScheduleId:flightScheduleId,flightNumber:flightNumberId,source, destination, departureDate, arrivalDate, departureTime, arrivalTime, availableSeats, fare}
        const flight = {id:id,flightNumber:flightNumberId,source, destination, departureDate, arrivalDate, departureTime, arrivalTime, availableSeats,stops, fare ,isRefundable}
       
        console.log(flight);

        // FlightService.updateFlightSchedule(flightScheduleId,flight).then((response) =>{

        // console.log(response.data)
        // navigate(`/view-flightSchedule/${flightNumberId}`);

        // }).catch(error => {
        //     console.log(error)
        // })

        FlightService.updateFlightSchedule(id,flight).then((response) =>{

            console.log(response.data)
            navigate(`/view-flightSchedule/${flightNumberId}`);
    
            }).catch(error => {
                console.log(error)
            })
    
    }


    return (
        <div>
           <br /><br />
           <div className = "container">
                <div className = "row">
                    <div className = "card col-md-6 offset-md-3 offset-md-3">
                    <h2 className = "text-center">Update Flight Schedule</h2>
                        <div className = "card-body">
                            <form>
                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Flight Scheudle ID:</label>
                                    <input
                                        type = "number"
                                        name = "flightNumber"
                                        className = "form-control"
                                        // value = {flightScheduleId}
                                        value = {id}
                                        disabled>
                                    </input>
                                </div>

                               

                                <div className="form-group mb-2">
                                    <label className="form-label">Source: </label>
                                    <select class="form-control" id="source" name = "source" value = {source}  onChange = {(e) => setSource(e.target.value)}>
                                    <option name="pune">Pune</option>
                                    <option name="nagpur">Nagpur</option>
                                    <option name="mumbai">Mumbai</option>
                                    <option name="delhi">Delhi</option>
                                    <option name="hyderabad">Hyderabad</option>
                                    </select>
                                </div>

                                <div className="form-group mb-2">
                                    <label className="form-label">Destination: </label>
                                    <select className="form-control" id="destination" name = "destination" value = {destination}  onChange = {(e) => setDestination(e.target.value)}>
                                    <option name="pune">Pune</option>
                                    <option name="nagpur">Nagpur</option>
                                    <option name="mumbai">Mumbai</option>
                                    <option name="delhi">Delhi</option>
                                    <option name="hyderabad">Hyderabad</option>
                                    </select>
                                </div>

                                <div className="form-group mb-2">
                                    <label className="form-label">Departure Date: </label>
                                    <input type="date" name="departureDate" className='form-control' value={departureDate} onChange = {(e) => setDepartureDate(e.target.value)} />
                                </div>

                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Arrival Date:</label>
                                    <input type="date" name="arrivalDate" className='form-control' value={arrivalDate} onChange = {(e) => setArrivalDate(e.target.value)} />
                                </div>

                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Departure Time:</label>
                                    <input type="time" step="1" name="departureTime" className='form-control' value={departureTime} onChange = {(e) => setDepartureTime(e.target.value)}  />
                                </div>

                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Arrival Time:</label>
                                    <input type="time" step="1" name="arrivalTime" className='form-control' value={arrivalTime} onChange = {(e) => setArrivalTime(e.target.value)}  />
                                </div>

                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Available Seats:</label>
                                    <input
                                        type = "number"
                                        placeholder = "Enter available seats"
                                        name = "availableSeats"
                                        className = "form-control"
                                        value = {availableSeats}
                                        onChange = {(e) => setAvailableSeats(e.target.value)}
                                        required>
                                    </input>
                                </div>

                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Stops:</label>
                                    <input
                                        type = "number"
                                        placeholder = "Enter stops"
                                        name = "stops"
                                        className = "form-control"
                                        value = {stops}
                                        onChange = {(e) => setStops(e.target.value)}
                                        required>
                                    </input>
                                </div>

                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Fare:</label>
                                    <input
                                        type = "number"
                                        placeholder = "Enter fare"
                                        name = "fare"
                                        className = "form-control"
                                        value = {fare}
                                        onChange = {(e) => setFare(e.target.value)}
                                        required>
                                    </input>
                                </div>

                                <div className="form-group mb-2">
                                    <label className="form-label">Is Refundable: </label>
                                    <select className="form-control" id="isRefundable" name = "isRefundable" value = {isRefundable}  onChange = {(e) => setIsRefundable(e.target.value)}>
                                    <option name="">Select from here</option>
                                    <option name="true">True</option>
                                    <option name="false">False</option>
                                    </select>
                                </div>

                                <button className = "btn btn-success" onClick = {(e) => addFlight(e)} >Submit </button>
                                <Link to={`/view-flightSchedule/${flightNumberId}`} className="btn btn-danger" style = {{marginLeft:"10px"}}> Cancel </Link>
                            </form>

                        </div>
                    </div>
                </div>

           </div>

        </div>
    )
}

export default UpdateFlightScheduleComponent
