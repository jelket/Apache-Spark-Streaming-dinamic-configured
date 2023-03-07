package org.example.transformations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.*;

public class Transf implements MapFunction<String, String>{

    List<String> transformValue;

    //public Transf(List<Integer> transformValue) { this.transformValue = transformValue; }

    public String call(String word) throws Exception {
        //Парсинг
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String > map = mapper.readValue(word, Map.class);

        //Нормализация номера телефона
        map.put("phone", map.get("phone").replaceAll("[^0-9]",  ""));
        String[] header = transformValue.toArray(new String[0]);

        //Удаление ненужных полей
        map.keySet().retainAll(transformValue);

        //Запись в csv формат
        if (Objects.equals(transformValue.get(0), "csv")){
            ICsvMapWriter mapWriter = null;
            try {
                mapWriter = new CsvMapWriter(new FileWriter("/home/magic/IdeaProjects/untitled/src/main/java/org/example/VotTakVot.csv", true),
                        CsvPreference.STANDARD_PREFERENCE);

                mapWriter.write(map, header);

            }
            finally {
                if( mapWriter != null ) {
                    mapWriter.close();
                }
            }
        }

        return mapper.writeValueAsString(map);
    }

    //Получение массива строчек с названиями полей
    public void setTransformValue(List<String> transformValue) {
        this.transformValue = transformValue;
    }

}