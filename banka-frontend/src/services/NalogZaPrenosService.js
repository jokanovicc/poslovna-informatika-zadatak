import axios from "axios";


export const NalogZaPrenosService = {

    addNalogZaPrenos,
    fetchRacuniKlijenata

}

async function addNalogZaPrenos(nalogZaPrenos){
    return await axios.post("http://localhost:8080/api/analitike-izvoda/nalog-za-prenos",nalogZaPrenos);
}

async function fetchRacuniKlijenata(){
    return await  axios.get("http://localhost:8080/api/racuni/racuni-klijenata")
}

