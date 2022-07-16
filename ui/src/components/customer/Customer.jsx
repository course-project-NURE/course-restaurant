import React, {useEffect, useState} from 'react'
import CustomerService from '../../service/CustomerService';

function Customer(){
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
            <h1>All Customers</h1>
            <div className={'container'}>
                <table className={'table'}>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Email</th>
                            <th>Name</th>
                            <th>Surname</th>
                            <th>Lastname</th>
                            <th>Phone</th>
                            <th>Modify</th>
                        </tr>
                    </thead>
                    <tbody>
                    {
                        allCustomers.map(
                            customer =>
                                <tr key={customers.id}>
                                    <td>{customer.id}</td>
                                    <td>{customer.email}</td>
                                    <td>{customer.name}</td>
                                    <td>{customer.surname}</td>
                                    <td>{customer.lastname}</td>
                                    <td>{customer.phone}</td>
                                    <td>{customer.role}</td>
                                </tr>
                        )
                    }
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default Customer
