import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import MainPage from "./pages/MainPage";
import Adopt from "./pages/Adopt";
import AnimalPage from "./pages/AnimalPage";
import AnimalSchedulePage from "./pages/AnimalSchedulePage";

function App(): React.ReactElement {
//TODO: In the actual catalogue, make them all in a list with traits
  return (
    <Router>
        <Routes>
            <Route path = "/" element = {<MainPage/>}/>
            {/* TODO: Implement the adoption page as a grid */}
            <Route path = "/adopt" element = {<Adopt/>}/>
            <Route path = "/adopt/:id" element = {<AnimalPage/>}/>
            <Route path = "/adopt/:id/schedule" element = {<AnimalSchedulePage/>}/>

        </Routes>
    </Router>
  )
}

export default App
