import {useState, useEffect} from "react";
import styles from "./animalSchedule.module.css"
import axios from "axios";
import type Animal from "../../../components/Animal";

type TimeIndexed = {
    hour: number,
    day: number
};

type TimeString = {
    hour: string,
    day: string
}

type ScheduleEntry = {
    startTime: string,
    endTime  : string,
    date     : string,
    animalId : string,
    duration : number
}


function AnimalScheduleGrid({ id }: { id: string }): React.ReactElement {

    const hours:string[]                = ["8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00"];
    const days:string[]                 = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"];
    const currDate:  Date               = new Date();
    const masterSchedule: boolean[][]   = [];
    const [scheduleGrid, setScheduleGrid] = useState<boolean[][]>(masterSchedule);

    const getWeekDates: (date: Date) => Date[] = (date) => {
        const temp: Date[] = [];

        for (let i: number = 0; i < 7; i++) {
            temp[i] = new Date(date); // Copy mondayDate, not new Date()
            temp[i].setDate(date.getDate() + i);
        }
        return temp;
    };


    const [endingTime, setEndingTime]               = useState<TimeIndexed| null>(null);
    const [startingTime, setStartingTime]           = useState<TimeIndexed| null>(null);
    const [visitMap, setVisitMap]                   = useState<Map<string, number>>(new Map());
    const [hoverLength, setHoverLength]             = useState<number>(1);
    const [tentativeStart, setTentativeStart]       = useState<TimeIndexed | null>(null);
    const [isActive, setIsActive]                   = useState<boolean>(false);
    //Get first monday of the week displayed
    const [startOfShownWeek, setStartOfShownWeek] = useState<Date>(() => {
        const monday: Date = new Date();
        monday.setDate(new Date().getDate() - new Date().getDay() + 1);
        return monday;
    });
    const [weekDates, setWeekDates] = useState<Date[]>(getWeekDates(new Date()));




    const convertTimeIndexToTimeString: (time: TimeIndexed) => TimeString = (time) => {
        const timeString: TimeString = {hour: "", day: ""};
        timeString.day = days[time.day];

        let hour:string;

        if(Math.floor(time.hour / 4) <= 12)
            hour = hours[Math.floor(time.hour / 4)].split(":")[0]; //Each hour has 4 indexes, for the 15 minute intervals available
        else{
            hour = (() : string => {
                const lastHour: number = parseInt(hours[hours.length - 1]);
                return ((lastHour + 1) % 24).toString();
            })(); //Get a hour that is outside of schedule, for visiual purpose only
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


        timeString.hour = hour + minutes;
        return timeString;
    };


    //For each day in the schedule, create a "mini-schedule" that will be added to the week schedule
    for(let i:number = 0; i < days.length; i++){
        const daySchedule: boolean[] = Array(hours.length * 4).fill(false);
        masterSchedule[i] = daySchedule;
    }

    const handleClick: (dayIndex: number, hourIndex:number) => void = (dayIndex, hourIndex) => {
        if (startingTime == null)
            setStartingTime({day:dayIndex, hour:hourIndex});
        else{
            //Started on a booked slot
            if(scheduleGrid[dayIndex][hourIndex])
                setStartingTime(null);
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
                 let lastFreeSlot: number = -1;

                for(let i: number = startingPoint; i <= endingPoint; i ++)
                    if(scheduleGrid[dayIndex][i] == true){
                        lastFreeSlot = i - 1;
                        isFree = false;
                        break
                }


                //If the time slot is free for a visit, lock it
                if(isFree){
                    //Change only the day in question. Mark the hours as true (slot taken) if they are between the starting point and endpoint
                    setScheduleGrid(prev =>{
                        return prev.map((day, dayIndexMap) =>{
                            if(dayIndex != dayIndexMap)
                                return day;
                            else
                                return day.map((hour, hourIndexMap) =>
                                    hourIndexMap >= startingPoint && hourIndexMap <= endingPoint ? true : hour
                                )
                        })
                    })
                    setEndingTime({day:dayIndex, hour:endingPoint});
                }
                //If there is another visit in the way, stop at the first possible
                else{
                    endingPoint = lastFreeSlot;

                    setScheduleGrid(prev =>{
                        return prev.map((day, dayIndexMap) =>{
                            if(dayIndex != dayIndexMap)
                                return day;
                            else
                                return day.map((hour, hourIndexMap) =>
                                    hourIndexMap >= startingPoint && hourIndexMap <= endingPoint ? true : hour
                                )
                        })
                    })

                    setEndingTime({day: dayIndex, hour: endingPoint});
                }

            }
        }
    }

    const handleHover: (hourIndex: number, dayIndex: number) => void = (hourIndex, dayIndex) =>{
        if(startingTime == null && !scheduleGrid[dayIndex][hourIndex] && isActive){
            setTentativeStart({day: dayIndex, hour: hourIndex});
        }
        else if(startingTime?.day === dayIndex){
            if(hourIndex - startingTime.hour> -1){
                const slice: boolean[] = scheduleGrid[dayIndex].slice(startingTime.hour, hourIndex + 1);
                let firstFalsePos: number = -1;
                //Find if all the slots involved in the visit are free. If not, limit gover to the portion that is.
                const isFree: boolean = slice.every((value, index) => {
                    if(value === true)
                    {
                        firstFalsePos = index;
                        return false;
                    }
                    return true;
                });

                if(isFree)
                    setHoverLength(hourIndex - startingTime.hour + 1);
                else{
                    setHoverLength(firstFalsePos);
                }
            }
            else setHoverLength(1);
        }else
            setHoverLength(1);
    }

    useEffect(() => {
        if(endingTime && startingTime){
            const tempMap: Map<string, number> = new Map(visitMap);
            if(startingTime.hour < endingTime.hour)
                tempMap.set(`${startingTime.day}-${startingTime.hour}`, Math.abs(startingTime.hour - endingTime.hour) + 1);
            else
                tempMap.set(`${endingTime.day}-${endingTime.hour}`, Math.abs(startingTime.hour - endingTime.hour) + 1);
            setVisitMap(tempMap);
        }
        if(startingTime && endingTime){
            //TODO: Add the isCancelled and VisitorName
            const visit: ScheduleEntry = {
                startTime: convertTimeIndexToTimeString(startingTime).hour,
                // As a interval is 15 mins, by leaving "hour:endingTime.hour" unchaged, the head of the 15 minute interval will display
                endTime: convertTimeIndexToTimeString({hour:endingTime.hour + 1, day:endingTime.day}).hour,
                date: weekDates[endingTime.day].toISOString().split("T")[0], //Get a date in format year-month-day
                animalId: id,
                duration: Math.abs(endingTime.hour + 1 - startingTime.hour) * 15
            }
            //TODO: Send this to back end


        console.log(visit)
        setStartingTime(null);
        setEndingTime(null);
        }
    }, [endingTime]);

    useEffect(() =>{
        setWeekDates(getWeekDates(startOfShownWeek));
    }, [startOfShownWeek])


    return(
    <section className = {styles.scheduleGridContainer}>
        <div className={styles.instructionText}>Please select the starting time of your intended visit and then, it's ending time.</div>
        <div>
            <button onClick = {() =>{
                    const tempDate: Date = new Date(startOfShownWeek); tempDate.setDate(tempDate.getDate() + 7); setStartOfShownWeek(tempDate); console.log(startOfShownWeek)
                }}>
                    Next Week
            </button >
            {(startOfShownWeek > currDate) && <button onClick = {() =>{
                    const tempDate: Date = new Date(startOfShownWeek); tempDate.setDate(tempDate.getDate() - 7); setStartOfShownWeek(tempDate); console.log(startOfShownWeek)
                }}>
                Previous Week
            </button>}
        </div>
        <div className = {styles.scheduleControl} onMouseEnter={()=>setIsActive(true)} onMouseLeave={()=>{setIsActive(false); setTentativeStart(null)}}>
                <div className = {`${styles.interSection} ${styles.gridElement}`}></div>
                {hours.map((hour, index) =>(                 //Hour Column on left
                    <div key = {index} className = {`${styles.hourGrid} ${styles.gridElement}`}>{hour}</div>
                ))}
                {days.map((day, index) =>(                   //Day Column on top
                    <div key = {index} className = {`${styles.dayGrid} ${styles.gridElement} ${styles.hourAndDate}`}>
                        <div>{day}</div>
                        <div>{weekDates[index].toLocaleDateString()}</div>
                    </div>
                ))}
                <div className = {`${styles.interSection} ${styles.gridElement}`}></div>
                {/* Day index are from 0 to 7. Hour Indexes are 4 per hour */}
                {scheduleGrid.map((day, dayIndex)=>(
                    day.map((_, hourIndex)=>(
                         <div key={`${dayIndex}-${hourIndex}`}
                            className={`${styles.scheduleGrid} ${styles.unscheduled} `}
                            style={{"gridRow":`${hourIndex + 2} / span 1`,
                                   "gridColumn":`${dayIndex + 2}`,
                                   "backgroundColor" : `${scheduleGrid[dayIndex][hourIndex]?"red":""}`
                                   }}
                            onClick={() => handleClick(dayIndex, hourIndex)}
                            onMouseEnter={() => handleHover(hourIndex, dayIndex)}
                        >
                                {/* Use div as border between hours as an actual css border prevents proper alignment    */}
                                {((hourIndex + 1) % 4) == 0 && <div className = {styles.rowBorder}></div>}
                                {visitMap.has(`${dayIndex}-${hourIndex}`) &&
                                   <div className={`${styles.visitBlock} `}
                                    style ={{height: `calc(${(visitMap.get(`${dayIndex}-${hourIndex}`) ?? 0)} * 100%)`}}>
                                    {((): string => {
                                        const duration: number = visitMap.get(`${dayIndex}-${hourIndex}`) ?? 0;
                                        const timeStringStart:TimeString = convertTimeIndexToTimeString({day: dayIndex, hour: hourIndex});
                                        const stopHourIndex: number = hourIndex + duration;
                                        const timeStringEnd:TimeString = convertTimeIndexToTimeString({day: dayIndex, hour: stopHourIndex});
                                        return `${timeStringStart.hour} - ${timeStringEnd.hour}`;
                                    })()}
                                   </div>
                                }
                                {/* Select time slot before locking */}
                                {(startingTime && hourIndex === startingTime.hour && dayIndex === startingTime.day && !visitMap.has(`${dayIndex}-${hourIndex}`))&&
                                    <div className={`${styles.selectedSlot}`}
                                    style = {{height : `calc(${hoverLength} * 100%)`}}>
                                        {`${convertTimeIndexToTimeString({day: dayIndex, hour: hourIndex}).hour} - ${convertTimeIndexToTimeString({day: dayIndex, hour: hourIndex + hoverLength}).hour}`}
                                    </div>
                                }

                                {/* Initial select */}
                                {startingTime == null && tentativeStart?.hour == hourIndex && tentativeStart?.day == dayIndex &&
                                    <div className={`${styles.selectedSlot}`}>
                                        {`${convertTimeIndexToTimeString({day: dayIndex, hour: hourIndex}).hour}`}
                                    </div>
                                }
                        </div>
                    ))
                ))}

            </div>
    </section>
    )
}

export default AnimalScheduleGrid
