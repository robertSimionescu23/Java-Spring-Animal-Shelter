import axios from "axios";
import AnimalBox from "./AnimalBox"
import styles from "./animalDisplay.module.css";
import { useState, useEffect } from "react";
import type Animal from "../../../components/Animal";

function AnimalDisplay(): React.ReactElement{
    //Testing data

    interface PageResponse<T> {
        data: {
            content: T[];
            totalPages: number;
            totalElements: number;
            currentPage: number;
        };
    }


    const [currentPage, setCurrentPage] = useState<number>(0);
    const [currentAnimals, setCurrentAnimals] = useState<Animal[] | null>(null);
    const itemsPerPage:number = 10; //TODO: Set to 1 just for testing purposes, revert to a usual number later
    const [numPages, setNumPages] = useState<number>(0);

    const loadAnimals: (currPage: number) => Promise<void> = async (currPage) =>{
        const res: PageResponse<Animal>  = await axios.get(`http://localhost:8080/api/v1/animal/public/page/${currPage}/${itemsPerPage}`)

        setCurrentAnimals(res.data.content);

        if (numPages === 0)
            setNumPages(res.data.totalPages);
    }

    useEffect(() => {
        loadAnimals(currentPage)
    }, [currentPage]);

    return(
        <section className = {styles.mainWrapper}>
            <div className = {styles.pageControl}>
                <button className = {styles.pageControlButton}
                 disabled = {currentPage === 0}
                 onClick={() => setCurrentPage(currentPage - 1)}>{"<"}
                </button>
                <h3 className = {styles.pageDisplay}>{`${currentPage + 1}/${numPages}`}</h3>
                <button className = {styles.pageControlButton}
                 disabled = {currentPage === numPages - 1}
                 onClick={() => setCurrentPage(currentPage + 1)}>{">"}
                </button>
            </div>
            {currentAnimals &&<div className = {styles.animalDisplay}>
                {currentAnimals.map((animal, index) => (
                //TODO: Hide the url in prod
                <AnimalBox pic = {animal.pictureURLs?`http://localhost:8080/api/v1/animal/public/download/${animal.id}/${animal.pictureURLs[0]}` : undefined} key={index} name={animal.name} info={animal.info} id={BigInt(animal.id)}/>
            ))}</div>}
        </section>
    )
}

export default AnimalDisplay
