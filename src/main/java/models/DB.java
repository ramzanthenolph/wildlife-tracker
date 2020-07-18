package models;
import org.sql2o.*;
import java.net.URI;
import java.net.URISyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DB {
    private static URI dbUri;
    public static Sql2o sql2o;
    static Logger logger = LoggerFactory.getLogger(DB.class);

    static {

        try{
            if(System.getenv("DATABASE_URL") == null){
                dbUri = new URI("postgres://localhost:4567/wildlife-tracker");
            }
            else {
                dbUri = new URI(System.getenv("DATABASE_URL"));
            }

            int port = dbUri.getPort();
            String host = dbUri.getHost();
            String path = dbUri.getPath();

            sql2o = new Sql2o("jdbc:postgresql://" + host + ":" + port + path);
        } catch (URISyntaxException e){
            logger.error("unable to connect to database");
        }

    }
}
