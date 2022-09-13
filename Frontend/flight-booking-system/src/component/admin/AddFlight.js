import React from 'react'

const AddFlight = () => {
  const user= JSON.parse( localStorage.getItem("user"));
  return (
    <div><h1>addFlight</h1>
    {user.userRole}</div>
  )
}

export default AddFlight