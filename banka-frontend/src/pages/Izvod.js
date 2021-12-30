import React, {useEffect, useState} from "react";
import {Button, Card, Col, Container, Form, Row, Toast} from "react-bootstrap";
import axios from "axios";
import MaterialTable from "material-table";
import {useParams} from "react-router-dom";
import {NalogZaPrenosService} from "../services/NalogZaPrenosService";
import {Autocomplete, TextField} from "@mui/material";
import jsPDF from "jspdf";
import 'jspdf-autotable'
import {IzvodService} from "../services/IzvodService";

const Izvod = (props) => {
    const[analitike,setAnalitike] = useState([]);
    const[datum,setDatum] = useState({
        startDate:'',
        endDate:'',
        id:0
    })

    const{id} = useParams();

    const columns = [
        {title:"Broj stavke",field:"brojStavke"},
        {title:"Racun dužnika",field:"racunDuznika"},
        {title:"Racun primaoca",field:"racunPrimaoca"},
        {title:"Iznos",field:"iznos"},
        {title:"Svrha",field:"svrhaPlacanja"},
        {title:"Datum prenosa",field:"vremePrenosa"},


    ]

    useEffect(()=> {
        fetchKlijenti();
    },[id])

    const[klijenti,setKlijenti] = useState([]);


    const handleFormInputChange = (name) => (event) => {
        const val = event.target.value;
        setDatum({ ...datum, [name]: val });
    };

    async function fetchKlijenti(){
        try {
            const response = await IzvodService.searchKlijenti();
            setKlijenti(response.data);
        }catch (error){
            console.error(`Greška prilikom dobavljanja korisnika: ${error}`);

        }
    }


    const handleKlijent=(id)=> {
        datum.id = id;

    }




    async function fetchIzvod(){
        try{
            console.log(datum);
            const response = await IzvodService.searchIzvod(datum);
            setAnalitike(response.data);
        }catch (error){
            console.error(`Greška prilikom dobavljanja analitika : ${error}`);
        }
    }

    const downloadPdf = () => {
        const doc = new jsPDF();
        let text = 'Analitika Izvoda od ' + datum.startDate + ' do ' + datum.endDate
        doc.text(text,20,10);
        doc.autoTable({
            theme:"grid",
            columns:columns.map(col => ({...col,dataKey:col.field})),
            body: analitike
        })
        doc.save('izvod.pdf');

    }



    return (
        <Container>
            <Card style={{ width: '69rem', marginTop: '50px',marginBottom:'50px',padding:'50px'}}>
                <Row>
                    <Col md={{ span: 6, offset: 3 }} style={{ textAlign: "center" }}>
                        <h2>Analitike izvoda</h2>
                        <hr/>
                        <Form>
                            <Form.Group>
                                <Form.Label>Početni datum</Form.Label>
                                <Form.Control
                                    max="2022-12-12"
                                    min="2019-12-12"
                                    required
                                    type="date"
                                    name="startDate"
                                    value={datum.startDate}
                                    onChange={handleFormInputChange("startDate")}
                                />
                            </Form.Group>
                            <Form.Group>
                                <Form.Label>Završni datum</Form.Label>
                                <Form.Control
                                    max="2022-12-12"
                                    min="2019-12-12"
                                    required
                                    type="date"
                                    name="endDate"
                                    value={datum.endDate}
                                    onChange={handleFormInputChange("endDate")}
                                />
                            </Form.Group>

                            <Form.Group>
                                <Form.Label>Klijent</Form.Label>
                                <Autocomplete
                                    color={"white"}
                                    disablePortal
                                    id="id"
                                    options={klijenti}
                                    getOptionLabel={option => option.ime}
                                    onChange={(event, value) =>handleKlijent(value.id)}
                                    renderInput={(params) => <TextField {...params} label="Klijent" />}
                                />
                            </Form.Group>



                            <Button variant="success" onClick={fetchIzvod}>
                                Pogledaj
                            </Button>
                        </Form>
                    </Col>
                </Row>
            </Card>

            {analitike.length > 0 &&
            <Card style={{ width: '69rem', margin: 'auto',textAlign:"center", marginBottom:"50px"}} className={"border border-dark bg-dark text-white"}>
                <MaterialTable columns={columns}
                               title={"Izvod računa"}
                               data={analitike}
                               actions={[
                                   {
                                       icon: () => <Button>PDF</Button>,
                                       tooltip: "Export to pdf",
                                       onClick: () => downloadPdf(),
                                       isFreeAction: true
                                   }
                               ]}


                />
            </Card>
            }
        </Container>
    );

}

export default Izvod;
