class StaffService {

    retrieveAllStaff() {
        return fetch("http://localhost:8080/staff")
    }

    retrieveStaffById(id) {
        return fetch('http://localhost:8080/staff/${id}')
    }

    retrieveStaffByEmail(email) {
        return fetch('http://localhost:8080/staff/${email}')
    }

    addStaff(staff) {
        return fetch("http://localhost:8080/staff", {
            method: 'POST',
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(staff)
        })
    }
}

export default new StaffService()