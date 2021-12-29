import React from "react";
import {Button, Container, Nav, Navbar} from "react-bootstrap";
import { Link } from "react-router-dom";

const NavBar = () => {
    return (
        <Navbar bg="dark" variant="dark">
            <Container>
                <Navbar.Brand href="/">Б А Н К А</Navbar.Brand>
                <Nav className="me-auto">
                    <Nav.Link href="/prenos-novca">Prenos novca</Nav.Link>
                    <Nav.Link href="/izvod">Analitika</Nav.Link>
                </Nav>
            </Container>
        </Navbar>)
};

export default NavBar;