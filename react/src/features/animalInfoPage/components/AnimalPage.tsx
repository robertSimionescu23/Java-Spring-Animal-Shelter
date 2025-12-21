import { useEffect, useState } from "react";
import styles from "./animalPage.module.css"
import type Animal from "../../../components/Animal";
import { Link, useLocation, useParams } from "react-router";
import axios from "axios";

interface Response<T> {
    data: T
}

type Params = {
  id: string;
};


function AnimalPageContainer(): React.ReactElement {
    const [pictureUrls, setPictureUrls] = useState<string[] | null>(null);
    const [animalInfo, setAnimalInfo ]  = useState<Animal | null> (null);
    const [focusedPhotoIndex, setFocusedPhotoIndex] = useState<number | null> (null);

    const params: Params = useParams<Params>() as Params;

    const loadPictures: (id: string) => Promise<void> = async (id) =>{
        const res: Response<Animal>  = await axios.get(`http://localhost:8080/api/v1/animal/public/${id}`)
        if(res.data)
            setAnimalInfo(res.data);
        if(res.data.pictureURLs)
            setPictureUrls(res.data.pictureURLs.map(url => `http://localhost:8080/api/v1/animal/public/download/${id}/`+ url));

    }


    useEffect(() => {
        loadPictures(params.id)
    },[0]);

  return (
    <section className={styles.infoContainer}>
        <div className = {styles.info}>
            <h1 className = {styles.name}>{animalInfo?.name}, age {animalInfo?.age?animalInfo.age:"unknown"}</h1>
            <p className = {styles.desc}>{animalInfo?.description}</p>
        </div>
        <div className = {styles.heroAndScheduleContainer}>
            { pictureUrls && <img src = {pictureUrls[0]} alt = "No picture provided" className = {styles.animalHeroPic}></img>}
            <Link className={styles.scheduleLink} to = {`${useLocation().pathname + "/schedule"}`}><b>Schedule a visit!</b></Link>
        </div>
        <div className={styles.otherPics}>
            {pictureUrls && pictureUrls.length > 2 && pictureUrls.map((url, index) => <img src = {url} alt = "No picture provided" className = {styles.animalRegularPic} onClick={()=>setFocusedPhotoIndex(index)}></img>)}
        </div>
        {focusedPhotoIndex != null && <div className={styles.focusedPhotoBackground} onClick={()=>setFocusedPhotoIndex(null)}>
        </div>}
        {pictureUrls && focusedPhotoIndex != null &&
            <div className = {styles.focusedPhotoContainer}>
                <img className = {styles.focusedPhoto} src = {pictureUrls[focusedPhotoIndex]} alt = "No picture provided"></img>
                <div className={styles.selectButtons}>
                    <button disabled={focusedPhotoIndex==1} onClick={()=>setFocusedPhotoIndex(focusedPhotoIndex-1)}>{'<'}</button>
                    <button disabled={focusedPhotoIndex==pictureUrls.length - 1}onClick={()=>setFocusedPhotoIndex(focusedPhotoIndex+1)}>{'>'}</button>
                </div>
            </div>
        }
    </section>

  );
}

export default AnimalPageContainer;
