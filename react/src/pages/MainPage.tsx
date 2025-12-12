import Divider from '../components/Divider';
import AboutUs from '../features/aboutUs/components/AboutUs';
import Footer from '../features/footer/components/Footer';
import Hero from '../features/hero/components/Hero';
import Navbar from '../features/navbar/components/Navbar';
import QuickBar from '../features/quickbar/components/Quickbar';

import {useRef} from "react";
function MainPage(): React.ReactElement {

    //TODO: In the actual catalogue, make them all in a list with traits
    const aboutUsRef: React.RefObject<HTMLHeadingElement | null> = useRef(null);
    const footerRef: React.RefObject<HTMLHeadingElement | null> = useRef(null);
    return (
    <>
        <QuickBar></QuickBar>
        <Navbar aboutUsRef={aboutUsRef} footerRef={footerRef}></Navbar>
        <Hero></Hero>
        <Divider></Divider>
        <AboutUs ref = {aboutUsRef}></AboutUs>
        <Divider></Divider>
        <Footer ref = {footerRef}></Footer>
    </>
  )
}

export default MainPage
