import {useEffect, useState} from 'react'
import StaffService from '../service/StaffService'
import {Button, TextField} from "@material-ui/core";

function Staff () {
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
        email,
        password,
        name,
        surname,
        lastname,
        phone,
        salary,
        role
    }

    const handleClick = (e) => {
        e.preventDefault()
        console.log(staff)
        StaffService.addStaff(staff).then(() => {
            console.log('New staff added')
        })
    }

    const handleEditClick = (e) => {
        e.preventDefault()
        return (
            <div className="container">
                Test
            </div>
        )
    }

    useEffect(() => {
        StaffService.retrieveAllStaff()
            .then(response => response.json())
            .then(result => setAllStaff(result))
    }, [])

    return (
        <div className={'container'}>
            <h1>All Staff</h1>
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
                            <th>Salary</th>
                            <th>Role</th>
                            <th>Modify</th>
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
                                    <td><button onClick={handleEditClick}>Edit</button></td>
                                </tr>
                        )
                    }
                    </tbody>
                </table>
            </div>
            <h1>Add Staff</h1>
            <div className={'container'}>
                <form className={'form'} noValidate autoComplete="off">
                    <TextField id="outlined-basic" label="email" variant="outlined" fullWidth
                               value={email}
                               onChange={(e)=>setEmail(e.target.value)}
                    /><TextField id="outlined-basic" label="password" variant="outlined" fullWidth
                               value={password}
                               onChange={(e)=>setPassword(e.target.value)}
                    />
                    <TextField id="outlined-basic" label="name" variant="outlined" fullWidth
                               value={name}
                               onChange={(e)=>setName(e.target.value)}
                    />
                    <TextField id="outlined-basic" label="surname" variant="outlined" fullWidth
                               value={surname}
                               onChange={(e)=>setSurname(e.target.value)}
                    />
                    <TextField id="outlined-basic" label="lastname" variant="outlined" fullWidth
                               value={lastname}
                               onChange={(e)=>setLastname(e.target.value)}
                    />
                    <TextField id="outlined-basic" label="phone" variant="outlined" fullWidth
                               value={phone}
                               onChange={(e)=>setPhone(e.target.value)}
                    />
                    <TextField id="outlined-basic" label="salary" variant="outlined" fullWidth
                               value={salary}
                               onChange={(e)=>setSalary(e.target.value)}
                    />
                    <TextField id="outlined-basic" label="role" variant="outlined" fullWidth
                               value={role}
                               onChange={(e)=>setRole(e.target.value)}
                    />
                    <Button onClick={handleClick}>
                        Submit
                    </Button>
                </form>
            </div>
        </div>
    )
}

export default Staff
