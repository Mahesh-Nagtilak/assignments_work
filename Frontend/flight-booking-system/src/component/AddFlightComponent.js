import React, {useState} from 'react'
import {Link, useParams } from 'react-router-dom';
import { useNavigate } from 'react-router';
import FlightService from '../services/FlightService';
import Navbar from './Navbar';

const AddFlightComponent = () => {

    const [flightNumber, setFlightNumber] = useState('')
    const [flightName, setFlightName] = useState('')
    const [flightType, setFlightType] = useState('')
    const [totalSeats, setTotalSeats] = useState('')
    

    const navigate = useNavigate();

    const addFlight = (e) => {
        e.preventDefault();

      
        const flight = {flightNumber, flightName, flightType, totalSeats}

        FlightService.addFlight(flight).then((response) =>{

            console.log(response.data)
            navigate("/getAllFlights");

            }).catch(error => {
                console.log(error)
            })
        
    }

    return (
        <div><Navbar/>
           <br /><br />
           <div className = "container">
                <div className = "row">
                    <div className = "card col-md-6 offset-md-3 offset-md-3">
                    <h2 className = "text-center">Add Flight</h2>
                        <div className = "card-body">
                            <form>
                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Flight Number:</label>
                                    <input
                                        type = "text"
                                        placeholder = "Enter flight number"
                                        name = "flightNumber"
                                        className = "form-control"
                                        value = {flightNumber}
                                        onChange = {(e) => setFlightNumber(e.target.value)}
                                        required
                                    >
                                    </input>
                                </div>

                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Flight Name:</label>
                                    <input
                                        type = "text"
                                        placeholder = "Enter flight name"
                                        name = "flightName"
                                        className = "form-control"
                                        value = {flightName}
                                        onChange = {(e) => setFlightName(e.target.value)}
                                        required
                                    >
                                    </input>
                                </div>

                                <div className="form-group mb-2">
                                    <label className="form-label">Flight Type: </label>
                                    <select class="form-control" id="form-label" name = "flightType" value = {flightType}  onChange = {(e) => setFlightType(e.target.value)}>
                                    <option name="economy">Economy</option>
                                    <option name="business">Business</option>
                                    <option name="premium">Premium</option>
                                    </select>
                                </div>

                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Total Seats:</label>
                                    <input
                                        type = "number"
                                        placeholder = "Enter total seats"
                                        name = "totalSeats"
                                        className = "form-control"
                                        value = {totalSeats}
                                        onChange = {(e) => setTotalSeats(e.target.value)}
                                        required
                                    >
                                    </input>
                                </div>

                               

                                <button className = "btn btn-success" onClick = {(e) => addFlight(e)} >Submit </button>
                                <Link to="/getAllFlights" className="btn btn-danger" style = {{marginLeft:"10px"}}> Cancel </Link>
                            </form>

                        </div>
                    </div>
                </div>

           </div>

        </div>
    )
}

export default AddFlightComponent
