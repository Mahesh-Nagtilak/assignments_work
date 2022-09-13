
import React, { useEffect, useState } from "react";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Paper from "@material-ui/core/Paper";
import Grid from '@mui/material/Grid'
import { Button, Radio } from "@mui/material";
import Navbar from "../Navbar";
import Box from '@mui/material/Box';
import axios from "axios";
import DeleteIcon from '@mui/icons-material/Delete';
 import FormControlLabel from '@mui/material/FormControlLabel';
 import Typography from '@mui/material/Typography'; 
 import TextField from '@mui/material/TextField';
import { useForm } from "react-hook-form";
import AccountCircle from '@mui/icons-material/AccountCircle';
import InputAdornment from '@mui/material/InputAdornment';
import LocalLibraryIcon from '@mui/icons-material/LocalLibrary';
import AttributionIcon from '@mui/icons-material/Attribution';
import PersonAddIcon from '@mui/icons-material/PersonAdd';
import AirplaneTicket from "@mui/icons-material/AirplaneTicket";
import { toast } from 'react-toastify';
import { useNavigate } from "react-router-dom";
import RadioGroup from '@mui/material/RadioGroup';

import FormControl from '@mui/material/FormControl';
import FormLabel from '@mui/material/FormLabel';

export default function AddPassenger() {
 const user=JSON.parse(sessionStorage.getItem("user"));  //later replace with session
 const flightSearchObj=JSON.parse(sessionStorage.getItem("flightSearchObj"));

    const [passengers, setPassengers] = useState([]);
    
 const navigate=useNavigate();
    const {register, handleSubmit,formState: {
           errors
       }} = useForm();


   const onSubmit =(data)=> {
  
   const passengerDto ={
   firstName:data.firstName,
    lastName:data.lastName,
    gender:data.gender,
    age:data.age,
   passportNumber:data.passportNumber,
    userId:user.userId   
}     

if(flightSearchObj.fareType=="seniorCitizen" && data.age<61)
{
  toast.error("Senior citizen age should be above 60") 
}
else
{

if( data.age>18)
{  if(flightSearchObj.adult<1)
  {
    toast.info("Don't Add Adult..!!")
  }
  else{
  axios.get("http://localhost:8091/api/passenger/getAdultPassengerbyUser/"+user.userId)
    .then(response => {
     
     
      
      if(response.data<flightSearchObj.adult){
        
        axios.post("http://localhost:8091/api/passenger/addPassenger", passengerDto, { headers: { "Content-Type": "application/json", }, })
        .then((res) => {

         window.location.reload(false);
        })
        .catch((err) =>{
          console.log(err.response.request.status);
          
        } );

      }
      else
      {
        toast.info(" You have Already added "+flightSearchObj.adult +" Adult passengers");
      }
    
     })
    .catch(error => {   alert(error.message);
      console.log(error.message)})
    }
}
  

   if( data.age<=2)
   {
    if(flightSearchObj.infant<1)
    {
      toast.info("Don't Add Infant..!!")
    }
    else{
     axios.get("http://localhost:8091/api/passenger/getInfantPassengerbyUser/"+user.userId)
       .then(response => {
      
          
         
         if(response.data<flightSearchObj.infant){
           
           axios.post("http://localhost:8091/api/passenger/addPassenger", passengerDto, { headers: { "Content-Type": "application/json", }, })
           .then((res) => {
           window.location.reload(false);
          
           
           })
           .catch((err) =>{
             console.log(err.response.request.status);
             
           } );
   
         }
         else
         {
          toast.info(" You have Already added "+flightSearchObj.infant +" infant passengers");
         }
       
        })
       .catch(error => {   alert(error.message);
         console.log(error.message)})
       }
  }
  


    if( data.age<=18 && data.age>2)
    {
      if(flightSearchObj.children<1)
      {
        toast.info("Don't Add children..!!")
      }
      else{
         axios.get("http://localhost:8091/api/passenger/getChildrenPassengerbyUser/"+user.userId)
           .then(response => {
            
            
            
             if(response.data<flightSearchObj.children){
               
               axios.post("http://localhost:8091/api/passenger/addPassenger", passengerDto, { headers: { "Content-Type": "application/json", }, })
               .then((res) => {
                window.location.reload(false);
               })
               .catch((err) =>{
                 console.log(err.response.request.status);
                 
               } );
       
             }
             else
             {
              toast.info(" You have Already added "+flightSearchObj.children +"children passengers");
             }
           
            })
           .catch(error => {   alert(error.message);
             console.log(error.message)})
       
           }
    }
        }
          
}
  const removePassenger=(id)=>
  {
    axios.delete("http://localhost:8091/api/passenger/deletePassenger/"+id)
    .then((response) => {
    
     window.location.reload(false);
    })
    .catch((err) =>{
      console.log(err.response.request.status);
      
    } );}
  
  const doPayment=()=>
  {   
    
    axios.get("http://localhost:8091/api/passenger/getAdultPassengerbyUser/"+user.userId)
    .then(response => {  
      if(response.data<flightSearchObj.adult)
      {  
        toast.info((flightSearchObj.adult-response.data )+" more Adult needs to add to proceed..!!")
        
      }
      else{
        axios.get("http://localhost:8091/api/passenger/getChildrenPassengerbyUser/"+user.userId)
        .then(response => {  
          if(response.data<flightSearchObj.children)
          {   
            toast.info((flightSearchObj.children-response.data )+" more children needs to add to proceed..!!");
            
          } 
          else
          {
            axios.get("http://localhost:8091/api/passenger/getInfantPassengerbyUser/"+user.userId)
            .then(response => {  
              if(response.data<flightSearchObj.infant)
              {  
                toast.info((flightSearchObj.infant-response.data )+" more Infant needs to add to proceed..!!");
              }
              else
              {
                navigate("/paymentPage");
              }
              
            }).catch(error=>console.log(error.data));
             
          
          }
        }).catch(error=>console.log(error.data));
         
      }

    }).catch(error=>console.log(error.data));
     
  }
  
    useEffect(
        () => {
          
            axios.get("http://localhost:8091/api/passenger/getAllPassengerbyUser/"+user.userId)
                .then(response => setPassengers(response.data) )
                .catch(error => {  console.log(error.message)})

        }, []
    )
 return (<>  <Navbar/>
   <Box
      sx={{
        display: 'block',
       ' margin-left': 'auto',
        'margin-right': 'auto',
        'width': '40%',
        
       
      }}
    >
    <React.Fragment>
      <Typography variant="h6"  gutterBottom><br/>
      <InputAdornment position="start">
                  <PersonAddIcon /> <b>Passengers</b>
                </InputAdornment> 
      </Typography><br/>
      <Box component="form"  onSubmit={handleSubmit(onSubmit)} sx={{ mt: 0, width: '80%' }}>
                         
      <Grid container spacing={3}>
        <Grid item xs={12} sm={6}>
        <TextField
          
            id="firstName"
            name="firstName"
            label="First name"
            fullWidth
            autoComplete="given-name"
            variant="outlined"
            InputProps={{
              startAdornment: (
                <InputAdornment position="start">
                  <AccountCircle />
                </InputAdornment>
              ),
            }}
            {...register("firstName",{required : true})}
            /> {  errors.firstName ?. type === "required" && <Box sx={{ color: 'error.main' }}>Please Enter  First Name </Box> }
         
         
        </Grid>

        <Grid item xs={12} sm={6}>
          <TextField
           
            id="lastName"
            name="lastName"
            label="Last name"
            fullWidth
            autoComplete="family-name"
            variant="outlined"
            InputProps={{
              startAdornment: (
                <InputAdornment position="start">
                  <AccountCircle />
                </InputAdornment>
              ),
            }}
            {...register("lastName",{required : true})}
          /> {  errors.lastName ?. type === "required" && <Box sx={{ color: 'error.main' }}>Please Enter  Last Name </Box> }
       
        </Grid>

        <Grid item xs={12} sm={6}>
          <TextField
           
            id="age"
            name="age"
            label="Age"
            fullWidth
            type="number"
            autoComplete="Age"
            variant="outlined"
            InputProps={{
              startAdornment: (
                <InputAdornment position="start">
                  <AttributionIcon />
                </InputAdornment>
              ),
            }}
            {...register("age",{required : true,min:1,max:100})}
          />                             
          {  errors.age ?. type === "required" && <Box sx={{ color: 'error.main' }}>Please Enter  Age </Box> }
          {  errors.age ?. type === "min" &&  <Box sx={{ color: 'error.main' }}>Age should be above 1 year</Box> }
          {  errors.age ?. type === "max" &&  <Box sx={{ color: 'error.main' }}>Age should be below 100 years</Box> }
              
        </Grid>
     
        <Grid item xs={12} sm={6}>
          <TextField
           
            id="passportNumber"
            name="passportNumber"
            label="passport Number"
            fullWidth
            autoComplete="passportNumber"
            variant="outlined"
            InputProps={{
              startAdornment: (
                <InputAdornment position="start" >
                  <LocalLibraryIcon />
                </InputAdornment>
              ),
            }}
            {...register("passportNumber")}
          />
        </Grid>
        <FormControl>
        <FormLabel id="demo-row-radio-buttons-group-label">Gender</FormLabel>
        <RadioGroup
        row
        aria-labelledby="demo-row-radio-buttons-group-label"
     
        
      >
       
       <FormControlLabel value="male"   control={<Radio />} label="Male"  {...register("gender",{required:true})}/>
        <FormControlLabel value="female"   control={<Radio />} label="Female"   {...register("gender",{required:true})} />
        <FormControlLabel value="other"   control={<Radio />} label="Other"  {...register("gender",{required:true})}/>
     
       
       
      </RadioGroup>
      {  errors.gender ?. type === "required" && <Box sx={{ color: 'error.main' }}>Select Gender </Box> }
</FormControl>
      </Grid><br/><Button variant="contained" type="submit" color="primary" startIcon={< PersonAddIcon />}>
Add Passenger
</Button></Box> 
    </React.Fragment>
</Box>
<br/>
<Box    sx={{ mt: 0, width: '80%',marginLeft:'auto',marginRight:'auto' }}>
   <TableContainer component={Paper} >
     <Table aria-label="simple table">
       <TableHead className="bg-warning">
         <TableRow>
        
           <TableCell align="left" >First Name</TableCell>
           <TableCell align="left">Last Name</TableCell>
           <TableCell align="left">Age</TableCell>
           <TableCell align="left">Passport Number</TableCell>
           <TableCell align="left">Gender</TableCell>
           <TableCell align="left">Remove</TableCell>
          
         </TableRow>
       </TableHead>
       <TableBody  className="bg-info">
      
         { passengers.length?passengers.map((passenger,index) => (
           <TableRow  key={index}>
          
             <TableCell component="th" scope="row">{passenger.firstName} </TableCell>  
             <TableCell align="left">{passenger.lastName}</TableCell>
             <TableCell align="left">{passenger.age}</TableCell>
             <TableCell align="left">{passenger.passportNumber}</TableCell>
             <TableCell align="left">{passenger.gender}</TableCell>
             <TableCell align="left"><Button variant="contained" color="error" onClick={()=>removePassenger(passenger.passengerId)} startIcon={< DeleteIcon />}>
        Remove
      </Button></TableCell>
           </TableRow>
         )) : null}
       </TableBody>
       
     </Table>
   </TableContainer><br/>
   <Button variant="contained" type="submit" sx={{ml:110}} color="success" startIcon={< AirplaneTicket />} onClick={()=>doPayment()}>Book Ticket</Button>
</Box><br/></>
 );
}