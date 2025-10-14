import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import MainPage from "./pages/MainPage";

function App() {
//TODO: In the actual catalogue, make them all in a list with traits
  return (
    <Router>
        <Routes>
            <Route path = "/" element = {<MainPage/>}/>
            {/* TODO: Implement the adoption page as a grid */}
            <Route path = "/adopt" element = {<></>}/>
        </Routes>
    </Router>
  )
}

export default App
