import './App.css'
import Hero from './features/hero/components/Hero';
import Navbar from './features/navbar/components/Navbar';
import QuickBar from './features/quickbar/components/Quickbar';
function App() {
//TODO: For the main page have a grid, that when you hover, you get more details in a 2 box element
//TODO: In the actual catalogue, make them all in a list with traits
  return (
    <>
        <QuickBar></QuickBar>
        <Navbar></Navbar>
        <Hero></Hero>
    </>
  )
}

export default App
