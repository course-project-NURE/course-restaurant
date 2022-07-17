import React, {useEffect, useState} from 'react'
import CustomerService from '../../service/CustomerService';
import "./Customer.css"

const Customer = () => {
    const email = useState('')
    const name = useState('')
    const surname = useState('')
    const lastname = useState('')
    const phone = useState('')
    const [allCustomers, setAllCustomers] = useState([])
    const customers = {
        email,
        name,
        surname,
        lastname,
        phone
    }

    useEffect(() => {
        CustomerService.retrieveAllCustomers()
            .then(response => response.json())
            .then(result => setAllCustomers(result))
    }, [])

    return (
        <div className={'container'}>
            <div className={'container'}>
                <h4>Customer</h4>
                <table className="table table-bordered border-dark">
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
                    {
                        allCustomers.map(
                            customer =>
                                <tr key={customer.id}>
                                    <td>{customer.id}</td>
                                    <td>{customer.email}</td>
                                    <td>{customer.name}</td>
                                    <td>{customer.surname}</td>
                                    <td>{customer.lastname}</td>
                                    <td>{customer.phone}</td>
                                </tr>
                        )
                    }
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default Customer;
