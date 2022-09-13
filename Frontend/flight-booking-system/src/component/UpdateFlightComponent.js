import React, {useState, useEffect} from 'react'
import {Link, useParams } from 'react-router-dom';
import { useNavigate } from 'react-router';
import FlightService from '../services/FlightService';

const UpdateFlightComponent = () => {

    const [flightNumber, setFlightNumber] = useState('')
    const [flightName, setFlightName] = useState('')
    const [flightType, setFlightType] = useState('')
    const [totalSeats, setTotalSeats] = useState('')
    // const [stops, setStops] = useState('')

    const navigate = useNavigate();
    const {flightNumberId} = useParams();

    const updateFlight = (e) => {
        e.preventDefault();
        setFlightNumber(flightNumberId);
        // const flight = {flightNumber, flightName, flightType, totalSeats, stops}
        const flight = {flightNumber, flightName, flightType, totalSeats}
        console.log(flightNumberId);

        FlightService.updateFlight(flightNumber, flight).then((response) => {
            console.log(response.data);
            navigate("/getAllFlights");
        }).catch(error => {
            console.log(error)
        })
    }


    useEffect(() => {

        FlightService.getFlightById(flightNumberId).then((response) =>{
            setFlightNumber(response.data.flightNumber)
            setFlightName(response.data.flightName)
            setFlightType(response.data.flightType)
            setTotalSeats(response.data.totalSeats)
            // setStops(response.data.stops)
        }).catch(error => {
            console.log(error)
        })
    }, [])
    return (
        <div>
           <br /><br />
           <div className = "container">
                <div className = "row">
                    <div className = "card col-md-6 offset-md-3 offset-md-3">
                        <h2 className = "text-center">Update Flight</h2>
                        <div className = "card-body">
                            <form>
                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Flight Number :</label>
                                    <input
                                        type = "text"
                                        name = "flightNumber"
                                        className = "form-control"
                                        value = {flightNumberId}
                                        disabled
                                    >
                                    </input>
                                </div>

                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Flight Name :</label>
                                    <input
                                        type = "text"
                                        placeholder = "Enter flight number"
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
                                    <option selected name="economy">Economy</option>
                                    <option name="business">Business</option>
                                    <option name="premium">Premium</option>
                                    </select>
                                </div>

                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Total Seats :</label>
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

                                {/* <div className = "form-group mb-2">
                                    <label className = "form-label"> Stops :</label>
                                    <input
                                        type = "number"
                                        placeholder = "Enter stops"
                                        name = "stops"
                                        className = "form-control"
                                        value = {stops}
                                        onChange = {(e) => setStops(e.target.value)}
                                        required
                                    >
                                    </input>
                                </div> */}

                                <button className = "btn btn-success" onClick = {(e) => updateFlight(e)} >Submit </button>
                                <Link to="/getAllFlights" className="btn btn-danger" style = {{marginLeft:"10px"}}> Cancel </Link>
                            </form>

                        </div>
                    </div>
                </div>

           </div>

        </div>
    )
}

export default UpdateFlightComponent
