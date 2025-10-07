import logo from "../../../assets/Logo.svg";
import styles from "./navbar.module.css"

function Navbar() {
    return <div className={styles.nav}>
        <div className={styles.logoWrapper}>
            <img className={styles.logo} src = {logo}></img>
            <h1 className = {styles.name}>Spring <br/>Animal Shelter</h1>
        </div>
        <div className = {styles.buttonWrapper}>
            <button className = {`${styles.name} ${styles.navButton}`}>Adoptii</button>
            <button className = {`${styles.name} ${styles.navButton}`}>Misiune</button>
            <button className = {`${styles.name} ${styles.navButton}`}>Despre Noi</button>
        </div>
    </div>
}
export default Navbar;
