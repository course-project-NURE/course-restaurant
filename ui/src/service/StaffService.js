
class StaffService {

    basicUrl = "http://localhost:8080/staff";

    retrieveAllStaff() {
        return fetch(this.basicUrl)
    }

    retrieveStaffById(id) {
        return fetch(this.basicUrl + '/' + id)
    }

    deleteStaffById(id){
        return fetch(this.basicUrl + '/' + id, {
            method: 'DELETE',
            headers:{"Content-Type":"application/json"}
        })
    }

    saveStaff(staff){
        const url = this.basicUrl + (staff.id ? '/' + staff.id : '')
        return fetch(url, {
            method: (staff.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(staff),
        })
    }
}

export default new StaffService()
