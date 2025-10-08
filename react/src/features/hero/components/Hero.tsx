import heroPicture from "../assets/hero3cropped.jpg";
import portrait1 from "../assets/portrait1.jpg";
import portrait2 from "../assets/heroPicture.jpg";
import portrait3 from "../assets/portrait3.jpg"
import styles from "./hero.module.css";

function Hero() {
    return <div className={styles.heroWrapper}>
        <img className = {styles.heroPic} src = {heroPicture}></img>
        <div className={styles.backgroundCircle}></div>
        <div className = {styles.heroTextWrapper}>
            <h1 className ={styles.mainText}>Spring Animal Shelter</h1>
            <h1 className ={styles.secText}>We flow together</h1>
        </div>
        <div className = {styles.portraitWrapper}>
            <img className={styles.portrait} src ={portrait1} ></img>
            <div className={styles.portraitColumn}>
                <img className={styles.portrait} src ={portrait2} />
                <img className={styles.portrait} src ={portrait3} />
            </div>
        </div>
    </div>
}

export default Hero;
