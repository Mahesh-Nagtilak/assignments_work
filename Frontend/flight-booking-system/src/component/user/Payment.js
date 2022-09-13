import { Button, InputAdornment } from '@material-ui/core';
import CloseIcon from '@mui/icons-material/Close';
import CurrencyRupeeIcon from '@mui/icons-material/CurrencyRupee';
import DiscountIcon from '@mui/icons-material/Discount';
import EmailRounded from '@mui/icons-material/EmailRounded';
import TrendingFlatIcon from '@mui/icons-material/TrendingFlat';
import { FormControl } from "@mui/material";
import Box from '@mui/material/Box';
import Checkbox from '@mui/material/Checkbox';
import FormControlLabel from '@mui/material/FormControlLabel';
import Grid from '@mui/material/Grid';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import axios from 'axios';
import * as React from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import Footer from '../Footer';
import Navbar from '../Navbar';
import ApplyCoupen from './ApplyOffer';
import '../css/payment.css';
export default function PaymentForm() {
  

   const user= JSON.parse(sessionStorage.getItem("user"));  
   
const headers = {
  'Content-Type': 'application/json',
   "Access-Control-Allow-Origin": "*",
  
}
   console.log(user.userId);

   const flightScheduleFare=JSON.parse(sessionStorage.getItem("flightScheduleFare"));
  const  flightScheduleId=sessionStorage.getItem("flightScheduleId");
  const discountRate=JSON.parse(sessionStorage.getItem("discount"));
  
  const flightSearchObj=JSON.parse(sessionStorage.getItem("flightSearchObj"));
  console.log(discountRate);
  var adultFare=0;
  var childrenFare=0;
  var infantFare=0;

  if(flightSearchObj.adult!=null) 
  {adultFare=flightSearchObj.adult*flightScheduleFare}

  if(flightSearchObj.children!=null)
   { childrenFare=flightSearchObj.children*flightScheduleFare}

  if(flightSearchObj.infant!=null) 
  { infantFare=flightSearchObj.infant*flightScheduleFare/2}

  
  const totalFare=adultFare+childrenFare+infantFare;


  
  var amountTopay=totalFare;
  if(flightSearchObj.fareType==="defence")
  {
    amountTopay=amountTopay-(amountTopay*25/100);
  }
  if(flightSearchObj.fareType==="student")
  {
    amountTopay=adultFare-(adultFare*20/100);
  }
  if(flightSearchObj.fareType==="seniorCitizen")
  {
    amountTopay=adultFare-(adultFare*35/100);
  }
  var filteredFare=amountTopay;

  if(discountRate!=null)
  {
     amountTopay=filteredFare-filteredFare*discountRate/100
   
  }

    const navigate=useNavigate();
   
    const bookTicket =event => {
      event.preventDefault();
      const data = new FormData(event.currentTarget);
     const obj ={
    
    userId:user.userId,
    flightId:flightScheduleId,
   amountPaid:amountTopay,
   email: data.get('email'),
      
  }
 
            axios.post("http://localhost:8091/api/passenger/bookTicket", obj, { headers:headers })
                .then((response) => {
                   console.log(response.data);
                    toast.success(response.data);
                    sessionStorage.removeItem("discountRate");
                    navigate('/confirmationPage',{ replace: true });

                
                })
                .catch((err) =>{
                  console.log(err.response.request.status);
                   if(err.response.request.status===400)
                   {
                    toast.error("Failed to send ticket details to email");
                    navigate('/confirmationPage',{ replace: true });

                   }
                   else
                   {
                    toast.error(""+err.response.request.response)
                   }
                
                 
                } );
        
    };
  
   
  return (   <><Navbar/>
     <div id='Payment-div'> 
 
    <div className="container"  >
    <div className='row'>
    <div className='col'>
  <FormControl  component="form"  onSubmit={bookTicket} sx={{border:'1px solid',marginTop:10,marginBottom:9, padding:5,backgroundColor:'white', height:'80%'}} > <Typography variant="h6" gutterBottom >
    Payment method
  </Typography>
      <Grid container spacing={3}>
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
        
      </Grid><br/><Button variant="contained" size="large" type="submit" color='primary' startIcon={< CurrencyRupeeIcon />}   >Pay </Button></FormControl></div> <div className='col'><Box  sx={{border:'1px solid',marginTop:10, padding:5,backgroundColor:'white',height:'80%'}}><b>Fare Summary</b> :<br/>Basic Fare :<br/>{(flightSearchObj.adult!=null)&& <>Adults({flightSearchObj.adult}<CloseIcon/><CurrencyRupeeIcon fontSize='small'/>{flightScheduleFare}) <TrendingFlatIcon fontSize='large'/> <CurrencyRupeeIcon fontSize='small'/> {adultFare}</>}<br/>
    
      {(flightSearchObj.children!=null)&& <> Children ({flightSearchObj.children}<CloseIcon/>{flightScheduleFare})<CurrencyRupeeIcon fontSize='small'/><TrendingFlatIcon fontSize='large'/> <CurrencyRupeeIcon fontSize='small'/>{childrenFare}</>}<br/>
    
      {(flightSearchObj.infant!=null)&& <>    Infant({flightSearchObj.infant}<CloseIcon/>{flightScheduleFare/2}) <CurrencyRupeeIcon fontSize='small'/><TrendingFlatIcon fontSize='large'/><CurrencyRupeeIcon fontSize='small'/> {infantFare}</>}<br/>
  
   {(flightSearchObj.fareType==="defence")&& <div><DiscountIcon/>Armed Force Fare Discount : 25%<TrendingFlatIcon fontSize='large'/><b>-  <CurrencyRupeeIcon fontSize='small'/> {(totalFare*25/100)}</b><br/></div>}
  
   {(flightSearchObj.fareType==="student")&& <div><DiscountIcon/>Student  Fare Discount : 20% <TrendingFlatIcon fontSize='large'/><b>- <CurrencyRupeeIcon fontSize='small'/>{(totalFare*20/100)}</b><br/></div>}
  
   {(flightSearchObj.fareType==="seniorCitizen")&& <div><DiscountIcon/>Senior citizen Fare Discount :35%<TrendingFlatIcon fontSize='large'/> <b>-<CurrencyRupeeIcon fontSize='small'/>{(totalFare*35/100)}</b><br/></div>}
 
    <hr/>Total Fare<TrendingFlatIcon fontSize='large'/><CurrencyRupeeIcon fontSize='small'/>{filteredFare}
   
    <hr/> {(discountRate!=null) ? <div> <DiscountIcon fontSize='small'/>Discount<TrendingFlatIcon fontSize='large'/> <b>-<CurrencyRupeeIcon fontSize='small'/>{filteredFare*discountRate/100}</b><br/>
  
   <b> Final Amount After Discount</b> <h3><CurrencyRupeeIcon fontSize='small'/>{filteredFare-filteredFare*discountRate/100}</h3></div> :null }  <Button color="error" startIcon={<DiscountIcon/>}><ApplyCoupen></ApplyCoupen></Button></Box> 
  
     <br/>
   </div>
   </div> 
  
   </div> </div><Footer/>
 </>
  );
}


