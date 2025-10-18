import AnimalDisplay from "../features/animalDisplay/components/AnimalDisplay";
import Navbar from "../features/navbar/components/Navbar";
import QuickBar from "../features/quickbar/components/Quickbar";

function Adopt(){

    return(
        <>
            <QuickBar></QuickBar>
            <Navbar></Navbar>
            <AnimalDisplay></AnimalDisplay>
        </>
    )
}

export default Adopt;
