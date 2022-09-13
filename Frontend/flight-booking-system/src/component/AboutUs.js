
import * as React from 'react';
import AppBar from '@mui/material/AppBar';
// import CameraIcon from '@mui/icons-material/PhotoCamera';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import CssBaseline from '@mui/material/CssBaseline';
import Grid from '@mui/material/Grid';
import Stack from '@mui/material/Stack';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Link from '@mui/material/Link';
import { createTheme, ThemeProvider } from '@mui/material/styles';
//import Nav from './Nav';
import Footer from './Footer';

function Copyright() {
    return (
        <Typography variant="body2" color="text.secondary" align="center">
            {'Copyright © '}
            <Link color="inherit" href="https://mui.com/">
            FlightFlexer
            </Link>{' '}
            {new Date().getFullYear()}
            {'.'}
        </Typography>
    );
}

const cards = [1];

const theme = createTheme();

function AboutUs() {

    return (
        <>
            <ThemeProvider theme={theme}>
                <CssBaseline />
                {/* <Nav /> */}
                <div id='about_us'>
                    {/* Hero unit */}
                    <Box
                        sx={{
                            bgcolor: 'background.paper',
                            pt: 8,
                            pb: 5,

                        }}
                    >
                        <Container maxWidth="sm">
                            <Typography
                                component="h1"
                                variant="h2"
                                align="center"
                                color="text.primary"
                                gutterBottom
                            >
                                FlightFlexer
                            </Typography>
                            <Typography variant="h5" align="center" color="text.secondary" paragraph>
                            At FlightFlexer, you can find the best of deals and cheap air tickets to any place you want by booking your tickets on our website 
                            </Typography>
                            <Stack
                                sx={{ pt: 4 }}
                                direction="row"
                                spacing={2}
                                justifyContent="center"
                            >

                            </Stack>
                        </Container>

                    </Box>

                    <Container sx={{ py: 8 }} maxWidth="md">
                        {/* End hero unit */}
                        <Grid container spacing={4}>
                            {cards.map((card) => (
                                <Grid item key={card} xs={12} sm={6} md={4}>


                                    <Card
                                        sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}
                                    >
                                        <CardMedia
                                            component="img"
                                            sx={{

                                                pt: '.5%',
                                            }}
                                            image="https://cdn.pixabay.com/photo/2015/11/24/19/14/mother-of-perpetual-help-1060612_960_720.jpg"

                                        />
                                        <CardContent sx={{ flexGrow: 1 }}>
                                            <Typography gutterBottom variant="h5" component="h2">
                                                Our Vision
                                            </Typography>
                                            <Typography>
                                            MakeMyTrip helps you book flight tickets that are affordable and customized to your convenience. With customer satisfaction being our ultimate goal
                                            </Typography>
                                        </CardContent>
                                    </Card>

                                </Grid>
                            ))}
                        </Grid>
                    </Container>

                </div>
                
                {/* End footer */}
            </ThemeProvider>
            <Footer />
        </>
    )
}

export default AboutUs