import { Box, Button, TextField } from '@mui/material';
import Grid from '@mui/material/Grid';
import axios from 'axios';
import React from 'react';
import { useForm } from 'react-hook-form';
import { toast } from 'react-toastify';
import Navbar from '../Navbar';
export const UpdatePassword = () => {
    const { register, handleSubmit,  formState: {
        errors
    } } = useForm();
     
const user=JSON.parse(sessionStorage.getItem("user"));
 
const headers = {
    'Content-Type': 'application/json',
     "Access-Control-Allow-Origin": "*",
    
  }
 
    const onSubmit = data => {
    
        axios.put("http://localhost:8091/api/userProfile/updatePassword/"+user.userId, data,
        { headers:headers } )
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
         
        > <h2>Update User Paasword</h2><br/>  <Grid item xs={12} sm={6}  style={{ width: 400 }}> New password :  <br/><br/> <Box  
        >               
         <TextField
        required
        fullWidth
        id="password"
        label=" enter new password "
        name="password"
        autoComplete="family-name"
        sx={{backgroundColor:'white'}}
        {...register("password",{required : true})}
      
    />
    {errors.password ?. type === "required" && <Box sx={{ color: 'error.main' }}>please Enter  new password</Box>}
<br/><br/>
       <Button
              type="submit"
              fullWidth
              variant="contained"
              color='warning'
            onClick={handleSubmit(onSubmit)}
             
            >
             Update Password 
            </Button></Box></Grid>
</Box>
  </>
  )
}
