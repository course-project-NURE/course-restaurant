import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import './AppNavbar.css'

export default class AppNavbar extends Component {
    constructor(props) {
        super(props);
        this.state = {isOpen: false};
        this.toggle = this.toggle.bind(this);
    }

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    render() {
        return(
            <div className="mid">
                <ul className="nav-bar">
                    <li><Link to="/customer">Customer</Link></li>
                    <li><Link to="/staff">Staff</Link></li>
                    <li><Link to="/menu-item">Menu item</Link></li>
                </ul>
            </div>
        )
    }
}
