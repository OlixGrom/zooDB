package helpers;

import animals.Animal;

import java.util.List;

public class PrintList {
    public void print(List<Animal> animals) {
        if (animals.size() == 0) {
            System.out.println("Список животных пуст. ");
        } else {
            System.out.println("Список животных: ");
            for (Animal animal : animals) {
                System.out.println(animal.toString());
            }
        }
    }

    public void printDB(List<Animal> animals) {
        if (animals.size() == 0) {
            System.out.println("Список животных пуст. ");
        } else {
            System.out.println("Список животных: ");
            for (Animal animal : animals) {//id type name age weight color
                System.out.println("id="+animal.getId()+
                        " type="+animal.getType()+
                        " name="+animal.getName()+
                        " age="+animal.getAge()+
                        " weight="+animal.getWeight()+
                        " color="+animal.getColor());
            }
        }
    }
}
