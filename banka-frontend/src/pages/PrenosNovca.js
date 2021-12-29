import {Button, Col, Container, Form, Row} from "react-bootstrap";
import React, {useEffect, useState} from "react";
import {NalogZaPrenosService} from "../services/NalogZaPrenosService";
import {useParams} from "react-router-dom";
import {Autocomplete, TextField} from "@mui/material";
import Swal from "sweetalert2";

const PrenosNovca = (props) => {

    const [nalogZaPrenos, setNalogZaPrenos] = useState({
        uplatilac:"",
        hitno:"",
        iznos:false,
        brojRacunaUplatioca:"",
        brojRacunaPrimaoca:"",
        svrhaPlacanja:""

    })
    const {id} = useParams();


    useEffect(()=> {
        fetchRacuni();
    },[id])

    const[racuni,setRacuni] = useState([]);

    async function fetchRacuni(){
        try {
            const response = await NalogZaPrenosService.fetchRacuniKlijenata();
            setRacuni(response.data);
            console.log(racuni);
        }catch (error){
            console.error(`Greška prilikom dobavljanja računa: ${error}`);

        }
    }





    const handleFormInputChange=(name)=>(event)=>{
        const val = event.target.value;

        setNalogZaPrenos({ ...nalogZaPrenos, [name]: val });
    }


    const handleChange = () => {
        nalogZaPrenos.hitno = !nalogZaPrenos.hitno;



    };

    const handleRacunUplatioca=(brojRacuna)=> {
        nalogZaPrenos.brojRacunaUplatioca = brojRacuna;

    }

    const handleRacunPrimaoca=(brojRacuna)=> {
        nalogZaPrenos.brojRacunaPrimaoca = brojRacuna;

    }

    const slanjeNaloga = async () => {
        if(nalogZaPrenos.uplatilac !== '' && nalogZaPrenos.brojRacunaPrimaoca != nalogZaPrenos.brojRacunaUplatioca && nalogZaPrenos.brojRacunaUplatioca != nalogZaPrenos.brojRacunaPrimaoca && nalogZaPrenos.svrhaPlacanja!=='' && nalogZaPrenos.iznos >100){

            await NalogZaPrenosService.addNalogZaPrenos(nalogZaPrenos);
            Swal.fire(
                'Odlično!',
                'Nalog je uspešno poslat!',
                'success'
            )

        }else{
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Izgleda da ste pogrešno uneli podatke!',
            })
        }

    }


    return(
        <Container style={{backgroundColor:"white", marginTop:"35px",padding:"20px"}}>
            <Row>
                <Col md={{ span: 6, offset: 3 }} style={{ textAlign: "center" }}>
                    <h1>Prenos novca</h1>
                    <hr/>
                    <Form>
                        <Form.Group>
                            <Form.Label>Ime i prezime uplatioca</Form.Label>
                            <Form.Control
                                type="text"
                                name="uplatilac"
                                value={nalogZaPrenos.uplatilac}
                                onChange={handleFormInputChange("uplatilac")}
                            />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Broj računa uplatioca</Form.Label>
                            <Autocomplete
                                disablePortal
                                id="racunUplatioca"
                                options={racuni}
                                getOptionLabel={option => option.brojRacuna}
                                onChange={(event, value) =>handleRacunUplatioca(value.brojRacuna)}
                                renderInput={(params) => <TextField {...params} label="Račun" />}
                            />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Iznos</Form.Label>
                            <Form.Control
                                type="number"
                                name="iznos"
                                value={nalogZaPrenos.iznos}
                                onChange={handleFormInputChange("iznos")}
                            />
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>Svrha plaćanja</Form.Label>
                            <Form.Control
                                as="textarea" rows={2}
                                name="svrhaPlacanja"
                                value={nalogZaPrenos.svrhaPlacanja}
                                onChange={handleFormInputChange("svrhaPlacanja")}
                            />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Broj računa primaoca</Form.Label>
                            <Autocomplete
                                disablePortal
                                id="racunPrimaoca"
                                options={racuni}
                                getOptionLabel={option => option.brojRacuna}
                                onChange={(event, value) =>handleRacunPrimaoca(value.brojRacuna)}
                                renderInput={(params) => <TextField {...params} label="Račun" />}
                            />
                        </Form.Group>


                        <Form.Group className="mb-4" controlId="formBasicCheckbox">
                            <Form.Check label="Hitno"
                                        required
                                        type="checkbox"
                                        name="hitno"
                                        value={nalogZaPrenos.hitno}
                                        onChange={handleChange}/>
                        </Form.Group>

                        <Button variant="success" onClick={slanjeNaloga}>
                            Pošalji novac
                        </Button>
                    </Form>
                </Col>
            </Row>
        </Container>


    );
}
export default PrenosNovca;