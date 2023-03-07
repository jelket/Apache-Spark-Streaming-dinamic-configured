package org.example.transformations;

import org.apache.spark.api.java.function.MapFunction;

public class TransformationUpperCase implements MapFunction<String, String> {
    @Override
    public String call(String word) throws Exception {
        char[] arraySymbols = word.toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < arraySymbols.length; i++) {
            if (i % 2 != 0) {
                Character arraySymbol = arraySymbols[i];
                result.append(arraySymbol.toString().toUpperCase());
            } else {
                result.append(arraySymbols[i]);
            }
        }
        return result.toString();
    }
}