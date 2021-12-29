import {Col, Container, Navbar} from "react-bootstrap";


const NavBar = () => {

    let fullYear = new Date().getFullYear();


    return(

    <Navbar fixed="bottom" bg="dark" variant="dark">
        <Container>
            <Col lg={12} className="text-center text-muted">
                <div>{fullYear}-{fullYear+1} Владимир Јокановић</div>
            </Col>
        </Container>
    </Navbar>);





}
export default NavBar;