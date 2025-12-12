import styles from "./animalBox.module.css"
// import testPic from "../assets/sasha-sashina-YCsh4ltV9Ec-unsplash.jpg"
import { useNavigate, type NavigateFunction  } from "react-router-dom"

function AnimalBox({pic, name, info, id} : {pic: string | undefined, name: string, info: string, id: bigint}): React.ReactElement{
    const navigate: NavigateFunction = useNavigate();
    return <div  className = {styles.mainWrapper} onClick = {() => navigate(`/adopt/${id}`)}>
        <img src = {pic} alt="No picture" className = {styles.picture}></img>
        <div className = {styles.textBox}>
            <div className = {styles.name}>{name}</div>
            <div className = {styles.info}>{info} </div>
        </div>
    </div>
}

export default AnimalBox;
