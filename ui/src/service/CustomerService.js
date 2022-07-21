class CustomerService {

    basicUrl = "http://localhost:8080/customer";
    retrieveAllCustomers() {
        return fetch(this.basicUrl)
    }
    sendPromoToEmail(email){
        return fetch(this.basicUrl+'/sendpromo/'+email)
    }
}

export default new CustomerService()
