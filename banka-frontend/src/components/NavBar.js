import React from "react";
import {Button, Container, Nav, Navbar} from "react-bootstrap";
import { Link } from "react-router-dom";

const NavBar = () => {
    return (
        <Navbar bg="dark" variant="dark">
            <Navbar.Brand href="#home">Б А Н К А</Navbar.Brand>
            <Navbar.Toggle />
            <Navbar.Collapse className="justify-content-end">
                <Navbar.Text>
                    z a p o s l e n i
                </Navbar.Text>
            </Navbar.Collapse>
        </Navbar>)
};

export default NavBar;