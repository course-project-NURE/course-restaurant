import {useEffect, useState} from 'react'
import StaffService from '../../service/StaffService'
import {TextField} from "@material-ui/core";
import Button from 'react-bootstrap/Button';
import "./Staff.css"
import Select from 'react-select';

const Staff = () =>{
    const id = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [name, setName] = useState('')
    const [surname, setSurname] = useState('')
    const [lastname, setLastname] = useState('')
    const [phone, setPhone] = useState('')
    const [salary, setSalary] = useState('')
    const [role, setRole] = useState('')
    const [allStaff, setAllStaff] = useState([])
    const staff = {
        id,
        email,
        password,
        name,
        surname,
        lastname,
        phone,
        salary,
        role
    }

    const options = [
        { value: 'ADMIN', label: 'Admin' },
        { value: 'COOK', label: 'Cook' },
        { value: 'DELIVERY_MANAGER', label: 'Dalivery manager' },
        { value: 'COURIER', label: 'Courier' },
        { value: 'MANAGER', label: 'Manager' }
    ];

    const handleClick = (e) => {
        e.preventDefault()
        console.log(staff)
        StaffService.addStaff(staff).then(() => {
            console.log('New staff added')
        })
        window.location.reload(0);
    }

    const handleEditClick = (e) => {
        e.preventDefault()
        return (
            <div className="container">
                Test
            </div>
        )

    }

    const handleRemoveClick  = (id) => {
        console.log(id)
        StaffService.deleteStaffById(id).then(() => {
            console.log('Staff with an' + id + "was deleted" )
        })
        window.location.reload(0);
    }

    useEffect(() => {
        StaffService.retrieveAllStaff()
            .then(response => response.json())
            .then(result => setAllStaff(result))
    }, [])

    return (
        <div className={'container'}>
            <div className={'container'}>
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
                    {
                        allStaff.map(
                            staff =>
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
                                        <button
                                            onClick={handleEditClick}
                                            className="edit">
                                        Edit
                                        </button>
                                    </td>
                                    <td>
                                        <button
                                            onClick={() => handleRemoveClick(staff.id)}
                                            className="remove">
                                            Remove
                                        </button>
                                    </td>
                                </tr>
                        )
                    }
                    </tbody>
                </table>
            </div>
            <div className={'container'}>
                <h5>Add Staff</h5>
                <form className="form" noValidate autoComplete="off">
                    <div className="save-input">
                        <TextField id="outlined-basic" label="email" variant="filled" fullWidth
                                   value={email}
                                   onChange={(e)=>setEmail(e.target.value)}

                        />
                    </div>
                    <div className="save-input">
                        <TextField id="outlined-basic" label="password" variant="filled" fullWidth
                                   value={password}
                                   onChange={(e)=>setPassword(e.target.value)}
                        />
                    </div>
                    <div className="save-input">
                        <TextField id="outlined-basic" label="name" variant="filled" fullWidth
                                   value={name}
                                   onChange={(e)=>setName(e.target.value)}
                        />
                    </div>
                    <div className="save-input">
                        <TextField id="outlined-basic" label="surname" variant="filled" fullWidth
                                   value={surname}
                                   onChange={(e)=>setSurname(e.target.value)}
                        />
                    </div>
                    <div className="save-input">
                        <TextField id="outlined-basic" label="lastname" variant="filled" fullWidth
                                   value={lastname}
                                   onChange={(e)=>setLastname(e.target.value)}
                        />
                    </div>
                    <div className="save-input">
                        <TextField id="outlined-basic" label="phone" variant="filled" fullWidth
                                   value={phone}
                                   onChange={(e)=>setPhone(e.target.value)}
                        />
                    </div>
                    <div className="save-input">
                        <TextField id="outlined-basic" label="salary" variant="filled" fullWidth
                                   value={salary}
                                   onChange={(e)=>setSalary(e.target.value)}
                        />
                    </div>
                    <div className="save-input">
                        <TextField id="outlined-basic" label="role" variant="filled" fullWidth
                                   value={role}
                                   onChange={(e)=>setRole(e.target.value)}
                        />
                        {/*<Select*/}
                        {/*    value={role}*/}
                        {/*    onChange={(e)=>setRole(e.target.value)}*/}
                        {/*    options={options}*/}
                        {/* />*/}
                    </div>
                    <Button onClick={handleClick}  variant="primary">
                        Submit
                    </Button>
                </form>
            </div>
        </div>
    )
}

export default Staff;
