import styles from "./footer.module.css";
import githubLogo from "../assets/github-mark-white.svg"
import mail from "../assets/reshot-icon-email-P9WA8LS724.svg"

function Footer({ref} : {ref: React.RefObject<HTMLHeadingElement | null>}): React.ReactElement {
    return <footer id="Contact" ref = {ref}>
        <img className = {styles.footerBackground}></img>
        <div className = {styles.footerContent}>
            <span className={styles.footerText}><i>Please</i> support your local animal shelters.<br/></span>
            <span className={styles.footerTextNoUnderline}>This shelter is not real, but the love for animals <i>is</i>.<br/><br/></span>
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
