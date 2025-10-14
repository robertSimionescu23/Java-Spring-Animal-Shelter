import styles from './quickbar.module.css';
import pawButton from "../assets/pawButton.svg"
import phoneButton from "../assets/phoneButtonSource.svg"
import ButtonWithIcon from '../../../components/ButtonWithIcon';

function QuickBar() {
    return <div className={styles.bar}>
        <img className = {styles.buttonImage} src={pawButton}></img>
        <ButtonWithIcon text = "Adopt"></ButtonWithIcon>
        <img className = {styles.buttonImage} src={phoneButton}></img>
        <ButtonWithIcon text = "Rescue 077xxxxxxx"></ButtonWithIcon>
    </div>
}

export default QuickBar;
