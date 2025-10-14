import styles from './animalDisplay.module.css';
import testPic from "../assets/sasha-sashina-YCsh4ltV9Ec-unsplash.jpg"
import ButtonWithIcon from '../../../components/ButtonWithIcon';
import pawIcon from "../../../assets/reshot-icon-paw-7EGRM82W6Z.svg";

//TODO: Figure how to get images from backend
function AnimalSlot({alignment}:{alignment: string}) {
    return alignment == "left"?
    <div className = {`${styles.slot} ${styles.left}`}>
        <div className = {styles.description}>
            <h1 className = {styles.nameLeft}>Sascha</h1>
            <span className = {styles.slotText}>This is Sasha, a lovely dog looking for a new home. She is very friendly and loves to play. She is good with children and other pets. If you are interested in adopting her, please contact us!</span>
            <div className={styles.buttonWrapper}>
                <img src={pawIcon} width={"50px"} height={"50px"} style={{position: "relative", right: "-25px"}}></img>
                <ButtonWithIcon text="Vist me!"></ButtonWithIcon>
            </div>
        </div>
        <img className = {`${styles.slotPic} ${styles.slotPicLeft}`} src = {testPic}></img>
    </div>
    :
    <div className = {`${styles.slot} ${styles.right}`}>
        <div className = {styles.description}>
            <h1 className = {styles.nameRight}>Sascha</h1>
            <span className = {styles.slotText}>This is Sasha, a lovely dog looking for a new home. She is very friendly and loves to play. She is good with children and other pets. If you are interested in adopting her, please contact us!</span>
            <div className={styles.buttonWrapper}>
                <img src={pawIcon} width={"50px"} height={"50px"} style={{position: "relative", right: "-25px"}}></img>
                <ButtonWithIcon text="Vist me!"></ButtonWithIcon>
            </div>
        </div>
        <img className = {`${styles.slotPic} ${styles.slotPicRight}`} src = {testPic}></img>
    </div>

}

export default AnimalSlot;
