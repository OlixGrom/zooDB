package animals.pets;

import animals.Animal;

public class Dog extends Animal {
    public Dog(String name, int age, double weight, String color) {
        super(name,age,weight,color);
    }

    public Dog(String name, int age, double weight, String color,String type) {
        super(name,age,weight,color, type);
    }

    public Dog(String name, int age, double weight, String color, String type, long id) {
        super(name,age,weight,color, type,id);
    }

    @Override
    public void say() {
        System.out.println("Гав");
    }
}
