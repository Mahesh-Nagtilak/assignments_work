import { Box, Button, TextareaAutosize, TextField } from '@mui/material';
import Grid from '@mui/material/Grid';
import axios from 'axios';
import React from 'react';
import { useForm } from 'react-hook-form';
import { toast } from 'react-toastify';
import Navbar from '../Navbar';
export const AddOffers = () => {
    const { register, handleSubmit,  formState: {
        errors
    } } = useForm();
     
   

    const onSubmit = data => {
    
        axios.post("http://localhost:8091/api/flightOffer/addOffer", data,
            {
                headers: {
                    "Content-Type": "application/json",
                },
            })
            .then((response) => {
               
               {   
                console.log(response);
             toast.success("Offer has been succesfully Added!!")
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
         
        >   <Grid item xs={12} sm={6}  style={{ width: 400 }}> Offer Name:  <br/> <Box  
        >               
         <TextField
        required
        fullWidth
        id="offerName"
        label="Offer Name"
        name="offer"
        autoComplete="family-name"
        sx={{backgroundColor:'white'}}
        {...register("offerName",{required : true})}
      
    />
    {errors.offerName ?. type === "required" && <Box sx={{ color: 'error.main' }}>Please Offer Name</Box>}
   <br/><br/>Offer Dicount Rate:  <br/><TextField
        required
        fullWidth
        type='number'
        id="discountRate"
        label="discountRate"
        name="discountRate"
        autoComplete="family-name"
      
       sx={{backgroundColor:'white'}}
        {...register("discountRate",{required : true})}
      
    />
    {errors.discountRate ?. type === "required" && <Box sx={{ color: 'error.main' }}>Please Offer Discount Rate</Box>}

            
    <br/><br/>Offer discription :  <br/>       <TextareaAutosize
    minRows={7}
    aria-label="maximum height"
    placeholder="write here"
  required
    style={{ width: 400 }}
    {...register("offerDescription",{required : true})}
                                  
    /> 

{errors.offerDescription ?. type === "required" && <Box sx={{ color: 'error.main' }}>Please Offer discription</Box>}
   
     <br/>  <br/>  <br/>
       <Button
              type="submit"
              fullWidth
              variant="contained"
              color='secondary'
            onClick={handleSubmit(onSubmit)}
             
            >
             Add Offer
            </Button></Box></Grid>
</Box>
  </>
  )
}
