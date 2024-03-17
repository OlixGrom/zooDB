package factory;
import animals.*;
import animals.birds.Duck;
import animals.pets.Cat;
import animals.pets.Dog;
import data.AnimalData;
import exception.AnimalNotSupported;

import java.util.Locale;

import static data.AnimalData.*;

public class AnimalFactory {
    public Animal create(AnimalData animalData, String name, int age, double weight, String color) throws AnimalNotSupported {
        switch(animalData){
            case DUCK:{
                return new Duck( name,  age,  weight,  color, animalData.toString() );
            }
            case DOG:{
                return new Dog( name,  age,  weight,  color, animalData.toString());
            }
            case CAT:{
                return new Cat( name,  age,  weight,  color, animalData.toString());
            }
        }
        throw new AnimalNotSupported(animalData);
    }

    public Animal create(AnimalData animalData, String name, int age, double weight, String color, long id) throws AnimalNotSupported{
        switch(animalData){
            case DUCK:{
                return new Duck( name,  age,  weight,  color, animalData.toString(),id );
            }
            case DOG:{
                return new Dog( name,  age,  weight,  color, animalData.toString(), id);
            }
            case CAT:{
                return new Cat( name,  age,  weight,  color, animalData.toString(),id);
            }
        }
        throw new AnimalNotSupported(animalData);
    }
}
