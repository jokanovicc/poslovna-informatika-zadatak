import {Col, Row, Container, Image, Button, Nav} from "react-bootstrap";
import React from "react";
import {Link} from "react-router-dom";


const Home = () => {
    return(
        <Container style={{ textAlign: "center" }}>
            <h1>Пословни систем банке</h1>
            <div className="mb-2">
                <Button variant="success" as={Link} to="/prenos-novca"  size="lg">
                    Пренос новца
                </Button>
                <Button variant="success" size="lg">
                    Аналитика извода
                </Button>
            </div>
            <div>
                <Button variant="success" size="sm">
                    Дневно стање клијента
                </Button>
            </div>
        </Container>

    );
}
export default Home;