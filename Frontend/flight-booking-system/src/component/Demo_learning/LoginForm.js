import { Button, FormControlLabel, InputAdornment } from '@material-ui/core'
import EmailRounded from '@mui/icons-material/EmailRounded'
import { FormControl, Grid, TextField } from '@mui/material'
import Checkbox from '@mui/material/Checkbox'
import { margin } from '@mui/system'
import React from 'react'
import Footer from '../Footer'
import Navbar from '../Navbar'
export const LoginForm = () => {
  return (
    <><Navbar/>
    <div>
        <div className='row' >
            <div className='col'>
            <img src="https://images.pexels.com/photos/2033343/pexels-photo-2033343.jpeg?cs=srgb&dl=pexels-jason-toevs-2033343.jpg&fm=jpg" width={'90%'} height={'60%'} alt="flight img"/>
            </div>

            <div className='col'>
                <FormControl  component="form" sx={{border:'1px solid', margin:10, padding:10,backgroundColor:'white'}} > 
                <h2>Payment Details</h2>
      <Grid container spacing={3} >
        <Grid item xs={12} md={6} >
          <TextField
            required
            type="names"
            id="cardName"
            label="Name on card"
            fullWidth
            autoComplete="cc-name"
            variant="standard"
          />
        </Grid>
        <Grid item xs={12} md={6}>
          <TextField
            required
           size='3'
           type='number'
            id="cardNumber"
            label="Card number"
            fullWidth
            autoComplete="cc-number"
            variant="standard"
          />
        </Grid>
        <Grid item xs={12} md={6}>
          <TextField
            required
            id="expDate"
            label="Expiry date"
            fullWidth
            autoComplete="cc-exp"
            variant="standard"
          />
        </Grid>
        <Grid item xs={12} md={6}>
          <TextField
            required
            id="cvv"
            label="CVV"
            type='number'
           min='100'
           max='999'
            helperText="Last three digits on signature strip"
            fullWidth
            autoComplete="cc-csc"
            variant="standard"
          />
        </Grid>
        <Grid item xs={12}>
          <FormControlLabel
            control={<Checkbox color="secondary" name="saveCard" value="yes" />}
            label="Remember  card details for next time"
          />
        </Grid>
        
        <Grid item xs={12} md={6} >
         Email to send Booking details:
                <TextField
                  required
                  fullWidth
                  id="email"
                  label="*"
                  name="email"
                  autoComplete="email"
                  placeholder='enter email'
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start">
                        <EmailRounded />
                      </InputAdornment>
                    ),
                  }}
              />
              </Grid>
             
      </Grid><br/> <Button variant="contained" color="primary">pay</Button></FormControl></div>
          
        </div>

    </div>
    <Footer/></>
  )
}
