import {Col, Row, Container, Image, Button, Nav} from "react-bootstrap";
import React from "react";
import {Link} from "react-router-dom";
import Izvod from "./Izvod";


const Home = () => {
    return(
        <Container style={{ textAlign: "center", marginTop:"75px"}}>
            <h1 style={{color:"white"}}>Posloni sistem banke</h1>
            <hr style={{backgroundColor:"white"}}/>
            <div className="mb-3">
                <Button style={{marginRight:"10px"}} variant="success" as={Link} to="/prenos-novca"  size="lg">
                    Пренос новца
                </Button>
                <Button variant="success" as={Link} to="/izvod" size="lg">
                    Аналитика извода
                </Button>
            </div>

        </Container>

    );
}
export default Home;