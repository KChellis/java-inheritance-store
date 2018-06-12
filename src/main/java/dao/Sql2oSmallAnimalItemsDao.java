package dao;


import models.Item;
import models.SmallAnimalItems;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oSmallAnimalItemsDao implements SmallAnimalItemsDao {
    private final Sql2o sql2o;
    public Sql2oSmallAnimalItemsDao(Sql2o sql2o) { this.sql2o = sql2o; }


    @Override
    public void add(SmallAnimalItems smallAnimalItems) {
        String sql = "INSERT INTO items (type, priceInCents, discountAsPercentage, description) VALUES (:type, :priceInCents, :discountAsPercentage, :description)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(smallAnimalItems)
                    .executeUpdate()
                    .getKey();
            smallAnimalItems.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public List<SmallAnimalItems> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM items WHERE type = 'small animal'")
                    .executeAndFetch(SmallAnimalItems.class);
        }

    }
    @Override
    public List<Item> getAllFromAllTypes() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM items")
                    .executeAndFetch(Item.class);
        }

    }

    @Override
    public SmallAnimalItems findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM items WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(SmallAnimalItems.class);
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
        String sql = "DELETE from items WHERE type ='small animal'";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

}
