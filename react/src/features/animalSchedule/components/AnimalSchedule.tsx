import { data, useParams } from "react-router-dom";
import styles from "./animalSchedule.module.css"
import saschaImg from "../assets/sasha.jpg"
import { startTransition, useState } from "react";

function AnimalSchedule(){

    const hours:string[] = ["8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00"];
    const days:string[] = ["Mon", "Tue", "Wen", "Thu", "Fri", "Sat", "Sun"];

    const [mondaySchedule] = useState<boolean[]>(
        Array(hours.length * 4).fill(false)
    );

    const [tuesdaySchedule] = useState<boolean[]>(
        Array(hours.length * 4).fill(false)
    );

    const [wednesdaySchedule] = useState<boolean[]>(
        Array(hours.length * 4).fill(false)
    );

    const [thursdaySchedule] = useState<boolean[]>(
        Array(hours.length * 4).fill(false)
    );

    const [fridaySchedule] = useState<boolean[]>(
      Array(hours.length * 4).fill(false)
    );

    const [saturdaySchedule] = useState<boolean[]>(
      Array(hours.length * 4).fill(false)
    );

    const [sundaySchedule] = useState<boolean[]>(
        Array(hours.length * 4).fill(false)
    );

    const masterSchedule: boolean[][] = [
        mondaySchedule,
        tuesdaySchedule,
        wednesdaySchedule,
        thursdaySchedule,
        fridaySchedule,
        saturdaySchedule,
        sundaySchedule,
    ];

    const [scheduleGrid, setScheduleGrid] = useState<boolean[][]>(masterSchedule);

    const [choosingHours, setChoosingHours] = useState<boolean>(true);

    type time = {
        hour: number,
        day: number
    };

    const [startingTime, setStartingTime] = useState<time | null>(null);
    const [endingTime, setEndingTime] = useState<time | null>(null);

    const toggleCell = (dayIndex: number, hourIndex: number):void => {
        //TODO: Rework
    };

    const getSpan = ():number =>{
        if(startingTime && endingTime){
            if(startingTime.hour > endingTime.hour)
                return startingTime.hour - endingTime.hour + 1;
            else if(startingTime.hour < endingTime.hour)
                return endingTime.hour - startingTime.hour + 1;
            else return 1;
        }
        else
            return 1;
    }

    const getStartingPoint = (): number =>{
        if(startingTime && endingTime){
            if(startingTime.hour > endingTime.hour)
                return endingTime.hour;
            else if(startingTime.hour < endingTime.hour)
                return startingTime.hour;
            else return startingTime.hour;
        }
        else
            return 1;
    }

    const chooseCell= (dayIndex: number, hourIndex: number):boolean =>{
        if(!startingTime || !endingTime)
            return false;
        if(dayIndex != startingTime.day)
            return false
        if(hourIndex != getStartingPoint())
            return false;
        return true;
    }



    return(
        <section className = {styles.mainWrapper}>
            <div className = {styles.scheduleControl}>
                <div className = {`${styles.interSection} ${styles.gridElement}`}></div>
                {hours.map((hour, index) =>(
                    <div key = {index} className = {`${styles.hourGrid} ${styles.gridElement}`}>{hour}</div>
                ))}
                {days.map((day, index) =>(
                    <div key = {index} className = {`${styles.dayGrid} ${styles.gridElement}`}>{day}</div>
                ))}
                <div className = {`${styles.interSection} ${styles.gridElement}`}></div>
                {scheduleGrid.map((day, dayIndex)=>(
                    day.map((hour, hourIndex)=>(
                         <div key={`${dayIndex}-${hourIndex}`}
                            className={hour?`${styles.gridElement} ${styles.scheduleBlock}` : `${styles.gridElement} ${styles.scheduleBlockNoAfter}`}
                            style={{"gridRow":`${hourIndex + 2} / span ${chooseCell(dayIndex, hourIndex)?getSpan(): 1}`,"gridColumn":`${dayIndex + 2}`,
                                   "borderBottom":`${(hourIndex%4 == 3)? "1px solid black":""}`
                                   }}
                            onClick={( ) => toggleCell(dayIndex, hourIndex)}
                        >
                        </div>
                    ))
                ))}
            </div>
            {/* TODO:Make this be the photo from the backend */}
            {/* TODO:Make this a full picture show off */}
            <div className={styles.animalAbout}>
                <img src = {saschaImg} className = {styles.animalPic}></img>
                <div className = {styles.animalInfo}></div>
            </div>
        </section>
    )
}

export default AnimalSchedule;
