import React, {Component} from "react";
import {withRouter} from "react-router";
import {Button, Container, Form, FormGroup, Input, Label} from "reactstrap";
import {Link} from "react-router-dom";
import AppNavbar from "../nav/AppNavbar";
import MenuItemService from "../../service/MenuItemService";

class MenuItemEdit extends Component{
    emptyItem = {
        title: '',
        price:'',
        description: '',
        category: ''
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
            const menuItem = await (await (MenuItemService.retrieveMenuItemById(this.props.match.params.id))).json();
            this.setState({item: menuItem});
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

        await MenuItemService.saveMenuItem(item);
        this.props.history.push('/menu-item');
    }

    render() {
        const {item} = this.state;
        const title = <h4>{item.id ? 'Edit Menu Item' : 'Add Menu Item'}</h4>;

        return <div>
            <AppNavbar/>
            <Container className={'list'}>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="title">Title</Label>
                        <Input type="text" name="title" id="title" value={item.title || ''}
                               onChange={this.handleChange} autoComplete="title"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="price">Price</Label>
                        <Input type="text" name="price" id="price" value={item.price || ''}
                               onChange={this.handleChange} autoComplete="price"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="description">Description</Label>
                        <Input type="text" name="description" id="description" value={item.description || ''}
                               onChange={this.handleChange} autoComplete="description"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="category">Category</Label>
                        <Input type="select" name="category" id="category" value={item.category || ''}
                               onChange={this.handleChange} autoComplete="role">
                            <option value={'PIZZA'}>Pizza</option>
                            <option value={'SUSHI'}>Sushi</option>
                            <option value={'NONALCOHOLIC'}>Nonalcoholic</option>
                            <option value={'ALCOHOLIC'}>Alcoholic</option>
                            <option value={'WOK'}>Courier</option>
                            <option value={'DESSERT'}>Alcoholic</option>
                            <option value={'SOUP'}>Courier</option>
                        </Input>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/menu-item">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}
export default withRouter(MenuItemEdit);
