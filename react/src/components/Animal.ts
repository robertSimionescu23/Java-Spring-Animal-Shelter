    export default interface Animal {
        id:number;
        name: string;
        age: number | null;
        info: string;
        species: string;
        adoptionStatus: string;
        description: string | null;
        pictureURLs: string[] | null;
    };
