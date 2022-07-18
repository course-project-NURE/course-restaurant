import React, {Component} from "react";
import {Container} from "reactstrap";
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


    render() {
        const {customers} = this.state

        const customerList = customers.map(customer => {
            return (
                <tr key={customer.id}>
                    <td>{customer.id}</td>
                    <td>{customer.email}</td>
                    <td>{customer.name}</td>
                    <td>{customer.surname}</td>
                    <td>{customer.lastname}</td>
                    <td>{customer.phone}</td>
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
