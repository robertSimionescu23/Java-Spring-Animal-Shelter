import Divider from "../../../components/Divider";
import styles from './animalDisplay.module.css';
import AnimalSlot from "./AnimalSlot";

function AnimalDisplay() {
    return <>
        <Divider/>
        <div className = {styles.animalDisplayWrapper}>
            <h1>Meet our friends</h1>
            <span className = {styles.divider}></span>
            <p className = {styles.welcomeTxt}>We're glad you're here!<br/>We welcome all animals, and we welcome you to befriend them!</p>
            <AnimalSlot alignment = "left"></AnimalSlot>
            <AnimalSlot alignment = "right"></AnimalSlot>
            <AnimalSlot alignment = "left"></AnimalSlot>
            <AnimalSlot alignment = "right"></AnimalSlot>
            <p className = {styles.welcomeTxt}>Come meet all of them!</p>
        </div>
    </>
}

export default AnimalDisplay;
