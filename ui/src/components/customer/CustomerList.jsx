import React, {Component} from "react";
import {Button, Container} from "reactstrap";
import AppNavbar from "../nav/AppNavbar";
import './Customer.css'
import CustomerService from "../../service/CustomerService";

class CustomerList extends Component{
    constructor(props) {
        super(props);
        this.state = {customers: []};
    }

    componentDidMount() {
        CustomerService.retrieveAllCustomers()
            .then(response => response.json())
            .then(data => this.setState({customers:data}));
    }
    //?!
    sendPromoAction(email){
        CustomerService.sendPromoToEmail(email).then(response =>{
            console.log(response);
            return response.json();
        })
            .then(data =>{
                console.log(data)
            })
            .catch(e=>console.log(e));
    }
    render() {
        const {customers} = this.state
        let now = new Date()
        let nowMonth = now.getMonth() + 1
        let nowDate = now.getDate()
        let button = ''
        let birthdateStyle = "bg-white text-dark"
        const customerList = customers.map(customer => {
            if(Number(customer.birthdate.slice(5,7))===nowMonth && Number(customer.birthdate.slice(8,10))===nowDate)
            {
                customer.promoAvailable = true
                if(!customer.promoReceived)
                {
                    customer.promoReceived = true
                    button = <Button onClick={() => this.sendPromoAction(customer.email)} className="bg-white text-primary">Send promo</Button>
                    birthdateStyle = "bg-primary text-white"
                }
            }
            else{
                customer.promoAvailable = false
            }
            return (
                <tr key={customer.id} className={birthdateStyle}>
                    <td>{customer.id}</td>
                    <td>{customer.email}</td>
                    <td>{customer.name}</td>
                    <td>{customer.surname}</td>
                    <td>{customer.lastname}</td>
                    <td>{customer.phone}</td>
                    <td>{customer.birthdate}</td>
                    <td>{button}</td>
                </tr>
            )
        })
        return(
            <div>
                <AppNavbar/>
                <Container fluid className='list'>
                    <h4>Customers</h4>
                    <table className="table table-bordered  border-dark">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Email</th>
                            <th>Name</th>
                            <th>Surname</th>
                            <th>Lastname</th>
                            <th>Phone</th>
                            <th>Birthdate</th>
                            <th>Promo</th>
                        </tr>
                        </thead>
                        <tbody>
                        {customerList}
                        </tbody>
                    </table>
                </Container>
            </div>
        )
    }
}
export default CustomerList;
