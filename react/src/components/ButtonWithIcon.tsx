import { Link } from 'react-router-dom';
import styles from './button.module.css';

function ButtonWithIcon({text, path}: {text: string, path?: string}) {
    return path? <Link to = {`/${path}`} className = {styles.buttonWithIcon}>{text}</Link>:
    <button className = {styles.buttonWithIcon}>{text}</button>
}

export default ButtonWithIcon;
