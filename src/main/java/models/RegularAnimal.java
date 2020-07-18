package models;

import org.sql2o.Connection;
import java.util.List;

public class RegularAnimal extends Animal {
    private static final String DB_TYPE = "Not Endangered";

    public RegularAnimal(String name, String health, String age) {
        this.name = name;
        this.health = health;
        this.age = age;
        this.type = DB_TYPE;
    }
    public static List<RegularAnimal> all(){
        String sql = "SELECT * FROM animals where type=:type";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("type",DB_TYPE)
                    .executeAndFetch(RegularAnimal.class);
        }
    }

    public static RegularAnimal find(int searchId){
        String sql = "SELECT * FROM animals where (id=:id AND type=:type)";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id",searchId)
                    .addParameter("type",DB_TYPE)
                    .executeAndFetchFirst(RegularAnimal.class);
        }
    }

}
