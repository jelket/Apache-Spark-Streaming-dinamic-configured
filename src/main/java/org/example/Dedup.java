package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.spark.api.java.function.FilterFunction;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.meta.derby.sys.Sys;
import org.jooq.sources.tables.Deduplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Dedup implements FilterFunction<String> {
    String dedupValue;

    public Dedup(String dedupValue) {
        this.dedupValue = dedupValue;
    }


    @Override
    public boolean call(String s) throws JsonProcessingException, SQLException {
        //Переменные доступа к базе данных
        String userName = "postgres";
        String password = "postgres";
        String url = "jdbc:postgresql://localhost:5432/diploma";

        final String[] DedupValue = new String[1];

        ObjectMapper mapper = new ObjectMapper();
        HashMap map = mapper.readValue(s, HashMap.class);
        try {
            Connection connDedup = DriverManager.getConnection(url, userName, password);
            DSLContext createDedup = DSL.using(connDedup, SQLDialect.POSTGRES);
            Result<Record> resultDedup = createDedup.select().from(Deduplication.DEDUPLICATION).where(Deduplication.DEDUPLICATION.VALUE.eq(map.get(dedupValue).toString())).fetch();
            System.out.println(map.get(dedupValue).toString());

            if (resultDedup.isEmpty()){
                createDedup.insertInto(Deduplication.DEDUPLICATION,
                                Deduplication.DEDUPLICATION.VALUE, Deduplication.DEDUPLICATION.TIMESTAMP)
                        .values(map.get(dedupValue).toString(), System.currentTimeMillis())
                        .execute();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    //Получение строчки с названием поля
    public void setDedupValue(String dedupValue) {
        this.dedupValue = dedupValue;
    }
}