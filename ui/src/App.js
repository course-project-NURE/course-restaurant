import './App.css'
import NavBar from "./components/nav/NavBar";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import Staff from "./components/staff/Staff";
import Customer from "./components/customer/Customer";
import React from "react";


function App() {
  return (
      <div>
          <Router>
              <NavBar/>
              <Routes>
                  <Route path='/staff' element={<Staff/>}/>
                  <Route path='/customer' element={<Customer/>}/>
              </Routes>
          </Router>
      </div>
  )
}

export default App
