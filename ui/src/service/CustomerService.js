class CustomerService {

    retrieveAllCustomers() {
        return fetch("http://localhost:8080/customer")
    }
}

export default new CustomerService()
