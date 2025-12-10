import React from "react";
import styles from "./animalSchedule.module.css"
import saschaImg from "../assets/sasha.jpg"
import AnimalScheduleGrid from "./AnimalScheduleGrid";

function AnimalSchedule(): React.ReactElement {

    return(
        <section className = {styles.mainWrapper}>
            <AnimalScheduleGrid/>
            {/* TODO:Make this be the photo from the backend */}
            {/* TODO:Make this a full picture show off */}
            {/* TODO:Adjust styling of this page once functionality is set  */}
            {/* TODO: Make choosing the calendar date possibile*/}
            <div className={styles.animalAbout}>
                <img src = {saschaImg} className = {styles.animalPic}></img>
                <div className = {styles.animalInfo}></div>
            </div>
        </section>
    )
}

export default AnimalSchedule;
