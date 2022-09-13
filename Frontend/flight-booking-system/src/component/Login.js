import AccountCircle from '@mui/icons-material/AccountCircle';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import VpnKeyRoundedIcon from '@mui/icons-material/VpnKey';
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
import React, { useState } from 'react';

import './css/Login.css'
import Footer from './Footer';
import OtpValidation from './OtpVadlidation';

import { useForm } from 'react-hook-form';
import { toast } from 'react-toastify';

import SensorOccupied from '@mui/icons-material/SensorOccupied';
import Navbar from './Navbar';

const theme = createTheme();


function Login() {
    
    const { register, handleSubmit,  formState: {
        errors
    } } = useForm();
    
    const [message, setMessage] = useState("");
    const [open, setOpen] = useState(false);
    const [mail, setMail] = useState("");
  

    
    const onSubmit = data => {
        axios.post("http://localhost:8091/api/login/authentication", data,
            {
                headers: {
                    "Content-Type": "application/json",
                },
            })
            .then((response) => {
                console.log(response.data);
               
             toast.success("OTP has been sent to email")
              setMail(data.email);
              
                 setOpen(true);
                 
                
               
               
            }).catch((err) =>{
                setMessage(err.response.request.response);
           
            });

    
        }

    return (<>
        <div id='Login-div'> 
 {(open)?<OtpValidation emailId={mail}></OtpValidation>:
<div>
<Navbar/> 
       
            <div >
                <ThemeProvider theme={theme}>
                    <Container component="main" maxWidth="xs" sx={{   marginTop: -8,height: '40rem', display: 'flex', alignItems: 'center' }}>
                        <CssBaseline />
                       
                        <Box id="Login-card"
                        onSubmit={
                            handleSubmit(onSubmit)}
                           
                            sx={{
                              marginTop: 8,
                              display: 'flex',
                              flexDirection: 'column',
                              alignItems: 'center',
                            }}
                          >
                            <Avatar sx={{ mt: 4, mb: 2, bgcolor: 'primary.main' }}>
                                <LockOutlinedIcon />
                            </Avatar>
                            <Typography component="h1" variant="h6"  sx={{  color: 'secondary.main' }}>
                              Sign In
                            </Typography>
                            <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1, width: '80%' }}>
                                <TextField
                                    margin="normal"
                                    required
                                    fullWidth
                                    id="email"
                                    label="Email Address"
                                    name="email"
                                    autoComplete="email"
                                    variant="outlined"
                                    //   onChange={ ()=>setMessage(false)}
                                    {...register("email",{required : true})}
                                    size="large"
                                    sx={{ mt: 1 }}
                                    InputProps={{
                                        startAdornment: (
                                          <InputAdornment position="start">
                                            <AccountCircle />
                                          </InputAdornment>
                                        ),
                                      }}
                                />{
                                    errors.email?.type==="required" && <Box sx={{ color: 'error.main' }}>Please enter your Email</Box>
        
                                }
                                <TextField
                                    margin="normal"
                                    required
                                    fullWidth
                                    name="password"
                                    label="Password"
                                    type="password"
                                    id="password"
                                    autoComplete="current-password"
                                    variant="outlined"
                                    // onChange={ ()=>setMessage(false)}
                                    size="large"
                                    sx={{ mt: 1, my: 1 }}
                                    {...register("password",{required : true})}
                                      InputProps={{
                                        startAdornment: (
                                          <InputAdornment position="start">
                                            <VpnKeyRoundedIcon />
                                          </InputAdornment>
                                        ),
                                      }}
                                />
{
            errors.password?.type==="required" && <Box sx={{ color: 'error.main' }}>Please enter your Password </Box>
        
        }  
                   <Box sx={{ color: 'error.main' }}>{(message!=null)&&<span>{message}</span>} </Box>
                
     
                                <Button
                                    type="submit"
                                    variant="contained"
                                    color="warning"
                                    sx={{ mt: 3, mb: 2 ,ml: 11,color: 'black' }}
                                    startIcon={< SensorOccupied />}
                                >
                                    Send OTP
                                </Button>
                            <br/>Create  New Account<Button href="/signUp" >Sign Up</Button>
                            </Box>
                        </Box>
                    </Container>
                    
                </ThemeProvider> 
               </div>    
        
         
          </div>} </div><Footer /></>
    );
}

export default Login;