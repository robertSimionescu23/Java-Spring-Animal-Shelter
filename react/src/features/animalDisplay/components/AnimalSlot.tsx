import styles from './animalDisplay.module.css';
import testPic from "../assets/sasha-sashina-YCsh4ltV9Ec-unsplash.jpg"

//TODO: work on text
function AnimalSlot({alignment}:{alignment: string}) {
    return alignment == "left"?
    <div className = {`${styles.slot} ${styles.left}`}>
        <div className = {styles.description}>
            <h2 className = {styles.nameLeft}>Sascha</h2>
            <span className = {styles.slotText}>This is Sasha, a lovely dog looking for a new home. She is very friendly and loves to play. She is good with children and other pets. If you are interested in adopting her, please contact us!</span>
        </div>
        <img className = {`${styles.slotPic} ${styles.slotPicLeft}`} src = {testPic}></img>
    </div>
    :
    <div className = {`${styles.slot} ${styles.right}`}>
        <div className = {styles.description}>
            <h2 className = {styles.nameRight}>Sascha</h2>
            <span className = {styles.slotText}>This is Sasha, a lovely dog looking for a new home. She is very friendly and loves to play. She is good with children and other pets. If you are interested in adopting her, please contact us!</span>
        </div>
        <img className = {`${styles.slotPic} ${styles.slotPicRight}`} src = {testPic}></img>
    </div>

}

export default AnimalSlot;
