import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import NavBar from "./components/NavBar";
import Home from "./pages/Home"

import React,{Fragment} from "react";
import PrenosNovca from "./pages/PrenosNovca";
import Izvod from "./pages/Izvod";
import Footer from "./components/Footer";

const App = () => {
  return (
      <Router>
        <Fragment>
          <NavBar/>
          <Routes>
            <Route exact path='/'>
              <Route exact path='/' element={<Home/>}/>
            </Route>
              <Route exact path='/prenos-novca'>
                  <Route exact path='/prenos-novca' element={<PrenosNovca/>}/>
              </Route>
              <Route exact path='/izvod'>
                  <Route exact path='/izvod' element={<Izvod/>}/>
              </Route>
          </Routes>
        </Fragment>
          <Footer/>
      </Router>

  );
}
export default App;