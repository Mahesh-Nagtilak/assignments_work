import React, { useEffect } from 'react'
import { useNavigate } from 'react-router-dom';

export const Logout = () => {


    const navigate = useNavigate();

    useEffect(()=>{
        logout()
    })
    const logout = () => {
        sessionStorage.clear();

        navigate('/home');
    }

};
