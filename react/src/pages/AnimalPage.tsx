import Navbar from "../features/navbar/components/Navbar";
import QuickBar from "../features/quickbar/components/Quickbar";
import AnimalPageContainer from "../features/animalInfoPage/components/AnimalPage";

function AnimalPage():React.ReactElement{
    return<>
        <QuickBar></QuickBar>
        <Navbar></Navbar>
        <AnimalPageContainer></AnimalPageContainer>
    </>
}

export default AnimalPage;
