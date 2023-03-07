package org.example.transformations;

import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class SrtuctsOptional {
    public static StructField[] call;
    Integer filterValue;
    StructType s;

    public SrtuctsOptional() {
    }

    public void setFilterValue(int filterValue) {
        this.filterValue = filterValue;
    }
/*
    @Override
    public StructType call(StructType s) {
        return s.add("Recency", DataTypes.IntegerType)
                .add("Frequency", DataTypes.IntegerType)
                .add("Monetary", DataTypes.IntegerType)
                .add("Time", DataTypes.IntegerType)
                .add("whether", DataTypes.IntegerType);
    }
*/
    public StructType call() {
        if (filterValue == 0){
            s = new StructType().add("name", DataTypes.StringType)
                    .add("surname", DataTypes.StringType)
                    .add("age", DataTypes.IntegerType)
                    .add("job", DataTypes.StringType)
                    .add("comment", DataTypes.StringType);
        } else if (filterValue == 1){
            s = new StructType().add("name", DataTypes.StringType);
        } else if (filterValue == 2){
            s = new StructType().add("name", DataTypes.StringType)
                    .add("surname", DataTypes.StringType);
        } else if (filterValue == 3){
            s = new StructType().add("name", DataTypes.StringType)
                    .add("surname", DataTypes.StringType)
                    .add("age", DataTypes.IntegerType);
        } else if (filterValue == 4){
            s = new StructType().add("name", DataTypes.StringType)
                    .add("surname", DataTypes.StringType)
                    .add("age", DataTypes.IntegerType)
                    .add("job", DataTypes.StringType);
        } else if (filterValue == 5){
            s = new StructType().add("name", DataTypes.StringType)
                    .add("comment", DataTypes.StringType);
        } else if (filterValue == 6){
            s = new StructType().add("name", DataTypes.StringType)
                    .add("surname", DataTypes.StringType)
                    .add("comment", DataTypes.StringType);
        } else if (filterValue == 7){
            s = new StructType().add("job", DataTypes.StringType)
                    .add("comment", DataTypes.StringType);
        } else {
            s = new StructType().add("name", DataTypes.StringType)
                    .add("surname", DataTypes.StringType)
                    .add("age", DataTypes.IntegerType)
                    .add("job", DataTypes.StringType)
                    .add("comment", DataTypes.StringType);
        }
        return s;
    }

}