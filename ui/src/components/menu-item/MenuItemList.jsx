import React, {Component} from "react";
import MenuItemService from "../../service/MenuItemService";
import {Link} from "react-router-dom";
import {Button, Container} from "reactstrap";
import AppNavbar from "../nav/AppNavbar";

class MenuItemList extends Component{
    constructor(props) {
        super(props);
        this.state = {menuItems: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        MenuItemService.retrieveAllMenuItems()
            .then(response => response.json())
            .then(data => this.setState({menuItems:data}));
    }

    async remove(id) {
        MenuItemService.deleteMenuItemById(id)
            .then(() => {
                let updatedMenuItems = [...this.state.menuItems].filter(i => i.id !== id);
                this.setState({menuItems: updatedMenuItems});
            });
    }

    render() {
        const {menuItems} = this.state

        const listMenuItems = menuItems.map(menuItem => {
            return (
                <tr key={menuItem.id}>
                    <td>{menuItem.id}</td>
                    <td>{menuItem.title}</td>
                    <td>{menuItem.price}</td>
                    <td>{menuItem.description}</td>
                    <td>{menuItem.category}</td>
                    <td>
                        <Button tag={Link} to={"/menu-item/" + menuItem.id} className="list-button" color="success">Edit</Button>
                    </td>
                    <td>
                        <Button onClick={() => this.remove(menuItem.id)} className="list-button" color="danger">Remove</Button>
                    </td>
                </tr>
            )
        })
        return(
            <div>
                <AppNavbar/>
                <Container fluid className='list'>
                    <div className="float-end">
                        <Button color="primary" tag={Link} to="/menu-item/new">Add Menu Item</Button>
                    </div>
                    <h4>Menu Items</h4>
                    <table className="table table-bordered  border-dark">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Price</th>
                            <th>Description</th>
                            <th>Category</th>
                            <th>Modify</th>
                            <th>Delete</th>
                        </tr>
                        </thead>
                        <tbody>
                        {listMenuItems}
                        </tbody>
                    </table>
                </Container>
            </div>
        )
    }
}
export default MenuItemList;
