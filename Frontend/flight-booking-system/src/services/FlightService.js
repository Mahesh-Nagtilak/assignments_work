import axios from 'axios'

const FLIGHT_BASE_REST_API_URL = 'http://localhost:8091/api/flight';

const headers = {
    'Content-Type': 'application/json',
     "Access-Control-Allow-Origin": "*",
  
  }
class FlightService{

    getAllFlights(){
        return axios.get(FLIGHT_BASE_REST_API_URL+"/"+"getAllFlights");
    }

    addFlight(flight){
        console.log(flight);
        return axios.post(FLIGHT_BASE_REST_API_URL+"/"+"addFlight", flight)
    }

    getFlightById(id){
        return axios.get(FLIGHT_BASE_REST_API_URL + '/' + id);
    }

    deleteFlight(flightNumber){
        return axios.delete(FLIGHT_BASE_REST_API_URL+"/"+"deleteFlight"+"/"+flightNumber);
    }

    updateFlight(flightNumber, flight){
        return axios.put(FLIGHT_BASE_REST_API_URL+"/"+"editFlight"+"/"+flightNumber, flight,{headers:headers});
    }

    getAllFlightsSchedule(flightNumber){
        return axios.get(FLIGHT_BASE_REST_API_URL+"/"+"findByFlightNumber"+"/"+flightNumber);
    }

    // deleteFlightSchedule(flightScheduleId){
    //     return axios.delete(FLIGHT_BASE_REST_API_URL+"/"+"deleteFlightSchedule"+"/"+flightScheduleId);
    // }

    deleteFlightSchedule(id){
        return axios.delete(FLIGHT_BASE_REST_API_URL+"/"+"deleteFlightSchedule"+"/"+id);
    }

    // updateFlightSchedule(flightScheduleId,flightSchedule){
    //     return axios.put(FLIGHT_BASE_REST_API_URL+"/"+"editFlightSchedule"+"/"+flightScheduleId,flightSchedule);
    // }

    updateFlightSchedule(id,flightSchedule){
        return axios.put(FLIGHT_BASE_REST_API_URL+"/"+"editFlightSchedule"+"/"+id,flightSchedule);
    }

    exportFlightDetails(){
        return axios.get(FLIGHT_BASE_REST_API_URL+"/"+"exportFlightDetails",{ responseType: 'blob'},{headers:headers});
       
    }

    exportFlightScheduleDetails(){
        return axios.get(FLIGHT_BASE_REST_API_URL+"/"+"exportFlightScheduleDetails",{ responseType: 'blob'},{headers:headers});
       
    }
}

export default new FlightService();