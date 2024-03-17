package exception;

import data.AnimalData;

public class AnimalNotSupported extends Exception {
    public AnimalNotSupported(AnimalData animalData){
        super( String.format("Animal %s not supported",animalData.name()));
    }
}
