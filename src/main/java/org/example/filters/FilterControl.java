package org.example.filters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.spark.api.java.function.FilterFunction;

import java.util.HashMap;
import java.util.List;

public class FilterControl implements FilterFunction<String> {
    List<Integer> filterValue;

    public FilterControl(List<Integer> filterValue) {
        this.filterValue = filterValue;
    }

    @Override
    public boolean call(String s) throws JsonProcessingException {
        //Парсинг
        ObjectMapper mapper = new ObjectMapper();
        HashMap map = mapper.readValue(s, HashMap.class);
        FilterMap filterMap = new FilterMap(map);
        filterMap.setFilter(map);

        //Обработка логических "OR" и "AND" для выражений типа: ((1 | 2) & (3 | 4))
        if (filterValue.get(1) == 0){
            if (filterValue.get(0) == 0){
                return FilterMap.call(filterValue.get(3));
            } else if (filterValue.get(0) == 1){
                return FilterMap.call(filterValue.get(3)) || FilterMap.call(filterValue.get(4));
            } else if (filterValue.get(0) == 2){
                return FilterMap.call(filterValue.get(3)) && FilterMap.call(filterValue.get(4));
            }
        } else if (filterValue.get(1) == 1){
            if (filterValue.get(0) == 0 && filterValue.get(2) == 0){
                return FilterMap.call(filterValue.get(3)) || FilterMap.call(filterValue.get(4));

            } else if (filterValue.get(0) == 0 && filterValue.get(2) == 1){
                return FilterMap.call(filterValue.get(3)) || (FilterMap.call(filterValue.get(4)) || FilterMap.call(filterValue.get(5)));

            } else if (filterValue.get(0) == 0 && filterValue.get(2) == 2){
                return FilterMap.call(filterValue.get(3)) || (FilterMap.call(filterValue.get(4)) && FilterMap.call(filterValue.get(5)));

            } else if (filterValue.get(0) == 1 && filterValue.get(2) == 0){
                return (FilterMap.call(filterValue.get(3)) || FilterMap.call(filterValue.get(4))) || FilterMap.call(filterValue.get(5));

            } else if (filterValue.get(0) == 1 && filterValue.get(2) == 1){
                return (FilterMap.call(filterValue.get(3)) || FilterMap.call(filterValue.get(4))) || (FilterMap.call(filterValue.get(5)) || FilterMap.call(filterValue.get(6)));

            } else if (filterValue.get(0) == 1 && filterValue.get(2) == 2){
                return (FilterMap.call(filterValue.get(3)) || FilterMap.call(filterValue.get(4))) || (FilterMap.call(filterValue.get(5)) && FilterMap.call(filterValue.get(6)));

            } else if (filterValue.get(0) == 2 && filterValue.get(2) == 0){
                return (FilterMap.call(filterValue.get(3)) && FilterMap.call(filterValue.get(4))) || FilterMap.call(filterValue.get(5));

            } else if (filterValue.get(0) == 2 && filterValue.get(2) == 1){
                return (FilterMap.call(filterValue.get(3)) && FilterMap.call(filterValue.get(4))) || (FilterMap.call(filterValue.get(5)) || FilterMap.call(filterValue.get(6)));

            } else if (filterValue.get(0) == 2 && filterValue.get(2) == 2){
                return (FilterMap.call(filterValue.get(3)) && FilterMap.call(filterValue.get(4))) || (FilterMap.call(filterValue.get(5)) && FilterMap.call(filterValue.get(6)));

            }

        } else if (filterValue.get(1) == 2){
            if (filterValue.get(0) == 0 && filterValue.get(2) == 0){
                return FilterMap.call(filterValue.get(3)) && FilterMap.call(filterValue.get(4));

            } else if (filterValue.get(0) == 0 && filterValue.get(2) == 1){
                return FilterMap.call(filterValue.get(3)) && (FilterMap.call(filterValue.get(4)) || FilterMap.call(filterValue.get(5)));

            } else if (filterValue.get(0) == 0 && filterValue.get(2) == 2){
                return FilterMap.call(filterValue.get(3)) && (FilterMap.call(filterValue.get(4)) && FilterMap.call(filterValue.get(5)));

            } else if (filterValue.get(0) == 1 && filterValue.get(2) == 0){
                return (FilterMap.call(filterValue.get(3)) || FilterMap.call(filterValue.get(4))) && FilterMap.call(filterValue.get(5));

            } else if (filterValue.get(0) == 1 && filterValue.get(2) == 1){
                return (FilterMap.call(filterValue.get(3)) || FilterMap.call(filterValue.get(4))) && (FilterMap.call(filterValue.get(5)) || FilterMap.call(filterValue.get(6)));

            } else if (filterValue.get(0) == 1 && filterValue.get(2) == 2){
                return (FilterMap.call(filterValue.get(3)) || FilterMap.call(filterValue.get(4))) && (FilterMap.call(filterValue.get(5)) && FilterMap.call(filterValue.get(6)));

            } else if (filterValue.get(0) == 2 && filterValue.get(2) == 0){
                return (FilterMap.call(filterValue.get(3)) && FilterMap.call(filterValue.get(4))) && FilterMap.call(filterValue.get(5));

            } else if (filterValue.get(0) == 2 && filterValue.get(2) == 1){
                return (FilterMap.call(filterValue.get(3)) && FilterMap.call(filterValue.get(4))) && (FilterMap.call(filterValue.get(5)) || FilterMap.call(filterValue.get(6)));

            } else if (filterValue.get(0) == 2 && filterValue.get(2) == 2){
                return (FilterMap.call(filterValue.get(3)) && FilterMap.call(filterValue.get(4))) && (FilterMap.call(filterValue.get(5)) && FilterMap.call(filterValue.get(6)));

            }

        }
        return true;
    }

    public void setFilters(List<Integer> filterValue) {
        this.filterValue = filterValue;
    }
}
