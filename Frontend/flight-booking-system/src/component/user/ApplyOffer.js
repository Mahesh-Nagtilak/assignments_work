
import CloseIcon from '@mui/icons-material/Close';
import DiscountIcon from '@mui/icons-material/Discount';
import { Box, ListItemButton } from '@mui/material';
import AppBar from '@mui/material/AppBar';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import List from '@mui/material/List';
import ListItemText from '@mui/material/ListItemText';
import Slide from '@mui/material/Slide';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import axios from 'axios';
import * as React from 'react';
const Transition = React.forwardRef(function Transition(props, ref) {
  return <Slide direction="up" ref={ref} {...props} />;
});

export default function ApplyCoupen() {
  const [open, setOpen] = React.useState(false);

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);

  };
  const applyDiscount = (rate) => {

    sessionStorage.setItem("discount", JSON.stringify(rate));
    setOpen(false);
    window.location.reload();


  };
  const [offers, setOffers] = React.useState([]);
  React.useEffect(
    () => {
      axios.get("http://localhost:8091/api/flightOffer/getAlloffers")
        .then(response => setOffers(response.data))
        .catch(error => alert("error while retriving data:" + error.message))
    }, []);

  return (
    <div>
      <Button variant="outlined" onClick={handleClickOpen}>
        Apply Offer
      </Button>
      <Dialog
        fullScreen
        open={open}
        onClose={handleClose}
        TransitionComponent={Transition}
      >
        <AppBar sx={{ position: 'relative' }}>
          <Toolbar>
            <IconButton
              edge="start"
              color="inherit"
              onClick={handleClose}
              aria-label="close"
            >
              <CloseIcon />
            </IconButton>
            <Typography sx={{ ml: 2, flex: 1 }} variant="h6" component="div">
              Select Offer
            </Typography>

          </Toolbar>
        </AppBar>

        <List> {offers.length ? offers.map((offer, index) => (
          <Box key={index}><ListItemButton onClick={() => applyDiscount(offer.discountRate)} autoFocus variant="contained" sx={{ color: 'tomato' }}>
            <DiscountIcon />

            <ListItemText primary={offer.offerName} secondary={"Discount Rate: " + offer.discountRate + "% ..... " + offer.offerDescription + "%"} />
          </ListItemButton>
            <Divider /></Box>)) : null}
        </List>

      </Dialog>
    </div>
  );
}
