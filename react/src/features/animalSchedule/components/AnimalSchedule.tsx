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
        switch(time.day){
            case 0:
                timeString.day = "Monday";
                break;
            case 1:
                timeString.day = "Tuesday";
                break;
            case 2:
                timeString.day = "Wednesday";
                break;
            case 3:
                timeString.day = "Thursday";
                break;
            case 4:
                timeString.day = "Friday";
                break;
            case 5:
                timeString.day = "Saturday";
                break;
            case 6:
                timeString.day = "Sunday";
                break;
        }

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

        let hour: string;

        switch(Math.floor(time.hour / 4)){
            case 0:
                hour = "8";
                break;
            case 1:
                hour = "9";
                break;
            case 2:
                hour = "10";
                break;
            case 3:
                hour = "11";
                break;
            case 4:
                hour = "12";
                break;
            case 5:
                hour = "13";
                break;
            case 6:
                hour = "14";
                break;
            case 7:
                hour = "15";
                break;
            case 8:
                hour = "16";
                break;
            case 9:
                hour = "17";
                break;
            case 10:
                hour = "18";
                break;
            case 11:
                hour = "19";
                break;
            case 12:
                hour = "20";
                break;
            default:
                hour = "x";
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
        //TODO: Make it so that the first time slot pressed is highlighted

    }

    const handleHover = (hourIndex: number, dayIndex: number) =>{
        // console.log(startingTime?.day == dayIndex);
        console.log(startingTime?.hour === hourIndex);
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
                                {(startingTime && hourIndex === startingTime.hour && dayIndex === startingTime.day)&&
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
