package helpers;

import data.AnimalData;
import data.ColorData;

import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class EnterData {
    private String tmpStr;
    private Scanner scanner;

    private CheckEnum checkEnum = new CheckEnum();
    private DisplayEnum displayEnum = new DisplayEnum();

    private static final Pattern PATTERN_STR = Pattern.compile("^[a-zA-Zа-яА-Я]+$");
    private static final Pattern PATTERN_INT = Pattern.compile("^[0-9]\\d*$");
    private static final Pattern PATTERN_FLOAT = Pattern.compile("^(?!0*(\\.0+)?$)([1-9]\\d*|0)(\\.\\d+)?$");


    public EnterData(Scanner scanner) {
        this.scanner = scanner;
        this.checkEnum = new CheckEnum();
        this.displayEnum = new DisplayEnum();
    }


    public String enterType(){
        while (true) {
            System.out.print(String.format("Выберите животное: %s ", displayEnum.displayListEnum(AnimalData.class)));
            tmpStr = scanner.next().trim();
            tmpStr = tmpStr.toUpperCase(Locale.ROOT).trim();
            if (checkEnum.checkValueInEnum(AnimalData.class, tmpStr.toUpperCase(Locale.ROOT))) {//проверили животное
                break;
            }
            System.out.println(String.format("Животное \"%s\" выбрано не верно. Повторите еще раз ", tmpStr));
        }
        return tmpStr;
    }
    public String enterId(){
        while (true) {
            System.out.print("Введите Id записи, которую хотите изменить: ");
            tmpStr = scanner.next().trim();
            if (Validators.isStringRegular(tmpStr,PATTERN_INT) && Long.parseLong(tmpStr) > 0) {
                break;
            }
            System.out.println("Вы ввели некорректный Id записи");
        }
        return tmpStr;
    }

    public String enterName(){
        while (true) {
            System.out.print("Введите кличку животного: ");
            tmpStr = scanner.next().trim();
            if (Validators.isStringRegular(tmpStr, PATTERN_STR)) {
                break;
            }
            System.out.println("Вы ввели некорректную кличку животного");
        }
        return tmpStr;
    }

    public String enterWeight(){
        while (true) {
            System.out.print("Введите вес: ");
            tmpStr = scanner.next().trim();
            if (Validators.isStringRegular(tmpStr,PATTERN_FLOAT) && Float.parseFloat(tmpStr) > 0) {
                break;
            }
            System.out.println("Вы ввели некорректный вес животного");
        }
        return tmpStr;
    }

    public String enterAge(){
        while (true) {
            System.out.print("Введите возраст: ");
            tmpStr = scanner.next().trim();
            if (Validators.isStringRegular(tmpStr,PATTERN_INT) && Integer.parseInt(tmpStr) > 0) {
                break;
            }
            System.out.println("Вы ввели некорректный возраст животного");
        }
        return tmpStr;
    }

    public String enterColor(){
        while (true) {
            System.out.print(String.format("Выберите цвет животного: %s ", displayEnum.displayListEnum(ColorData.class)));
            tmpStr = scanner.next().toUpperCase(Locale.ROOT).trim();
            if (checkEnum.checkValueInEnum(ColorData.class, tmpStr)) {//проверили животное
                break;
            }
            System.out.println(String.format("Вы ввели некорректный цвет - %s. Повторите еще раз", tmpStr));
        }
        return tmpStr;
    }

}
