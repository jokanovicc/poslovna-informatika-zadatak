import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import NavBar from "./components/NavBar";
import Home from "./pages/Home"

import React,{Fragment} from "react";
import PrenosNovca from "./pages/PrenosNovca";

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
          </Routes>
        </Fragment>
      </Router>

  );
}
export default App;