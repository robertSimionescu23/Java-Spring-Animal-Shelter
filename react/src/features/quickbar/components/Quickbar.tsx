import styles from './quickbar.module.css';
import pawButton from "../assets/pawButton.svg"
import phoneButton from "../assets/phoneButtonSource.svg"
import Button from '../../../components/button';

function QuickBar() {
    return <div className={styles.bar}>
        <img className = {styles.buttonImage} src={pawButton}></img>
        <Button text = "Adopt"></Button>
        <img className = {styles.buttonImage} src={phoneButton}></img>
        <Button text = "Rescue 077xxxxxxx"></Button>
    </div>
}

export default QuickBar;
