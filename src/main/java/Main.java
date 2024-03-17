import animals.Animal;
import data.AnimalData;
import data.InputMenu;
import exception.AnimalNotSupported;
import factory.AnimalFactory;
import helpers.CheckEnum;
import helpers.DisplayEnum;
import helpers.EnterData;
import helpers.PrintList;
import tables.AnimalTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    static private List<Animal> animals = new ArrayList<>();
    public static AnimalTable animalTable = new AnimalTable();

    public static void main(String[] args) throws AnimalNotSupported{
        Scanner scanner = new Scanner(System.in);
        CheckEnum checkEnum = new CheckEnum();
        DisplayEnum displayEnum = new DisplayEnum();
        PrintList printList = new PrintList();
        while (true) {
            System.out.print(String.format("Введите команду %s: ", displayEnum.displayListEnum(InputMenu.class)));
            String answerOperation = scanner.nextLine();
            answerOperation = answerOperation.toUpperCase(Locale.ROOT).trim();
            if (!checkEnum.checkValueInEnum(InputMenu.class, answerOperation.toUpperCase(Locale.ROOT))) {//проверили команду
                System.out.println(String.format("Команда \"%s\" не верна. Повторите еще раз", answerOperation));
                continue;
            }

            switch (InputMenu.valueOf(answerOperation)) {
                case ADD:
                    addList().
                            say();
                    break;
                case LIST:
                    animals= animalTable.read();
                    printList.printDB(animals);
                    break;
                case FILTR:
                    animals=printWithFiltr();
                    printList.printDB(animals);
                    break;
                case EDIT:
                    update();
                    break;
                case EXIT:
                    System.out.println("До свидания!");
                    System.exit(0);
            }
        }
    }

    private static void update() throws AnimalNotSupported {
        String name = "", color = "", tmpStr,typeAnimal, idRecord;
        int age = 0;

        double weight = 0;
        EnterData enterData = new EnterData(new Scanner(System.in));
        idRecord = enterData.enterId();
        typeAnimal = enterData.enterType();
        AnimalData animalData = AnimalData.valueOf(typeAnimal);
        name = enterData.enterName();
        age = Integer.parseInt(enterData.enterAge());
        weight = Float.parseFloat(enterData.enterWeight());
        color = enterData.enterColor();
        //------------------------------------

        Animal animalObject = new AnimalFactory().create(animalData, name, age, weight, color,Long.parseLong(idRecord));
        animalTable.update(idRecord,animalObject);
        animals.add(animalObject);
    }

    private static List<Animal> printWithFiltr() throws AnimalNotSupported {
        EnterData enterData = new EnterData(new Scanner(System.in));
        String typeAnimal;
        typeAnimal = enterData.enterType();
        AnimalData animalData = AnimalData.valueOf(typeAnimal);
        return animalTable.read(animalData);
    }
    private static Animal addList() throws AnimalNotSupported {

        String name = "", color = "", tmpStr, typeAnimal;
        int age = 0;
        double weight = 0;
        EnterData enterData = new EnterData(new Scanner(System.in));

        typeAnimal = enterData.enterType();
        AnimalData animalData = AnimalData.valueOf(typeAnimal);


        name = enterData.enterName();
        age = Integer.parseInt(enterData.enterAge());
        weight = Float.parseFloat(enterData.enterWeight());
        color = enterData.enterColor();
        //------------------------------------

        Animal animalObject = new AnimalFactory().create(animalData, name, age, weight, color);
        animalTable.write(animalObject);
        animals.add(animalObject);
        return animalObject;
    }
}