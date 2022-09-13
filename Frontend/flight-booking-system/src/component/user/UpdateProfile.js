
import { Box, Button, TextField } from '@mui/material'
import React from 'react'
import Grid from '@mui/material/Grid';
import Navbar from '../Navbar'
import { useForm } from 'react-hook-form';
import axios from 'axios';
import { toast } from 'react-toastify';
export const UpdateProfile = () => {
    const { register, handleSubmit,  formState: {
        errors
    } } = useForm();
     
const user=JSON.parse(sessionStorage.getItem("user"));
 
const headers = {
  'Content-Type': 'application/json',
   "Access-Control-Allow-Origin": "*",
  
}
    const onSubmit = data => {
    
        axios.put("http://localhost:8091/api/userProfile/updateUser/"+user.userId, data,
        { headers:headers })
            .then((response) => {
               
               {   
                console.log(response);
             toast.success(response.data)
                }
               
               
            }).catch((err) =>{
                console.log(err);

            });

    
        }


  return (
    <>
  <Navbar/>
  <Box  
   noValidate 
          sx={{
            marginTop: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            border:'10px'
          }}
         
        > <h2>Update User Profile</h2><br/>  <Grid item xs={12} sm={6}  style={{ width: 400 }}> First Name:  <br/> <Box  
        >               
         <TextField
        required
        fullWidth
        id="firstName"
        label=" New First Name "
        name="firstName"
        autoComplete="family-name"
        sx={{backgroundColor:'white'}}
        {...register("firstName",{required : true})}
      
    />
    {errors.firstName ?. type === "required" && <Box sx={{ color: 'error.main' }}>Please Enter  First Name</Box>}
   <br/><br/>Last Name:  <br/><TextField
        required
        fullWidth
        type='lastName'
        id="lastName"
        label="New last Name"
        name="lastName"
        autoComplete="family-name"
       sx={{backgroundColor:'white'}}
        {...register("lastName",{required : true})}
      
    />
    {errors.lastName ?. type === "required" && <Box sx={{ color: 'error.main' }}>Please Enter  Last Name</Box>}
    
 
     <br/>  <br/>  <br/>
       <Button
              type="submit"
              fullWidth
              variant="contained"
              color='success'
            onClick={handleSubmit(onSubmit)}
             
            >
             Update Profile 
            </Button></Box></Grid>
</Box>
  </>
  )
}
