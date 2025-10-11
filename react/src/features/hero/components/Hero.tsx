import styles from "./hero.module.css";

function Hero() {
    return <div className={styles.heroWrapper}>
        <div className = {styles.heroPicWrapper}>
            <img className ={`${styles.heroPic} ${styles.frame1}`}></img>
            <img className ={`${styles.heroPic} ${styles.frame2}`}></img>
            <img className ={`${styles.heroPic} ${styles.frame3}`}></img>
        </div>
        {/* <img className ={`${styles.heroPic} ${styles.frame2}`}></img> */}

        <div className={styles.backgroundCircle}></div>
        <div className = {styles.heroTextWrapper}>
            <h1 className ={styles.mainText}>Spring Animal Shelter</h1>
            <h1 className ={styles.secText}>We flow together</h1>
        </div>
        {/* <div className = {styles.portraitWrapper}>
            <img className={styles.portrait} src ={portrait1} ></img>
            <div className={styles.portraitColumn}>
                <img className={styles.portrait} src ={portrait2} />
                <img className={styles.portrait} src ={portrait3} />
            </div>
        </div> */}
    </div>
}

export default Hero;
