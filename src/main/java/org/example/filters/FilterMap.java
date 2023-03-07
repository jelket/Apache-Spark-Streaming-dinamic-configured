package org.example.filters;

import java.util.HashMap;
import java.util.Objects;

public class FilterMap {
    static HashMap map;

    public FilterMap(HashMap map) {
        this.map = map;
    }

    //Примерные id фильтров
    public static boolean call(Integer s) {
        if (s == 1){
            return Objects.equals(map.get("country").toString(), "US");
        } else if (s == 2){
            return Objects.equals(map.get("country").toString(), "UK");
        } else if (s == 3){
            return Objects.equals(map.get("country").toString(), "RUS");
        } else if (s == 4){
            return Objects.equals(map.get("name").toString(), "Ben");
        } else if (s == 5){
            return Objects.equals(map.get("name").toString(), "John");
        } else if (s == 6){
            return Objects.equals(map.get("name").toString(), "Marc");
        } else if (s == 7){
            return Objects.equals(map.get("name").toString(), "Bob");
        } else if (s == 8){
            return Integer.parseInt((map.get("age")).toString()) < 20;
        } else if (s == 9){
            return Integer.parseInt((map.get("age")).toString()) < 30;
        } else if (s == 10){
            return Integer.parseInt((map.get("age")).toString()) < 40;
        } else if (s == 11){
            return Integer.parseInt((map.get("age")).toString()) < 50;
        }
        return true;
    }

    //Получаем HashMap который будем фильтровать
    public void setFilter(HashMap map) {
        this.map = map;
    }
}
