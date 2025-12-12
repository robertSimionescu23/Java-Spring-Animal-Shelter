import AnimalSchedule from "../features/animalSchedule/components/AnimalSchedule";
import Navbar from "../features/navbar/components/Navbar";
import QuickBar from "../features/quickbar/components/Quickbar";

function AnimalSchedulePage():React.ReactElement{
    return<>
        <QuickBar></QuickBar>
        <Navbar></Navbar>
        <AnimalSchedule></AnimalSchedule>
    </>
}

export default AnimalSchedulePage;
