package dao;

import models.DogItems;
import models.Item;
import models.SmallAnimalItems;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Sql2oDogItemsDao implements DogItemsDao{
    private final Sql2o sql2o;
    public Sql2oDogItemsDao(Sql2o sql2o) { this.sql2o = sql2o; }

    @Override
    public void add(DogItems dogItems) {
        String sql = "INSERT INTO items (type, priceInCents, discountAsPercentage, description) VALUES (:type, :priceInCents, :discountAsPercentage, :description)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(dogItems)
                    .executeUpdate()
                    .getKey();
            dogItems.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public List<DogItems> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM items WHERE type = 'dog'")
                    .executeAndFetch(DogItems.class);
        }

    }


    @Override
    public DogItems findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM items WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(DogItems.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from items WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }


    @Override
    public void clearAll() {
        String sql = "DELETE from items WHERE type ='dog'";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void update(int id, HashMap<String, Object> updateContent) {
        for(String key : updateContent.keySet()){
            String sql = "UPDATE items SET (" + key + ") = (:" + key + ") WHERE id = :id";
            try (Connection con = sql2o.open()) {
                con.createQuery(sql)
                        .addParameter(key, updateContent.get(key))
                        .addParameter("id", id)
                        .executeUpdate();
            } catch (Sql2oException ex) {
                System.out.println(ex);
            }
        }
    }
}
