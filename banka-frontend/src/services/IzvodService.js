import axios from "axios";


export const IzvodService = {

    searchIzvod,
    searchKlijenti


}

async function searchIzvod(datum){
    return await axios.post("http://localhost:8080/api/analitike-izvoda/izvod-racuna",datum);
}

async function searchKlijenti(){
    return await axios.get("http://localhost:8080/api/klijenti");
}