import { useEffect, useState } from "react";
import styles from "./animalPage.module.css"
import type Animal from "../../../components/Animal";
import { Link, useLocation, useParams } from "react-router";
import axios from "axios";
import ButtonWithIcon from "../../../components/ButtonWithIcon";

interface Response<T> {
    data: T
}

type Params = {
  id: string;
};

function AnimalPageContainer(): React.ReactElement {
    const params: Params = useParams<Params>() as Params;

    const loadPictures: (id: string) => Promise<void> = async (id) =>{
        const res: Response<Animal>  = await axios.get(`http://localhost:8080/api/v1/animal/public/${id}`)
        console.log(res.data)
        if(res.data)
            setAnimalInfo(res.data);
        if(res.data.pictureURLs)
            setPictureUrls(res.data.pictureURLs.map(url => `http://localhost:8080/api/v1/animal/public/download/${id}/`+ url));

    }

    const [pictureUrls, setPictureUrls] = useState<string[] | null>(null);
    const [animalInfo, setAnimalInfo ]  = useState<Animal | null> (null);

    useEffect(() => {
        loadPictures(params.id)
    });

  return (
    <section className={styles.infoContainer}>
        <div className = {styles.info}>
            <h1 className = {styles.name}>{animalInfo?.name}, age {animalInfo?.age?animalInfo.age:"unknown"}</h1>
            <p>{animalInfo?.description}</p>
        </div>
        <div className = {styles.heroAndScheduleContainer}>
            { pictureUrls && <img src = {pictureUrls[0]} alt = "No picture provided" className = {styles.animalHeroPic}></img>}
            <Link className={styles.scheduleLink} to = {`${useLocation().pathname + "/schedule"}`}><b>Schedule a visit!</b></Link>
        </div>
    </section>
  );
}

export default AnimalPageContainer;
