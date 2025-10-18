import AnimalBox from "./AnimalBox"
import styles from "./animalDisplay.module.css";
import { useState } from "react";

function AnimalDisplay(){
    //Testing data

    type Animal = {
        name: string;
         info: string;
    };

    const animals: Animal[] = Array.from({ length: 53 }, (_, i) => ({
        name: `Sascha ${i + 1}`,
        info: "2 y.o., female",
    }));
    const [currentPage, setCurrentPage] = useState<number>(1);
    const itemsPerPage:number = 10;
    const numPages:number = Math.ceil(animals.length/itemsPerPage);

    const startIndex:number = (currentPage - 1)*itemsPerPage;
    const displayedAnimals:Animal[] = animals.slice(startIndex, startIndex + itemsPerPage);

    return(
        <section className = {styles.mainWrapper}>
            <div className = {styles.pageControl}>
                <button className = {styles.pageControlButton}
                 disabled = {currentPage === 1}
                 onClick={() => setCurrentPage(currentPage - 1)}>{"<"}
                </button>
                <h3 className = {styles.pageDisplay}>{`${currentPage}/${numPages}`}</h3>
                <button className = {styles.pageControlButton}
                 disabled = {currentPage === numPages}
                 onClick={() => setCurrentPage(currentPage + 1)}>{">"}
                </button>
            </div>
            <div className = {styles.animalDisplay}>
                {displayedAnimals.map((animal, index) => (
                <AnimalBox key={index} name={animal.name} info={animal.info} id={BigInt(index)}/>
            ))}</div>
        </section>
    )
}

export default AnimalDisplay
