import React, { useEffect, useState } from "react";
import styles from "./animalSchedule.module.css"
import AnimalScheduleGrid from "./AnimalScheduleGrid";
import type Animal from "../../../components/Animal";
import axios from "axios";
import { useParams } from "react-router-dom";

interface Response<T> {
    data: T
}

type Params = {
  id: string;
};

function AnimalSchedule(): React.ReactElement {
    const params: Params = useParams<Params>() as Params;

    const [pictureUrl, setPictureUrl]           = useState<string | undefined>();
    const [description, setDescription  ]           = useState<string | undefined>();

    const getInfo: (id: string) => Promise<void> = async (id) =>{
        const res: Response<Animal>  = await axios.get(`http://localhost:8080/api/v1/animal/public/${id}`)
        if(res.data.pictureURLs)
            setPictureUrl(`http://localhost:8080/api/v1/animal/public/download/${id}/`+ res.data.pictureURLs[0]);
        if(res.data.description)
            setDescription(res.data.description)
    };


    useEffect(()=>{
        getInfo(params.id);
    },[0]);


    return(
        <section className = {styles.mainWrapper}>
            <AnimalScheduleGrid/>
            {/* TODO:Make this be the photo from the backend */}
            {/* TODO: Make choosing the calendar date possibile*/}
            <div className={styles.animalAbout}>
                <img src = {pictureUrl} alt = "No picture provided" className = {styles.animalPic}></img>
                { description && <div className={styles.description}>{description}</div>}
            </div>
        </section>
    )
}

export default AnimalSchedule;
