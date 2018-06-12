package dao;

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
        return null;
    }

    @Override
    public SmallAnimalItems findById(int id) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAll() {

    }

}
