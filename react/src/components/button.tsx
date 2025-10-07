import styles from './button.module.css';

function Button({text}: {text: string}) {
    return <button className = {styles.button}>{text}</button>
}

export default Button;
