import styles from "./animalBox.module.css"
import testPic from "../assets/sasha-sashina-YCsh4ltV9Ec-unsplash.jpg"
import { useNavigate } from "react-router-dom"

function AnimalBox({name, info, id} : {name: string, info: string, id: bigint}){
    const navigate = useNavigate();
    return <div  className = {styles.mainWrapper} onClick = {() => navigate(`/adopt/${id}`)}>
        <img src = {testPic} className = {styles.picture}></img>
        <div className = {styles.textBox}>
            <div className = {styles.name}>{name}</div>
            <div className = {styles.info}>{info} </div>
        </div>
    </div>
}

export default AnimalBox;
