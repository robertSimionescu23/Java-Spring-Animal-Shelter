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

    type TimeString = {
        hour: string,
        day: string
    }


    const [endingTime, setEndingTime]    = useState<TimeIndexed| null>(null);
    const [startingTime, setStartingTime] = useState<TimeIndexed| null>(null);
    const [visitMap, setVisitMap] = useState<Map<string, number>>(new Map());
    const [hoverLength, setHoverLength] = useState<number>(1);

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

    const convertTimeIndexToTimeString = (time: TimeIndexed): TimeString =>{
        const timeString: TimeString = {hour: "", day: ""};
        timeString.day = days[time.day];

        let hour:string;

        if(Math.floor(time.hour / 4) <= 12)
            hour = hours[Math.floor(time.hour / 4)].split(":")[0]; //Each hour has 4 indexes, for the 15 minute intervals available
        else
            hour = '21'; //This hour is theoreticly out of schedule. the visit cand end at 21:00 but not start.

        console.log(Math.floor(time.hour / 4))
        let minutes: string;
        switch(time.hour  % 4){
            case 1:
                minutes = ":15";
                break;
            case 2:
                minutes = ":30";
                break;
            case 3:
                minutes = ":45";
                break;
            case 0:
                minutes = ":00";
                break;
            default:
                minutes = ":x";
                break;
        }


        timeString.hour = hour + minutes;
        return timeString;
    };

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
                const startingPoint: number = startingTime.hour;
                let endingPoint: number;

                //Disable visits with end times before the starting time
                if(startingTime.hour < hourIndex)
                    endingPoint = hourIndex;
                else
                    endingPoint = startingPoint;

                //Make sure that no 2 visits overlap
                for(let i: number = startingPoint; i <= endingPoint; i ++)
                    if(scheduleGrid[dayIndex][i] ==  true)
                        isFree = false;

                //If the time slot is free for a visit, lock it
                if(isFree){
                    for(let i: number = startingPoint; i <= endingPoint; i ++)
                        scheduleGrid[dayIndex][i] = true;
                    setEndingTime({day:dayIndex, hour:endingPoint});
                }
                //If they overlap, start over
                else{
                    setStartingTime(null);
                }

            }
        }
        //TODO: Make it so that the first time slot pressed is highlighted

    }

    const handleHover = (hourIndex: number, dayIndex: number) =>{
        if(startingTime?.day === dayIndex){
            if(hourIndex - startingTime.hour> -1)
                setHoverLength(hourIndex - startingTime.hour + 1);

        }else
            setHoverLength(1);

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
                {/* Day index are from 0 to 7. Hour Indexes are 4 per hour */}
                {scheduleGrid.map((day, dayIndex)=>(
                    day.map((_, hourIndex)=>(
                         <div key={`${dayIndex}-${hourIndex}`}
                            className={`${styles.scheduleGrid} ${styles.unscheduled} `}
                            style={{"gridRow":`${hourIndex + 2} / span 1`,
                                   "gridColumn":`${dayIndex + 2}`
                                   }}
                            onClick={() => handleClick(dayIndex, hourIndex)}
                            onMouseEnter={() => handleHover(hourIndex, dayIndex)}
                        >
                                {visitMap.has(`${dayIndex}-${hourIndex}`) &&
                                   <div className={`${styles.visitBlock} `}
                                    style ={{height: `calc(${(visitMap.get(`${dayIndex}-${hourIndex}`) ?? 0)} * 100%)`}}>
                                    {(() => {
                                        const duration = visitMap.get(`${dayIndex}-${hourIndex}`) ?? 0;
                                        const timeStringStart = convertTimeIndexToTimeString({day: dayIndex, hour: hourIndex});
                                        const stopHourIndex: number = hourIndex + duration;
                                        const timeStringEnd = convertTimeIndexToTimeString({day: dayIndex, hour: stopHourIndex});
                                        return `${timeStringStart.hour} - ${timeStringEnd.hour}`;
                                    })()}
                                   </div>
                                }

                                {/* Initial select */}
                                {(startingTime && hourIndex === startingTime.hour && dayIndex === startingTime.day && !visitMap.has(`${dayIndex}-${hourIndex}`))&&
                                <>
                                    <div className={`${styles.selectedSlot}`}
                                    style = {{height : `calc(${hoverLength} * 100%)`}}>
                                        {`${convertTimeIndexToTimeString({day: dayIndex, hour: hourIndex}).hour} - ${convertTimeIndexToTimeString({day: dayIndex, hour: hourIndex + hoverLength}).hour}`}
                                    </div>
                                    <div className={`${styles.selectTextBubble}`}>
                                        Press the time slot you would like this visit to end at. This can be changed later.
                                    </div>
                                </>


                                }
                        </div>
                    ))
                ))}

            </div>
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
