import React, {Component} from "react";
import StaffService from "../../service/StaffService";
import {Link} from "react-router-dom";
import {Button, Container} from "reactstrap";
import AppNavbar from "../nav/AppNavbar";
import './Staff.css'

class StaffList extends Component{
    constructor(props) {
        super(props);
        this.state = {staff: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        StaffService.retrieveAllStaff()
            .then(response => response.json())
            .then(data => this.setState({staff:data}));
    }

    async remove(id) {
        StaffService.deleteStaffById(id)
            .then(() => {
            let updatedStaff = [...this.state.staff].filter(i => i.id !== id);
            this.setState({staff: updatedStaff});
        });
    }

    render() {
        const {staff} = this.state

        const staffList = staff.map(staff => {
            return (
                <tr key={staff.id}>
                    <td>{staff.id}</td>
                    <td>{staff.email}</td>
                    <td>{staff.name}</td>
                    <td>{staff.surname}</td>
                    <td>{staff.lastname}</td>
                    <td>{staff.phone}</td>
                    <td>{staff.salary}</td>
                    <td>{staff.role}</td>
                    <td>
                        <Button tag={Link} to={"/staff/" + staff.id} className="list-button" color="success"> Edit</Button>
                    </td>
                    <td>
                        <Button onClick={() => this.remove(staff.id)} className="list-button" color="danger">Remove</Button>
                    </td>
                </tr>
            )
        })
        return(
            <div>
                <AppNavbar/>
                <Container fluid className='list'>
                    <div className="float-end">
                        <Button color="primary" tag={Link} to="/staff/new">Add Staff</Button>
                    </div>
                    <h4>Staff</h4>
                    <table className="table table-bordered  border-dark">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Email</th>
                            <th>Name</th>
                            <th>Surname</th>
                            <th>Lastname</th>
                            <th>Phone</th>
                            <th>Salary</th>
                            <th>Role</th>
                            <th>Modify</th>
                            <th>Delete</th>
                        </tr>
                        </thead>
                        <tbody>
                        {staffList}
                        </tbody>
                    </table>
                </Container>
            </div>
        )
    }
}
export default StaffList;
