import React,{useNavigate, useState} from 'react'

export const UserBookings = () => {

    const navigate=useNavigate();
    const user=sessionStorage.getItem("user");
    const {bookings,setBookings}=useState([]);
    useEffect(
        () => {
            axios.get("http://localhost:8078/api/booking/getUserBooking/"+user.userId, { headers: headers })
                
                .then((response)=>{   console.log(response.data);
                    setBookings(response.data)})
                .catch(error => console.log(error))

        }, []
    )
   const  feedBack=(flightScheduleId)=>
   {
         sessionStorage.setItem("flightScheduleId",JSON.stringify(flightScheduleId));
         navigate("/feedback");
   }

  return (
   <><Box    sx={{ mt: 0, width: '80%',marginLeft:'auto',marginRight:'auto' }}>
   <TableContainer component={Paper} >
     <Table aria-label="simple table">
       <TableHead className="bg-warning">
         <TableRow>
        
           <TableCell align="left" >Booking ID</TableCell>
           <TableCell align="left">Booking  Amount</TableCell>
          
           <TableCell align="left">Booking Date</TableCell>
           <TableCell align="left">Flight Name</TableCell>
           <TableCell align="left">Flight Number</TableCell>
           <TableCell align="left">Source </TableCell>
           <TableCell align="left">Destination </TableCell>
           <TableCell align="left">Departure Date </TableCell>
           <TableCell align="left">##</TableCell>
         </TableRow>
       </TableHead>
       <TableBody  className="bg-info">
      
         { bookings.length?bookings.map((booking,index) => (
           <TableRow  key={index}>
          
             <TableCell component="th" scope="row">{booking.bookingId} </TableCell>  
             <TableCell align="left">{booking.bookingAmount}</TableCell>
             <TableCell align="left">{booking.bookingDate}</TableCell>
             <TableCell align="left">{booking.flightName}</TableCell>
             <TableCell align="left">{booking.flightNumber}</TableCell>
             <TableCell align="left">{booking.source}</TableCell>
             <TableCell align="left">{booking.destination}</TableCell>
             <TableCell align="left">{booking.departureDate}</TableCell>
          
           {((new Date())>(new Date(booking.departureDate))) && <TableCell align="left"><Button variant="contained" color="error" onClick={()=>feedBack(booking.flightScheduleId)}>
        FeedBack
      </Button></TableCell>}
           </TableRow>
         )) : null}
       </TableBody>
       
     </Table>
   </TableContainer><br/>
  
</Box><br/></>
  )
}
