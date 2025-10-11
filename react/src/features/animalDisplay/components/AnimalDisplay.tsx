import Divider from "../../../components/Divider";
import styles from './animalDisplay.module.css';
import AnimalSlot from "./AnimalSlot";

function AnimalDisplay() {
    return <>
        <Divider/>
        <div className = {styles.animalDisplayWrapper}>
            <h1>Meet our friends</h1>
            <AnimalSlot alignment = "left"></AnimalSlot>
            <AnimalSlot alignment = "right"></AnimalSlot>
        </div>
    </>
}

export default AnimalDisplay;
