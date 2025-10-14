import styles from "./footer.module.css";
import githubLogo from "../assets/github-mark-white.svg"
import mail from "../assets/reshot-icon-email-P9WA8LS724.svg"

function Footer() {
    return <footer>
        {/* <p>Animal Shelter &copy; 2024</p> */}
        <img className = {styles.footerBackground}></img>
        <div className = {styles.footerContent}>
            <span className={styles.footerText}>The shelter is not real,<br/> but the love for animals <i>is</i>.</span>
            <div className={styles.divider}></div>
            <div className = {styles.contactBox}>
                <img src = {githubLogo} className={styles.icon}></img>
                <a href ={"https://github.com/robertSimionescu23"} className = {styles.contactText}>github.com/robertSimionescu23</a>
            </div>
            <div className = {styles.contactBox}>
                <img src = {mail} className={styles.icon}></img>
                <span className = {styles.contactText}>simionescurobert23@gmail.com</span>
            </div>
        </div>
    </footer>
}

export default Footer;
