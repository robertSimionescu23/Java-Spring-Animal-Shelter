import styles from './button.module.css';

function ButtonWithIcon({text}: {text: string}) {
    return <button className = {styles.buttonWithIcon}>{text}</button>
}

export default ButtonWithIcon;
