import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './App.css';
import Home from './component/Home';
import ListFlightsComponent from './component/ListFlightsComponent'

import Login from './component/Login';
import { Logout } from './component/Logout';
import AddFlightComponent from './component/AddFlightComponent'
import ListFlightsScheduleComponent from './component/ListFlightsScheduleComponent'
import UpdateFlightComponent from './component/UpdateFlightComponent'
import AddFlightScheduleComponent from './component/AddFlightScheduleComponent'
import UpdateFlightScheduleComponent from './component/UpdateFlightScheduleComponent'
import OtpValidation from './component/OtpVadlidation';
import SignUp from './component/SignUp';
import AddPassenger from './component/user/AddPassenger';
import { AllTickets } from './component/user/AllTickets';
import {AvailableFlights} from './component/user/AvailableFlights';
import { ConfirmationPage } from './component/user/ConfirmationPage';
import { Feedback } from './component/user/Feedback';

import PaymentForm from './component/user/Payment';
import { SearchFlight } from './component/user/SearchFlight';
import { UpdatePassword } from './component/user/UpdatePassword';
import { UpdateProfile } from './component/user/UpdateProfile';
import GetAllOffers from './component/GetAllOffers';
import { AddOffers } from './component/user/AddOffers';
import { FeedBackList } from './component/admin/FeedBackList';
import Navbar from './component/Navbar';
import { LoginForm } from './component/Demo_learning/LoginForm';


function App() {
   const user= sessionStorage.getItem("user");
   const admin= sessionStorage.getItem("admin");
  return (
    <div >
      <BrowserRouter>
      <Routes >
         <Route path='/' element={<Home/>} />
        <Route path='/home' element={<Home/>} />
        <Route path='/signUP' element={<SignUp/>}/>
        <Route path='/Login' element={<Login/>}/>
        <Route path='/demo' element={<LoginForm/>}/>
        
        {(user!=null)&&
        <>
        <Route path="/searchFlight" element={<SearchFlight/>}/>
         <Route path='/otpValidation' element={<OtpValidation/>}/>
        <Route path='/addPassenger' element={<AddPassenger/>}/> 
        <Route path='/paymentPage' element={<PaymentForm/>}/>
       <Route path='/confirmationPage' element={<ConfirmationPage/>}/> 
       <Route path='/availableFlights' element={<AvailableFlights/>}/>
       <Route path='/feedback' element={<Feedback/>}/>
     
       <Route path='/allTickets' element={<AllTickets/>}/> 
      
       </> }
    
       <Route path='/getAllOffers' element={<GetAllOffers/>}/> 
      {(admin!=null)&&<>
    
        <Route path='/addOffers' element={<AddOffers/>}/>
    
       <Route path='/feedBackList' element={<FeedBackList/>}/>   
         <Route path = "/getAllFlights" element = {<ListFlightsComponent />}></Route>
          <Route path = "/add-flight" element = {<AddFlightComponent />} ></Route>
          <Route path = "/edit-flight/:flightNumberId" element = {<UpdateFlightComponent />}></Route>
          <Route path = "/view-flightSchedule/:flightNumberId" element = {<ListFlightsScheduleComponent />}></Route>
          <Route path = "/add-flightSchedule/:flightNumberId" element = {<AddFlightScheduleComponent />} ></Route>
          <Route path = "/edit-flightSchedule/:id" element = {<UpdateFlightScheduleComponent />}></Route></>}
          {(admin!=null || user!=null)&&<>
          <Route path='/logout' element={<Logout/>}/>
          <Route path='/updateProfile' element={<UpdateProfile/>}/> 
       <Route path='/updatePassword' element={<UpdatePassword/>}/> </>}
       </Routes> 
      
        </BrowserRouter> 
         
     
      <ToastContainer theme='colored'/>
    </div>
  );
}

export default App;
