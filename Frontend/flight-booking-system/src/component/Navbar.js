
import HomeIcon from '@mui/icons-material/Home';
import HowToRegIcon from '@mui/icons-material/HowToReg';
import InfoIcon from '@mui/icons-material/Info';
import LockOpenIcon from '@mui/icons-material/LockOpen';
import MenuIcon from '@mui/icons-material/Menu';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Divider from '@mui/material/Divider';
import Drawer from '@mui/material/Drawer';
import IconButton from '@mui/material/IconButton';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemText from '@mui/material/ListItemText';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import * as React from 'react';

import { Avatar } from '@mui/material';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
const drawerWidth = 240;
const navItems = ['Home', 'Sign In', 'Sign Up', 'About Us', 'Contact Us','Search Flight'];

export default function Navbar(props) {
 const user= JSON.parse(sessionStorage.getItem("user"));
 const admin= JSON.parse(sessionStorage.getItem("admin"));
  const { window } = props;
  const [mobileOpen, setMobileOpen] = React.useState(false);

  const [anchorEl, setAnchorEl] = React.useState(null);
  const open = Boolean(anchorEl);
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };

  const handleDrawerToggle = () => {
    setMobileOpen(!mobileOpen);
  };

  const drawer = (
    <Box onClick={handleDrawerToggle} sx={{ textAlign: 'center' , bgcolor: '#F6CFFC'}}>
      <Typography variant="h6" sx={{ my: 2 }}>
      FlightFlexer
      </Typography>
      <Divider/>
      
      <List>
      <ListItem disablePadding>
            <ListItemButton sx={{ textAlign: 'center' ,color: 'navy' }} href="/home">
              <ListItemText primary={navItems[0]} />
            </ListItemButton>
          </ListItem>
          <ListItem disablePadding>
            <ListItemButton sx={{ textAlign: 'center',color: 'navy' }} href="/Login">
              <ListItemText primary={navItems[1]} />
            </ListItemButton>
          </ListItem>
          <ListItem disablePadding>
            <ListItemButton sx={{ textAlign: 'center' ,color: 'navy'}} href="/Registration">
              <ListItemText primary={navItems[2]} />
            </ListItemButton>
          </ListItem>
          <ListItem disablePadding>
            <ListItemButton sx={{ textAlign: 'center' ,color: 'navy'}} href="/AboutUs">
              <ListItemText primary={navItems[3]} />
            </ListItemButton>
          </ListItem>
          <ListItem disablePadding>
            <ListItemButton sx={{ textAlign: 'center',color: 'navy' }} href="/ContactUs">
              <ListItemText primary={navItems[4]} />
            </ListItemButton>
          </ListItem>

      </List>
    </Box>
  );

  const container = window !== undefined ? () => window().document.body : undefined;

  return (
    <Box sx={{ display: 'flex'}}>
      <AppBar component="nav" sx={{  bgcolor: 'skyblue' ,position:'relative' }}>
        <Toolbar>
          <IconButton
            color="inherit"
            aria-label="open drawer"
            edge="start"
            onClick={handleDrawerToggle}
            sx={{ mr: 2, display: { sm: 'none', color: 'navy'} }}
          >
            <MenuIcon />
          </IconButton><Avatar  src="https://w7.pngwing.com/pngs/53/992/png-transparent-airplane-flight-computer-icons-similar-icons-with-these-tags-plane-flight-weibo-hotel-icon-car-blue-flight-transport.png" />
          <Typography
            variant="h6"
            component="div"
            sx={{ flexGrow: 1, display: { xs: 'none', sm: 'block' } ,textAlign: 'center' }}
          >
          <Button
                                    href="/home"
                                    size="large"
                                    sx={{ my: 0, color: 'navy', display: 'block',fontSize: 28,textAlign: 'left'}}
                                >FlightFlexer

                                </Button>
          </Typography>
          <Box sx={{ display: { xs: 'none', sm: 'block' } }}>
<Button
    href='/home'
 
    sx={{ my: 1, color: 'navy' }}
>
   <HomeIcon fontSize='small'/> {navItems[0]}
</Button>
{((user==null) && (admin==null))&&<>
<Button
    href="/Login"
    
    sx={{ my: 1, color: 'navy' }}
>
<LockOpenIcon  fontSize='small'/> {navItems[1]}
</Button>


<Button
    href="/signUp"
   
    sx={{ my: 1, color: 'navy' }}
>
    <HowToRegIcon  fontSize='small'/>{navItems[2]}
</Button>


<Button
    href="/aboutUs"
   
    sx={{ my: 1, color: 'navy' }}
>
    <InfoIcon fontSize='small'/>{navItems[3]}
</Button>
</>}
{(user!=null)&&<>
<Button
    href="/searchFlight"
    
    sx={{ my: 1, color: 'navy' }}
>
    Book Flight
</Button>
<Button
    href="/allTickets"
    
    sx={{ my: 1, color: 'navy' }}
>
    Tickets
</Button>

<Button
    href="/getAllOffers"
    
    sx={{ my: 1, color: 'navy' }}
>
    offers
</Button>


</>
}

{(admin!=null)&&<>
<Button
    href="/add-flight"
    
    sx={{ my: 1, color: 'navy' }}
>
    Add Flight
</Button>

<Button
    href="/getAllFlights"
    
    sx={{ my: 1, color: 'navy' }}
>
    All Flights
</Button>
<Button
    href="/feedBackList"
    
    sx={{ my: 1, color: 'navy' }}
>
   FeedBack-List
</Button>
<Button
    href="/addOffers"
    
    sx={{ my: 1, color: 'navy' }}
>
   Add Offers
</Button>
<Button
    href="/getAllOffers"
    
    sx={{ my: 1, color: 'navy' }}
>
    View Offers
</Button>

<Button
    href="/addOffer"
    
    sx={{ my: 1, color: 'navy' }}
>
Add Offer
</Button><Button
    href="/complaints"
    
    sx={{ my: 1, color: 'navy' }}
>
     Complaints
</Button>

</>

}

{(admin!=null || user!=null)&&
<>
<Button
        id="basic-button"
        aria-controls={open ? 'basic-menu' : undefined}
        aria-haspopup="true"
        aria-expanded={open ? 'true' : undefined}
        onClick={handleClick}
      >
       
      <Avatar src="/broken-image.jpg" />
      </Button>
      <Menu
        id="basic-menu"
        anchorEl={anchorEl}
        open={open}
        onClose={handleClose}
        MenuListProps={{
          'aria-labelledby': 'basic-button',
        }}
      >
        <MenuItem onClick={handleClose}><Button
    href="/updateProfile"
    
    sx={{  color: 'black' }}
>
   Update Profile
</Button></MenuItem>
        <MenuItem onClick={handleClose}>
<Button
    href="/updatePassword"
    
    sx={{  color: 'black' }}
>
   Change Password
</Button></MenuItem>
        <MenuItem onClick={handleClose}><Button
    href="/logout"
    sx={{  color: 'black' }}
>
     Logout
</Button></MenuItem>
      </Menu>


</>}
          </Box>
        </Toolbar>
      </AppBar>
      <Box component="nav">
        <Drawer
          container={container}
          variant="temporary"
          open={mobileOpen}
          onClose={handleDrawerToggle}
          ModalProps={{
            keepMounted: true, // Better open performance on mobile.
          }}
          sx={{
            display: { xs: 'block', sm: 'none' },
            '& .MuiDrawer-paper': { boxSizing: 'border-box', width: drawerWidth },
          }}
        >
          {drawer}
        </Drawer>
      </Box>
       {/* <Box component="main" sx={{ p: 2 }}>
        <Toolbar />
       
      </Box>   */}
    </Box>
  );
}
