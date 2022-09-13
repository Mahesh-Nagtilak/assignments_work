
import FlightLandIcon from '@mui/icons-material/FlightLand';
import FlightTakeoffIcon from '@mui/icons-material/FlightTakeoff';
import { InputAdornment } from '@mui/material';
import axios from 'axios';
import React from 'react';
import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import '../css/searchFlight.css';
import Footer from '../Footer';
import Navbar from '../Navbar';

import "react-datepicker/dist/react-datepicker.css";

const myStyle=
{
  'background-image': 'url("https://pngimages.in/uploads/png/airplane_symbol.png")',
  'background-color':'#cccccc', /* Used if the image is unavailable */
  'height': 'auto', /* You must set a specified height */
  'background-position': 'center', /* Center the image */
  'background-repeat': 'no-repeat', /* Do not repeat the image */
  'background-size': 'cover' /* Resize the background image to cover the entire container */
 
}
export const SearchFlight = () => {
  
 
  const {register, handleSubmit} = useForm();

const navigate=useNavigate();
const onSubmit =data => {
 
  
var today = new Date();
var future = new Date(data.departureDate);
if(data.source===data.destination)
{
 
  toast.error("Source and Desination can't be Same!!");
}else if(Number(data.infant)> Number(data.adult))
{
  toast.error("Infant count cannot exceed adult count");
}
else if(data.fareType==="student" && (Number(data.children)>0 || Number(data.infant)>0))
{
  toast.error("Special Fare >> Only adult passengers can avail of Student fares. You may continue to book a regular fare or de-select all child/infant passengers to book the Student fare.");
}
else if(data.fareType==="seniorCitizen" && (Number(data.children)>0 || Number(data.infant)>0))
 {
  toast.error("Special Fare >> Only adult passengers can avail of Senior Citizen fares. You may continue to book a regular fare or de-select all child/infant passengers to book the Senior Citizen fare");
 
 }
 else if(today.getTime()>future.getTime())
 {
  toast.error("Select Fututre date..!!");
 }
 else
 {
  let passengerCount= Number(data.adult)+Number(data.children)+Number(data.infant);
  
 const checkAvailbilityObj={
  "source":data.source,
  "destination":data.destination,
  "departureDate":data.departureDate,
  "flightFlightType":data.flightFlightType,
  "availableSeats":passengerCount
} 
const user=JSON.parse(sessionStorage.getItem("user"));
const headers = {
  'Content-Type': 'application/json',
  // 'Access-Control-Allow-Methods': 'POST',
  
}


        axios.post("http://localhost:8091/api/search/searchFlight", checkAvailbilityObj,{
          headers: headers
        } )
            .then((response) => {
       
               if(response.data.length==0)
                  { 
                    toast.error("Flights are Not available!!");
                  }
                  else
                  {    
                   
                    console.log(response.data);
                    console.log(JSON.stringify(data));
                    sessionStorage.setItem("flightSearchObj",JSON.stringify(data));
                    sessionStorage.setItem("checkAvailbilityObj",JSON.stringify(checkAvailbilityObj));
                 
                    navigate("/availableFlights");
                    
                  }
            })
            .catch((err) =>{ console.log(err);
            } );
          }
    
};

  return (
  < div style={myStyle}>
  <Navbar/>
 <div>
    <div  className='wrapper  opacity-25' id="backGround">
    <form  onSubmit={handleSubmit(onSubmit)}>
    <b className='h3 text-dark'>Search  Flight</b>
        <div class="form-group  d-sm-flex margin"> <InputAdornment position="start" >
                  <FlightTakeoffIcon fontSize='large' />
                </InputAdornment><br/>
        <select class="form-select form-select-lg ms-2 mb-3 " required aria-label=".form-select-lg example"   {...register("source",{required : true})}>
  <option value="" >Source</option>
  <option value="solapur">solapur</option>
  <option value="pune">pune</option>
  <option value="Mumbai">Mumbai</option>
  <option value="Delhi">Delhi</option>
  <option value="kolkata">kolkata</option>
  <option value="nashik">Nashik</option>
  <option value="kanpur">Kanpur</option>
  <option value="nagpur">Nagpur</option>
  <option value="hyderabad">Hyderabad</option>
</select><br/>
<select  required class="form-select form-select-lg ms-2 mb-3" aria-label=".form-select-lg example"   {...register("destination",{required : true})}>
  <option value="" >Destination</option>
  <option value="pune">pune</option>
  <option value="Mumbai">Mumbai</option>
  <option value="Delhi">Delhi</option>
  <option value="Kolkata">Kolkata</option>
  <option value="nashik">Nashik</option>
  <option value="kanpur">Kanpur</option>
  <option value="hyderabad">Hyderabad</option>
</select>
 <InputAdornment position="end" >
                  <FlightLandIcon fontSize='large' />
                </InputAdornment> 

        </div>
        <div class="form-group d-sm-flex ms-2 margin">
        <div class="label h6 " id="Depart Date"> Departure Date</div>
           
                <input type="date"  placeholder="Depart Date" class="form-control" required {...register("departureDate")}/>
        </div>
      
               
<select required class="form-select form-select-lg ms-2  mb-3" aria-label=".form-select-lg example"   {...register("flightFlightType",{required : true})}>
  <option value="">Select Class Type</option>
  <option value="economy">Economy</option>
  <option value="business">Business</option>
  <option value="premium">Premium</option>
</select>
  <div class="form-group  d-sm-flex margin">
<select class="form-select form-select-lg ms-2 mb-3" required aria-label=".form-select-lg example"   {...register("adult",{required : true})}>
  <option  value="">Adult Count</option>
  <option value="1">1</option>
  <option value="2">2</option>
  <option value="3">3</option>
  <option value="4">4</option>
  <option value="5">5</option>
  <option value="6">6</option>
  <option value="7">7</option>
  <option value="8">8</option>
  <option value="9">9</option>
  <option value="10">10</option>
  <option value="11">11</option>
</select>
<select class="form-select form-select-lg  ms-2 mb-3"  aria-label=".form-select-lg example"   {...register("children")}>
  <option  value="">Children Count</option>
  <option value="1">1</option>
  <option value="2">2</option>
  <option value="3">3</option>
  <option value="4">4</option>
  <option value="5">5</option>
  <option value="6">6</option>
  <option value="7">7</option>
  <option value="8">8</option>
  <option value="9">9</option>
  <option value="10">10</option>
  <option value="11">11</option>
</select>
<select class="form-select form-select-lg  ms-2 mb-3"  aria-label=".form-select-lg example"   {...register("infant")}>
  <option  value="">Infant Count</option>
  <option value="1">1</option>
  <option value="2">2</option>
  <option value="3">3</option>
  <option value="4">4</option>
  <option value="5">5</option>
  <option value="6">6</option>
  <option value="7">7</option>
  <option value="8">8</option>
  <option value="9">9</option>
  <option value="10">10</option>
  <option value="11">11</option>
</select>
</div>
<div class="form-group  d-sm-flex margin" >
<div class="form-check ms-4">
  <input class="form-check-input" type="radio" name="fareType" id="flexRadioDefault1" value="regular" {...register("fareType")} checked />
  <label class="form-check-label" for="flexRadioDefault1">
  Regular Fares
  </label>
</div>
<div class="form-check ms-4">
  <input class="form-check-input" type="radio" name="fareType" id="flexRadioDefault2"  value="defence"  {...register("fareType")} />
  <label class="form-check-label" for="flexRadioDefault2">
   Armed Forces Fare
  </label>
</div>
<div class="form-check ms-4">
  <input class="form-check-input" type="radio" name="fareType" id="flexRadioDefault2"  value="student" {...register("fareType")} />
  <label class="form-check-label" for="flexRadioDefault2">
  Student Fare
  </label>
</div>
<div class="form-check ms-4">
  <input class="form-check-input" type="radio" name="fareType" id="flexRadioDefault2"  value="seniorCitizen"  {...register("fareType")} />
  <label class="form-check-label" for="flexRadioDefault2">
Senior Citizen Fare
  </label>
</div>
</div>
        <div class="form-group my-3">
            <button type="submit"class="btn btn-warning text-center p-3">Search Fligths
            </button>
        </div>
    </form>

</div> </div><Footer/></div>
  
 
  )
}
