package helpers;

import data.AnimalData;
import data.InputMenu;

public class DisplayEnum {
    public <T extends Enum<T>> String displayListEnum(Class<T> enumType){
        T[] tmpEnum = enumType.getEnumConstants();

        String[] list = new String[tmpEnum.length];
        for (int i = 0; i < tmpEnum.length; i++) {
            list[i] = tmpEnum[i].name().toUpperCase();
        }
        return String.join("/", list);
    }
}