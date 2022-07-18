import React, {Component} from "react";
import {withRouter} from "react-router";
import StaffService from "../../service/StaffService";
import {Button, Container, Form, FormGroup, Input, Label} from "reactstrap";
import {Link} from "react-router-dom";
import AppNavbar from "../nav/AppNavbar";
import './Staff.css'

 class StaffEdit extends Component{
     emptyItem = {
         email: '',
         password:'',
         name: '',
         surname: '',
         lastname: '',
         phone: '',
         salary: '',
         role: ''
     };

     constructor(props) {
         super(props);
         this.state = {
             item: this.emptyItem
         };
         this.handleChange = this.handleChange.bind(this);
         this.handleSubmit = this.handleSubmit.bind(this);
     }

     async componentDidMount() {
         if (this.props.match.params.id !== 'new') {
             const staff = await (await (StaffService.retrieveStaffById(this.props.match.params.id))).json();
             this.setState({item: staff});
         }
     }

     handleChange(event) {
         const target = event.target;
         const value = target.value;
         const name = target.name;
         let item = {...this.state.item};
         item[name] = value;
         this.setState({item});
     }

     async handleSubmit(event) {
         event.preventDefault();
         const {item} = this.state;

         await StaffService.saveStaff(item);
         this.props.history.push('/staff');
     }

     render() {
         const {item} = this.state;
         const title = <h4>{item.id ? 'Edit Staff' : 'Add Staff'}</h4>;

         return <div>
             <AppNavbar/>
             <Container className={'list'}>
                 {title}
                 <Form onSubmit={this.handleSubmit}>
                     <FormGroup>
                         <Label for="email">Email</Label>
                         <Input type="text" name="email" id="email" value={item.email || ''}
                                onChange={this.handleChange} autoComplete="email"/>
                     </FormGroup>
                     <FormGroup>
                         <Label for="password">Password</Label>
                         <Input type="text" name="password" id="password" value={item.password || ''}
                                onChange={this.handleChange} autoComplete="password"/>
                     </FormGroup>
                     <FormGroup>
                         <Label for="name">Name</Label>
                         <Input type="text" name="name" id="name" value={item.name || ''}
                                onChange={this.handleChange} autoComplete="name"/>
                     </FormGroup>
                     <FormGroup>
                         <Label for="surname">Surname</Label>
                         <Input type="text" name="surname" id="surname" value={item.surname || ''}
                                onChange={this.handleChange} autoComplete="surname"/>
                     </FormGroup>
                     <FormGroup>
                         <Label for="lastname">Lastname</Label>
                         <Input type="text" name="lastname" id="lastname" value={item.lastname || ''}
                                onChange={this.handleChange} autoComplete="lastname"/>
                     </FormGroup>
                     <FormGroup>
                         <Label for="phone">Phone</Label>
                         <Input type="text" name="phone" id="phone" value={item.phone || ''}
                                onChange={this.handleChange} autoComplete="phone"/>
                     </FormGroup>
                     <FormGroup>
                         <Label for="salary">Salary</Label>
                         <Input type="text" name="salary" id="salary" value={item.salary || ''}
                                onChange={this.handleChange} autoComplete="salary"/>
                     </FormGroup>
                     <FormGroup>
                         <Label for="role">Role</Label>
                         <Input type="select" name="role" id="role" value={item.role || ''}
                                onChange={this.handleChange} autoComplete="role">
                             <option value={'ADMIN'}>Admin</option>
                             <option value={'COOK'}>Cook</option>
                             <option value={'MANAGER'}>Manager</option>
                             <option value={'DELIVERY_MANAGER'}>Delivery manager</option>
                             <option value={'COURIER'}>Courier</option>
                         </Input>
                     </FormGroup>
                     <FormGroup>
                         <Button color="primary" type="submit">Save</Button>{' '}
                         <Button color="secondary" tag={Link} to="/staff">Cancel</Button>
                     </FormGroup>
                 </Form>
             </Container>
         </div>
     }
}
export default withRouter(StaffEdit);
