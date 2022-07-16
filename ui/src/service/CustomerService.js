class CustomerService {

    retrieveAllCustomers() {
        return fetch("http://localhost:8080/customer")
    }

    retrieveCustomerById(id) {
        return fetch('http://localhost:8080/customer/${id}')
    }

    retrieveCustomerByEmail(email) {
        return fetch('http://localhost:8080/customer/${email}')
    }

    addCustomer(customer) {
        return fetch("http://localhost:8080/customer", {
            method: 'POST',
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(customer)
        })
    }
}

export default new CustomerService()
