import logo from "../../../assets/Logo.svg";
import styles from "./navbar.module.css"

function Navbar({aboutUsRef} : {aboutUsRef: React.RefObject<HTMLHeadingElement | null>}) {

    function scrollToAboutUs() {
        if (aboutUsRef && aboutUsRef.current) {
            aboutUsRef.current.scrollIntoView({ behavior: 'smooth' });
        }
    }
    return <div className={styles.nav}>
        <div className={styles.logoWrapper}>
            <img className={styles.logo} src = {logo}></img>
            <h1 className = {styles.name}>Spring <br/>Animal Shelter</h1>
        </div>
        <div className = {styles.buttonWrapper}>
            <button className = {`${styles.name} ${styles.navButton}`}><b>Adopt</b></button>
            <button className = {`${styles.name} ${styles.navButton}`} onClick={() => scrollToAboutUs()}><b>About us</b></button>
        </div>
    </div>
}
export default Navbar;
