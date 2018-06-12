import com.google.gson.Gson;
import dao.Sql2oSmallAnimalItemsDao;
import dao.Sql2oDogItemsDao;
import dao.Sql2oCatItemsDao;
import exceptions.ApiException;
import models.CatItems;
import models.DogItems;
import models.SmallAnimalItems;
import models.Item;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;




public class App {
    public static void main(String[] args) {
        Sql2oSmallAnimalItemsDao smallAnimalItemsDao;
        Sql2oDogItemsDao dogItemsDao;
        Sql2oCatItemsDao catItemsDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/salesify.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        smallAnimalItemsDao = new Sql2oSmallAnimalItemsDao(sql2o);
        dogItemsDao = new Sql2oDogItemsDao(sql2o);
        catItemsDao = new Sql2oCatItemsDao(sql2o);
        conn = sql2o.open();
    }
}
