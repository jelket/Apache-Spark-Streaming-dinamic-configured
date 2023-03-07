package org.example.filters.stringfilters;

import org.apache.spark.api.java.function.FilterFunction;

public class FilterEqualsN implements FilterFunction<String> {
    Integer filterValue;

    public FilterEqualsN(int filterValue) {
        this.filterValue = filterValue;
    }

    @Override
    public boolean call(String s) {
        System.out.println("Filter 2 Value from broadcast in: " + FilterEqualsN.class.getName() + " = " + filterValue);
        System.out.println("Filter 2 words: " + s.split(" ").length);
        return s.split(" ").length == filterValue;
    }

    public int getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(int filterValue) {
        this.filterValue = filterValue;
    }
}