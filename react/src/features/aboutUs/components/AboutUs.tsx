import styles from "./aboutUs.module.css";
import spring from "../assets/spring.png"

function AboutUs({ref} :{ref: React.RefObject<HTMLHeadingElement | null>}) {

    return <section className={styles.aboutUs} id="aboutUs">
        <h1 ref = {ref}>About us</h1>
        <span className={styles.divider}></span>
        <h2>This is not a real animal shelter!</h2>
        <div className = {styles.contentWrapper}>
            <img className = {`${styles.image}`} src={spring}></img>
            <p>Welcome to Spring Animal Shelter, a fictional animal shelter created as part of a Spring Boot portfolio project. This project showcases backend development skills such as RESTful API design, database integration, and CRUD operations — all centered around managing animal adoption data in a realistic,
                 compassionate setting. While the shelter isn’t real, the technology and functionality behind it are fully implemented.</p>
        </div>
    </section>
}

export default AboutUs;
