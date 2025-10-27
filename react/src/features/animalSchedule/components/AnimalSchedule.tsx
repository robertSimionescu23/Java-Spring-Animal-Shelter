import styles from "./animalSchedule.module.css"
import saschaImg from "../assets/sasha.jpg"
import { useEffect, useState } from "react";

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


    const [scheduleGrid] = useState<boolean[][]>(masterSchedule);

    type TimeIndexed = {
        hour: number,
        day: number
    };


    const [endingTime, setEndingTime]    = useState<TimeIndexed| null>(null);
    const [startingTime, setStartingTime] = useState<TimeIndexed| null>(null);
    const [visitMap, setVisitMap] = useState<Map<string, number>>(new Map());

    useEffect(() => {
        if(endingTime && startingTime){
            const tempMap = new Map(visitMap);
            if(startingTime.hour < endingTime.hour)
                tempMap.set(`${startingTime.day}-${startingTime.hour}`, Math.abs(startingTime.hour - endingTime.hour) + 1);
            else
                tempMap.set(`${endingTime.day}-${endingTime.hour}`, Math.abs(startingTime.hour - endingTime.hour) + 1);
            setVisitMap(tempMap);
        }
        setStartingTime(null);
        setEndingTime(null);
    }, [endingTime]);

    useEffect(()=>{
        console.log(visitMap);
    },[visitMap])


    const handleClick = (dayIndex: number, hourIndex:number) => {
        if (startingTime == null)
            setStartingTime({day:dayIndex, hour:hourIndex});
        else{
            //Case where another column has been clicked (days do not match)
            if(startingTime.day != dayIndex){
                setStartingTime(null);
                setEndingTime(null);
            }
            else{
                let isFree: boolean = true;
                let startingPoint: number = startingTime.hour;
                let endingPoint: number;

                //Switch starting point of visit depending on the clicked time slots
                if(startingTime.hour < hourIndex)
                    endingPoint = hourIndex;
                else{
                    endingPoint = startingTime.hour;
                    startingPoint = hourIndex;
                }

                //Make sure that no 2 visits overlap
                for(let i: number = startingPoint; i <= endingPoint; i ++)
                    if(scheduleGrid[dayIndex][i] ==  true)
                        isFree = false;

                if(isFree){
                    for(let i: number = startingPoint; i <= endingPoint; i ++)
                        scheduleGrid[dayIndex][i] = true;
                    setEndingTime({day:dayIndex, hour:hourIndex});
                }
                //If they overlap, start over
                else{
                    setStartingTime(null);
                }

            }
        }
        console.log(startingTime);
        //TODO: Make it so that the first time slot pressed is highlighted

    }


    return(
        <section className = {styles.mainWrapper}>
            <div className = {styles.scheduleControl}>
                <div className = {`${styles.interSection} ${styles.gridElement}`}></div>
                {hours.map((hour, index) =>(                 //Hour Column on left
                    <div key = {index} className = {`${styles.hourGrid} ${styles.gridElement}`}>{hour}</div>
                ))}
                {days.map((day, index) =>(                   //Day Column on top
                    <div key = {index} className = {`${styles.dayGrid} ${styles.gridElement}`}>{day}</div>
                ))}
                <div className = {`${styles.interSection} ${styles.gridElement}`}></div>
                {/* Day index are from 0 to 7. Hour Indexes follow teh same principle */}
                {scheduleGrid.map((day, dayIndex)=>(
                    day.map((hour, hourIndex)=>(
                         <div key={`${dayIndex}-${hourIndex}`}
                            className={hour?`${styles.gridElement} ${styles.scheduleBlock}`:`${styles.gridElement} ${styles.scheduleBlockNoAfter}`}
                            style={{"gridRow":`${hourIndex + 2} / span ${visitMap.has(`${dayIndex}-${hourIndex}`)?visitMap.get(`${dayIndex}-${hourIndex}`) : 1}`,
                                   "gridColumn":`${dayIndex + 2}`,
                                   "borderBottom":`${(hourIndex%4 == 3)? "1px solid black":""}`,
                                   "zIndex":`${visitMap.has(`${dayIndex}-${hourIndex}`)?"2" : "1"}`
                                   }}
                            onClick={() => handleClick(dayIndex, hourIndex)}
                        >
                            {visitMap.has(`${dayIndex}-${hourIndex}`)?`Visit scheduled`:""}
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
