package dao;

import models.DogItems;
import models.SmallAnimalItems;
import models.CatItems;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;

import static org.junit.Assert.*;

public class Sql2oDogItemsDaoTest {
    private Sql2oSmallAnimalItemsDao smallAnimalItemsDao;
    private Sql2oDogItemsDao dogItemsDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        smallAnimalItemsDao = new Sql2oSmallAnimalItemsDao(sql2o);
        dogItemsDao = new Sql2oDogItemsDao(sql2o);
        conn = sql2o.open();
    }

    public DogItems setupNewDogItems(){
        return new DogItems(1499, 10, "kong");
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add() {
        DogItems dogItems = setupNewDogItems();
        int idOfOriginal = dogItems.getId();
        dogItemsDao.add(dogItems);
        assertNotEquals(idOfOriginal, dogItems.getId());
    }

    @Test
    public void getAll() {
        DogItems dogItems = setupNewDogItems();
        DogItems dogItemsTwo = setupNewDogItems();
        dogItemsDao.add(dogItems);
        dogItemsDao.add(dogItemsTwo);
        assertEquals(2, dogItemsDao.getAll().size());
    }

    @Test
    public void findById() {
        DogItems dogItems = setupNewDogItems();
        dogItemsDao.add(dogItems);
        DogItems foundDogItems = dogItemsDao.findById(dogItems.getId());
        assertEquals(dogItems, foundDogItems);
    }

    @Test
    public void deleteById() {
        DogItems dogItems = setupNewDogItems();
        DogItems dogItemsTwo = setupNewDogItems();
        dogItemsDao.add(dogItems);
        dogItemsDao.add(dogItemsTwo);
        dogItemsDao.deleteById(1);
        assertEquals(1, dogItemsDao.getAll().size());
    }

    @Test
    public void clearAll() {
        DogItems dogItems = setupNewDogItems();
        DogItems dogItemsTwo = setupNewDogItems();
        dogItemsDao.add(dogItems);
        dogItemsDao.add(dogItemsTwo);
        SmallAnimalItems smallAnimalItems = new SmallAnimalItems(1299, 10, "deer jerky");
        smallAnimalItemsDao.add(smallAnimalItems);
        dogItemsDao.clearAll();
        assertEquals(1, smallAnimalItemsDao.getAllFromAllTypes().size());
    }

    @Test
    public void updateChangesValues() {
        DogItems dogItems = setupNewDogItems();
        dogItemsDao.add(dogItems);
        System.out.println(dogItems.getDiscountAsPercentage());
        HashMap<String, Object> updateContent = new HashMap<>();
        updateContent.put("priceInCents", 699);
        updateContent.put("description", "a thing");
        dogItemsDao.update(dogItems.getId(), updateContent);
        DogItems updatedItem = dogItemsDao.findById(dogItems.getId());
        assertEquals("a thing", updatedItem.getDescription());
        assertEquals(699, updatedItem.getPriceInCents());
        assertEquals(10, updatedItem.getDiscountAsPercentage());
    }
}