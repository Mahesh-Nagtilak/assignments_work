import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import PasswordIcon from '@mui/icons-material/Password';
import Avatar from '@mui/material/Avatar';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Container from '@mui/material/Container';
import CssBaseline from '@mui/material/CssBaseline';
import InputAdornment from '@mui/material/InputAdornment';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import axios from 'axios';
import React from 'react';

import './css/otpValidation.css'
import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';
import Navbar from './Navbar';

import LockOpen from '@mui/icons-material/LockOpen';

const theme = createTheme();
function OtpValidation(props) {
     
   
    const navigate = useNavigate();
   

    const { register, handleSubmit, formState: {
        errors
    } } = useForm();
    const onSubmit = data => {
      let otpValidation =
        { 
         otp:data.otp,
         email:props.emailId
        }
        axios.post("http://localhost:8091/api/login/validate", otpValidation,
            {
                headers: {
                    "Content-Type": "application/json",
                },
            })
            .then((response) => {
                console.log(response.data.email);
               
                
                 if(response.data.userRole==="ROLE_USER")
                 {
                    sessionStorage.setItem("user",JSON.stringify(response.data));
                 }
                 if(response.data.userRole==="ROLE_ADMIN")
                 {
                    sessionStorage.setItem("admin",JSON.stringify(response.data));
                 
                }
                 navigate('/home',{ replace: true });
            
               
            }).catch((err) =>{
                console.log(err + "Incorrect Data");
                alert(err.response.request.response);
               
            });

        }
  

    return (
        <>
         <Navbar /> 
            <div>
                <ThemeProvider theme={theme}>
                    <Container component="main" maxWidth="xs" sx={{ height: '20rem', display: 'flex', alignItems: 'center', pt: 5 ,mt:10}}>
                        <CssBaseline />

                        <Box id="Login-card"
                         onSubmit={
                            handleSubmit(onSubmit)}
                            sx={{
                                display: 'flex',
                                flexDirection: 'column',
                                alignItems: 'center',
                                justifyItems: 'center',
                             
                              
                                // width: '80%',
                            }}
                        >
                            <Avatar sx={{ mt: 4, mb: 2, bgcolor: 'primary.main', }}>
                                <LockOutlinedIcon />
                            </Avatar>
                            <Typography component="h1" variant="h6"  sx={{  color: 'secondary.main' }}>
                              Enter OTP to Login
                            </Typography>
                            <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1, width: '80%' }}>
                             
                                <TextField
                                    margin="normal"
                                    required
                                    fullWidth
                                    name="otp"
                                    label="otp"
                                    type="number"
                                    id="otp"
                                   
                                    variant="outlined"
                                   
                                    size="large"
                                    sx={{ mt: 1, my: 1 }}
                                    {...register("otp",{required : true,min:100000,max:999999})}
                                      InputProps={{
                                        startAdornment: (
                                          <InputAdornment position="start">
                                            <PasswordIcon />
                                          </InputAdornment>
                                        ),
                                      }}
                                />
{errors.otp ?.type === "required" && <Box sx={{ color: 'error.main' }}>Please Enter  OTP </Box> }
{errors.otp ?.type === "min" &&  <Box sx={{ color: 'error.main' }}>Please Enter 6 digit OTP</Box> }
{errors.otp ?.type === "max" &&  <Box sx={{ color: 'error.main' }}>Please Enter 6 digit OTP</Box> }
                                <Button
                                    type="submit"
                                    variant="contained"
                                    color="warning"
                                    sx={{ mt: 3, mb: 2, ml: 11, color: 'black' }}
                                    startIcon={< LockOpen />}
                                >
                                    Login
                                </Button>
                           
                            </Box>
                        </Box>
                    </Container>
                    
                </ThemeProvider>
            </div>
           
           </>
    );
}

export default OtpValidation;