import styles from "./hero.module.css";

function Hero() {
    return <div className={styles.heroWrapper}>
        <div className = {styles.heroPicWrapper}>
            {/* //TODO: Figure how to get images from backend */}
            <img className ={`${styles.heroPic} ${styles.frame1}`}></img>
            <img className ={`${styles.heroPic} ${styles.frame2}`}></img>
            <img className ={`${styles.heroPic} ${styles.frame3}`}></img>
        </div>

        <div className={styles.backgroundCircle}></div>
        <div className = {styles.heroTextWrapper}>
            <h1 className ={styles.mainText}>Spring Animal Shelter</h1>
            <h1 className ={styles.secText}>We flow together</h1>
        </div>
    </div>
}

export default Hero;
