import { Link } from "react-router-dom";
import logo from "../../../assets/Logo.svg";
import styles from "./navbar.module.css"
import { useNavigate, type NavigateFunction } from "react-router-dom";

function Navbar({aboutUsRef, footerRef} : {aboutUsRef?: React.RefObject<HTMLHeadingElement | null>, footerRef?: React.RefObject<HTMLHeadingElement | null>}) : React.ReactElement {
    const navigate: NavigateFunction = useNavigate();
    function scrollToAboutUs(): void {
        if (aboutUsRef && aboutUsRef.current) {
            aboutUsRef.current.scrollIntoView({ behavior: 'smooth' });
        }
    }

     function scrollToFooter(): void {
        if (footerRef && footerRef.current) {
            footerRef.current.scrollIntoView({ behavior: 'smooth' });
        }
    }
    return <nav className={styles.nav}>
        <div className={styles.logoWrapper}>
            <img onClick={() =>navigate("/")} className={styles.logo} src = {logo}></img>
            <h1 onClick={() =>navigate("/")} className = {styles.name}>Spring <br/>(Fictual) Animal Shelter</h1>
        </div>
        <div className = {styles.buttonWrapper}>
            {aboutUsRef && <Link to = "/adopt" className = {`${styles.name} ${styles.navButton}`}><b>Fictual Adoption</b></Link>}
            {aboutUsRef && <button className = {`${styles.name} ${styles.navButton}`} onClick={() => scrollToAboutUs()}><b>About us</b></button>}
            {footerRef  && <button className = {`${styles.name} ${styles.navButton}`} onClick={() => scrollToFooter()}><b>Contact us</b></button>}
        </div>
    </nav>
}
export default Navbar;
