

import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Typography from "@mui/material/Typography";
import axios from "axios";
import React, { useEffect, useState } from "react";
import "./css/Offer.css";
import Navbar from "./Navbar";
 
export default function GetAllOffers() {
  
 const admin= JSON.parse(sessionStorage.getItem("admin"));
  let [offer, setOffer] = useState([]);
  let [errorMsg, setErrorMsg] = useState("");
  
  const deleteOffer=(offerId)=>
  {
     axios.delete("http://localhost:8091/api/flightOffer/deleteOffer/"+offerId)
     .then((response) => {
      alert("offer succesfully deleted");
      window.location.reload();
    
    })
    .catch((error) => console.log(error.data));
  }
  useEffect(() => {
    GetAllOffers()
  }, []);
 
  const GetAllOffers = () => {
    axios
      .get("http://localhost:8091/api/flightOffer/getAlloffers")
      .then((response) => {
        const result = response.data;
        console.log(result);
        if (result === null) throw errorMsg;
        setOffer(result);
      })
      .catch((error) => setErrorMsg("error "));
  };
 
  return (<><Navbar/>
    <div className="card">
        {offer.map((offer) => (
      <div>
        <Card sx={{ maxWidth: 345 }}>
          <CardMedia
            component="img"
            height="200"
            image={require("../images/offer.jpg")}
            alt="green iguana"
          />
          <CardContent>
         
            <Typography gutterBottom variant="h5" component="div">
              {offer.offerName}
            </Typography>
            <Typography gutterBottom variant="h5" component="div">
              {offer.discountRate}%
            </Typography>
            <Typography variant="body2" color="text.secondary">
              {offer.offerDescription}
            </Typography>
          </CardContent>
          <CardActions>
        {(admin!=null)&&  <button type="button" class="btn btn-danger" onClick={()=>deleteOffer(offer.offerId)}>Delete</button>}
          </CardActions>
        </Card>
      </div>
      ))}
    </div></>
  );
}
 
 
 