import { yupResolver } from '@hookform/resolvers/yup';
import AccountCircle from '@mui/icons-material/AccountCircle';
import AssignmentIndIcon from '@mui/icons-material/AssignmentInd';
import EmailRounded from '@mui/icons-material/EmailRounded';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import VpnKey from '@mui/icons-material/VpnKey';
import { InputAdornment } from '@mui/material';
import Avatar from '@mui/material/Avatar';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Container from '@mui/material/Container';
import CssBaseline from '@mui/material/CssBaseline';
import Grid from '@mui/material/Grid';
import Link from '@mui/material/Link';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import axios from 'axios';
import React from 'react';
import { useForm } from "react-hook-form";
import { useNavigate } from 'react-router-dom';
import * as Yup from 'yup';
import Navbar from './Navbar';
import './css/Registration.css'
import { toast } from 'react-toastify';
import Footer from './Footer';

const theme = createTheme();

export default function SignUp() {
   
  
    const formSchema = Yup.object().shape({
        firstName: Yup.string()
        .required('First Name is mandatory')
        .min(3, 'first name must be at 3 char long')
        .max(10, 'first name must be below 10 char'),
        lastName: Yup.string()
        .required('Last Name is mandatory')
        .min(3, 'Last Name must be at 3 char long')
        .max(10, 'Last name must be below 10 char'),
        email: Yup.string()
        .required('Email is mandatory'),
      
        password: Yup.string()
          .required('Password is mandatory')
          .min(6, 'Password must be at 6 char long')
          .max(15, 'Password can not be greater than 15 char'),
        confirmPwd: Yup.string()
          .required('Password is mandatory')
          .oneOf([Yup.ref('password')], 'Passwords does not match'),
      })
      const formOptions = { resolver: yupResolver(formSchema) }
      const { register, handleSubmit, formState } = useForm(formOptions)
      const { errors } = formState
      const navigate = useNavigate();
  
    const onSubmit =data => {
      

       
            axios.post("http://localhost:8091/api/registration", data, { headers: { "Content-Type": "application/json", }, })
                .then((response) => {
                
                   toast.success("Account Activation link has been send to Email")
                    navigate('/login');

                })
                .catch((err) =>{
                  console.log(err.response);
                  toast.error(err.response.data);
                   
                } );
          
        
    };

  return (<>
   
    <div id='Registration-div'> <Navbar/>
    <ThemeProvider theme={theme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline /> 
       
        <Box  id="Registration-card"
          sx={{
            marginTop: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5" sx={{  color: 'secondary.main' }}>
            Sign up
          </Typography>
          <Box component="form" noValidate  onSubmit={handleSubmit(onSubmit)} sx={{ mt: 1, width: '80%' }}>
            <Grid container spacing={2}>
              <Grid item xs={12} sm={6}>
                <TextField
                  autoComplete="given-name"
                  name="firstName"
                  required
                  fullWidth
                  id="firstName"
                  label="First Name"
                  autoFocus
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start">
                        <AccountCircle />
                      </InputAdornment>
                    ),
                  }}
                  {...register("firstName",{required : true})}
                                  
                                />  <Box sx={{ color: 'error.main' }}>{errors.firstName?.message}</Box>
        
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  required
                  fullWidth
                  id="lastName"
                  label="Last Name"
                  name="lastName"
                  autoComplete="family-name"
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start">
                        <AccountCircle />
                      </InputAdornment>
                    ),
                  }}
                  {...register("lastName",{required : true})}
                
              /> <Box sx={{ color: 'error.main' }}>{errors.lastName?.message}</Box>

              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="email"
                  label="Email Address"
                  name="email"
                  autoComplete="email"
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start">
                        <EmailRounded />
                      </InputAdornment>
                    ),
                  }}
                  {...register("email",{pattern: /^[^@ ]+@[^@ ]+\.[@ .]{2,}$/ })}
                 
                 

              /> <Box sx={{ color: 'error.main' }}>{errors.email?.message}</Box>
          {errors.email && errors.email.type==="pattern" && (<p>Email is Not valid</p>)}
        
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="password"
                  label="Password"
                  type="password"
                  id="password"
                  autoComplete="new-password"
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start">
                        <VpnKey />
                      </InputAdornment>
                    ),
                  }}
                  {...register("password",{required : true})}

                  />  <Box sx={{ color: 'error.main' }}>{errors.password?.message}</Box>

              </Grid><Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="confirmPwd"
                  label="confirmPwd"
                  type="confirmPwd"
                  id="confirmPwd"
                  autoComplete="new-password"
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start">
                        <VpnKey />
                      </InputAdornment>
                    ),
                  }}
                  {...register("confirmPwd",{required : true})}

                  />  <Box sx={{ color: 'error.main' }}>{errors.confirmPwd?.message}</Box>

              </Grid>
             
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              color='primary'
              sx={{ mt: 3, mb: 2 ,color:'black'}}
              startIcon={< AssignmentIndIcon />}
            >
              Sign Up
            </Button>
            <Grid container justifyContent="flex-end">
              <Grid item>
                <Link href="/login" variant="body2">
                  Already have an account? Sign in
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
       
      </Container>
    </ThemeProvider></div> <Footer /></>
  );
}