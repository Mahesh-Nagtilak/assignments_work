
import React, { useState } from 'react';
import Carousel from 'react-bootstrap/Carousel';
import Footer from '../component/Footer';
import './css/form.css';
import Navbar from './Navbar';


function Home() {
  const [index, setIndex] = useState(0);

  const handleSelect = (selectedIndex, e) => {
    setIndex(selectedIndex);
  };
  return (
    <>
      <Navbar />  <Carousel activeIndex={index} onSelect={handleSelect}>
        <Carousel.Item>
          <img
            className="d-block w-100"
            src="https://pharmaceuticalmanufacturer.media/downloads/5269/download/Plane.gif?cb=21ebb6735e8c9d0692f1fc3a5b57192c"
            alt="First slide"
            width={1530}
            height={500}
          />
          <Carousel.Caption>
            <h3>Book Flight Now</h3>
            <p>You will get Discount on Booking</p>
          </Carousel.Caption>
        </Carousel.Item>
        <Carousel.Item>
          <img
            className="d-block w-100"
            src="https://images.news18.com/ibnlive/uploads/2021/12/akasa-air-164018485216x9.jpg"
            alt="Second slide"
            width={1530}
            height={500}
          />

          <Carousel.Caption>
            <h3>Fly  </h3>
            <p>want to go to your dream location</p>
          </Carousel.Caption>
        </Carousel.Item>
        <Carousel.Item>
          <img
            className="d-block w-100"
            src="https://nigerdeltaconnect.com/wp-content/uploads/2021/10/place_hw5d.jpg"
            alt="Third slide"
            width={1530}
            height={500}
          />

          <Carousel.Caption>
            <h3>Book Flight </h3>
            <p>
              Get set to feel special and enjoy memorable stays to vacation
            </p>
          </Carousel.Caption>
        </Carousel.Item>
      </Carousel>

      

      
        
            
            <div class="row mt-4 p-4">
              <div class="col">
                <img id='rowimg' src={'https://d3pvfs0dgh4r2q.cloudfront.net/website/MainComponent/common/ImportantLinks/chng.png'}  alt=""/>
                <h3>Change Assist</h3>
              </div>
              <div class="col">
                <img id='rowimg' src={'https://d3pvfs0dgh4r2q.cloudfront.net/website/MainComponent/common/ImportantLinks/dealshome.png'} alt=""/>
                <h3>Get Deals</h3>
              </div>
              <div class="col">
                <img id='rowimg' src={'https://d3pvfs0dgh4r2q.cloudfront.net/website/MainComponent/common/ImportantLinks/covid.png'} alt="" />
                <h3>Covid Info</h3>
              </div>
              <div class="col">
                <img id='rowimg' src={'https://d3pvfs0dgh4r2q.cloudfront.net/website/MainComponent/common/ImportantLinks/screen.png'} alt="" />
                <h3>SpiceScreen
                </h3>
              </div>
              <div class="col">
                <img id='rowimg' src={'https://d3pvfs0dgh4r2q.cloudfront.net/website/MainComponent/common/ImportantLinks/activity1.png'}  alt=""/>
                <h3>Activities</h3>
              </div>
              <div class="col">
                <img id='rowimg' src={'https://d3pvfs0dgh4r2q.cloudfront.net/website/MainComponent/common/ImportantLinks/gst.png'} alt="" />
                <h3>GST Invoice</h3>
              </div>
            </div>
            <div class="row mt-4 p-4">
              <Carousel id='captioncarousel' controls={false} indicators={false}>
                <Carousel.Item>

                  <p>Due to high call volume at our Reservation Center, we encourage you to use ourChange Assist portal to choose alternate flights or refund for your rescheduled/cancelled flights, at your own convenience.</p>

                </Carousel.Item>
                <Carousel.Item>

                  <p>Due to high call volume at our Reservation Center, we encourage you to use ourChange Assist portal to choose alternate flights or refund for your rescheduled/cancelled flights, at your own convenience.</p>

                </Carousel.Item>
                <Carousel.Item>

                  <p>Ahmedabad Airport Advisory: Commencement of the scheduled runway re-carpeting work at Sardar Vallabhbhai Patel International airport will be carried out from January 17 till May 31, 2022, between 9am and 6pm.</p>

                </Carousel.Item>
                <Carousel.Item>

                  <p>Due to high call volume at our Reservation Center, we encourage you to use ourChange Assist portal to choose alternate flights or refund for your rescheduled/cancelled flights, at your own convenience.</p>

                </Carousel.Item>
                <Carousel.Item>

                  <p>your rescheduled/cancelled flights, at your own convenience.With the commencement of our summer schedule, there may be a revision in some of our flights. Please check flight status before proceeding to the airport.</p>

                </Carousel.Item>
              </Carousel>
            </div>
        

       

    



      <Footer />
    </>

  )
}

export default Home