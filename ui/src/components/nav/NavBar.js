import React from 'react';
import {  Link } from "react-router-dom";
import "./NavBar.css"

const NavBar = () =>{
    return(
        <header className="header">
            <div className="mid">
                <ul className="nav-bar">
                    <li><Link to="/customer">Customer</Link></li>
                    <li><Link to="/staff">Staff</Link></li>
                </ul>
            </div>
        </header>
    )
}

export default NavBar;
