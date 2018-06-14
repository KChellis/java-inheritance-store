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



        post("/items/catitems/new", "application/json", (req, res) -> {
            CatItems catItems = gson.fromJson(req.body(), CatItems.class);
            catItemsDao.add(catItems);
            res.status(201);
            return gson.toJson(catItems);
        });

        post("/items/dogItems/new", "application/json", (req, res) -> {
            DogItems dogItems = gson.fromJson(req.body(), DogItems.class);
            dogItemsDao.add(dogItems);
            res.status(201);
            return gson.toJson(dogItems);
        });

        post("/items/smallAnimalItems/new", "application/json", (req, res) -> {
            SmallAnimalItems smallAnimalItems = gson.fromJson(req.body(), SmallAnimalItems.class);
            smallAnimalItemsDao.add(smallAnimalItems);
            res.status(201);
            return gson.toJson(smallAnimalItems);
        });
        
        get("/items/smallanimalitems", "application/json", (req, res) -> {
            if (smallAnimalItemsDao.getAll().size() == 0) {
                return "{\"message\":\"I'm sorry, but no small animal items are currently listed in the database.\"}";
            } else {
                return gson.toJson(smallAnimalItemsDao.getAll());
            }
        });

        get("/items/catitems", "application/json", (req, res) -> {
            if (catItemsDao.getAll().size() == 0) {
                return "{\"message\":\"I'm sorry, but no cat items are currently listed in the database.\"}";
            } else {
                return gson.toJson(catItemsDao.getAll());
            }
        });

        get("/items/dogitems", "application/json", (req, res) -> {
            if (dogItemsDao.getAll().size() == 0) {
                return "{\"message\":\"I'm sorry, but no dog items are currently listed in the database.\"}";
            } else {
                return gson.toJson(dogItemsDao.getAll());
            }
        });

        get("/items", "application/json", (req, res) -> {
            if (smallAnimalItemsDao.getAllFromAllTypes().size() == 0) {
                return "{\"message\":\"I'm sorry, but no items are currently listed in the database.\"}";
            } else {
                return gson.toJson(smallAnimalItemsDao.getAllFromAllTypes());
            }
        });

        get("/items/smallanimalitems/:id", "application/json", (req, res) -> {
            int smallAnimalItemsId = Integer.parseInt(req.params("id"));
            return gson.toJson(smallAnimalItemsDao.findById(smallAnimalItemsId));
        });

        get("/items/dogitems/:id", "application/json", (req, res) -> {
            int dogItemsId = Integer.parseInt(req.params("id"));
            return gson.toJson(dogItemsDao.findById(dogItemsId));
        });

        get("/items/catitems/:id", "application/json", (req, res) -> {
            int catItemsId = Integer.parseInt(req.params("id"));
            return gson.toJson(catItemsDao.findById(catItemsId));
        });

        delete("/items/dogItems/:id/delete", "application/json", (req, res) -> {
            int dogItemsId = Integer.parseInt(req.params("id"));
            dogItemsDao.deleteById(dogItemsId);
            return "{\"message\":\"The item has been deleted\"}";
        });

        delete("/items/catItems/:id/delete", "application/json", (req, res) -> {
            int catItemsId = Integer.parseInt(req.params("id"));
            catItemsDao.deleteById(catItemsId);
            return "{\"message\":\"The item has been deleted\"}";
        });

        delete("/items/smallAnimalItems/:id/delete", "application/json", (req, res) -> {
            int smallAnimalItemsId = Integer.parseInt(req.params("id"));
            smallAnimalItemsDao.deleteById(smallAnimalItemsId);
            return "{\"message\":\"The item has been deleted\"}";
        });

        post("/items/smallanimalitems/:id/update", "application/json", (req, res) -> {
            int smallAnimalItemsId = Integer.parseInt(req.params("id"));
            HashMap<String, Object> updateContent = gson.fromJson(req.body(), HashMap.class);
            smallAnimalItemsDao.update(smallAnimalItemsId, updateContent);
            return gson.toJson(smallAnimalItemsDao.findById(smallAnimalItemsId));

        });

        post("/items/dogitems/:id/update", "application/json", (req, res) -> {
            int dogItemsId = Integer.parseInt(req.params("id"));
            HashMap<String, Object> updateContent = gson.fromJson(req.body(), HashMap.class);
            dogItemsDao.update(dogItemsId, updateContent);
            return gson.toJson(dogItemsDao.findById(dogItemsId));

        });

        post("/items/catitems/:id/update", "application/json", (req, res) -> {
            int catItemsId = Integer.parseInt(req.params("id"));
            HashMap<String, Object> updateContent = gson.fromJson(req.body(), HashMap.class);
            catItemsDao.update(catItemsId, updateContent);
            return gson.toJson(catItemsDao.findById(catItemsId));

        });


    }
}
