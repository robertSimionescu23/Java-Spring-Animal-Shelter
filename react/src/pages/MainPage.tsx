import Divider from '../components/Divider';
import AboutUs from '../features/aboutUs/components/AboutUs';
import Footer from '../features/footer/components/Footer';
import Hero from '../features/hero/components/Hero';
import Navbar from '../features/navbar/components/Navbar';
import QuickBar from '../features/quickbar/components/Quickbar';

import {useRef} from "react";
function MainPage() {

    //TODO: In the actual catalogue, make them all in a list with traits
    const aboutUsRef = useRef<HTMLHeadingElement | null>(null);
    return (
    <>
        <QuickBar></QuickBar>
        <Navbar aboutUsRef={aboutUsRef}></Navbar>
        <Hero></Hero>
        <Divider></Divider>
        <AboutUs ref = {aboutUsRef}></AboutUs>
        <Divider></Divider>
        <Footer></Footer>
    </>
  )
}

export default MainPage
