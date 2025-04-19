package org.example.tenantapp.helperclasses;

import java.util.ArrayList;
import java.util.List;

public class TestDataSource {
    public static List<DataSource> dataSources = new ArrayList<DataSource>();
    static {
        for (int i = 1; i <= 5; i++) {
            dataSources.add(new DataSource(
                    "localhost",
                    "3306",
                    "kg_db" + i,
                    "kg_user",
                    "kg_pass"
            ));
        }
    }
    public  static DataSource get(int index){
        return dataSources.get(index);
    }
}
