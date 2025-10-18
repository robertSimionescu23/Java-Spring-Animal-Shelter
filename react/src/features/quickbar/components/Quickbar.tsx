import styles from './quickbar.module.css';
import pawButton from "../assets/pawButton.svg"
import phoneButton from "../assets/phoneButtonSource.svg"
import ButtonWithIcon from '../../../components/ButtonWithIcon';
import { useNavigate } from 'react-router-dom';

function QuickBar() {
    const navigate = useNavigate();
    return <div className={styles.bar}>
        <img onClick={() => navigate("/adopt")} className = {styles.buttonImage} src={pawButton}></img>
        <ButtonWithIcon path = "adopt" text = "Adopt"></ButtonWithIcon>
        <img className = {styles.buttonImage} src={phoneButton}></img>
        <ButtonWithIcon text = "Rescue 077xxxxxxx"></ButtonWithIcon>
    </div>
}

export default QuickBar;
