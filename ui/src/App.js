import './App.css'
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import React from "react";
import StaffEdit from "./components/staff/StaffEdit";
import Home from "./components/Home";
import StaffList from "./components/staff/StaffList";
import CustomerList from "./components/customer/CustomerList";
import MenuItemList from "./components/menu-item/MenuItemList";
import MenuItemEdit from "./components/menu-item/MenuItemEdit";


function App() {
  return (
      <div className="container">
          <Router>
              <Switch>
                  <Route path='/' exact={true} component={Home}/>
                  <Route path='/staff' exact={true} component={StaffList}/>
                  <Route path='/staff/:id' component={StaffEdit}/>
                  <Route path='/customer' component={CustomerList}/>
                  <Route path='/menu-item' exact={true} component={MenuItemList}/>
                  <Route path='/menu-item/:id' exact={true} component={MenuItemEdit}/>
              </Switch>
          </Router>
      </div>
  )
}

export default App
