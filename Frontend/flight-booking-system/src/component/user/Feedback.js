import { Box, Button, Rating, TextareaAutosize } from '@mui/material';
import Grid from '@mui/material/Grid';
import axios from 'axios';
import React from 'react';
import { useForm } from 'react-hook-form';
import { toast } from 'react-toastify';
import Navbar from '../Navbar';
export const Feedback = () => {
  const user = JSON.parse(sessionStorage.getItem("user"));
  const flightScheduleId = JSON.parse(sessionStorage.getItem("flightScheduleId"));

  const { register, handleSubmit, formState: {
    errors
  } } = useForm();

  const [value, setValue] = React.useState(2);

  const onSubmit = data => {
    alert(data.feedback + " " + value)
    const feedbackObj =
    {
      feedback: data.feedback,
      rating: value,
      flightId: flightScheduleId,
      userId: user.userId


    }
    axios.post("http://localhost:8078/api/feedback/add-feedback", feedbackObj,
      {
        headers: {
          "Content-Type": "application/json",
        },
      })
      .then((response) => {

        {
          toast.success("FeedBack has been succesfully submitted!!")
        }


      }).catch((err) => {
        console.log(err);

      });


  }


  return (
    <>
      <Navbar />
      <Box
        noValidate
        sx={{
          marginTop: 8,
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
          border: '10px'
        }}
      >   <Grid item xs={12} sm={6}> FeedBack:  <br /> <br /><Box
      > <TextareaAutosize
          minRows={7}
          aria-label="maximum height"
          placeholder="write here"

          style={{ width: 400 }}
          {...register("feedback", { required: true })}

        />

        {errors.feedback?.type === "required" && <Box sx={{ color: 'error.main' }}>Please enter FeedBack</Box>}



        <br />  <br />  <br />

        Rating: <br /><br /><Rating
          name="simple-controlled"
          value={value}
          onChange={(event, newValue) => {
            setValue(newValue);
          }}
        />




        <br /><br />

        <Button
          type="submit"
          fullWidth
          variant="contained"
          color='secondary'
          onClick={handleSubmit(onSubmit)}

        >
          Submit
        </Button></Box></Grid>
      </Box>
    </>
  )
}
