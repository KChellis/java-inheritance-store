package dao;

import models.SmallAnimalItems;
import models.CatItems;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;

import static org.junit.Assert.*;

public class Sql2oCatItemsDaoTest {
    private Sql2oSmallAnimalItemsDao smallAnimalItemsDao;
    private Sql2oCatItemsDao catItemsDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        smallAnimalItemsDao = new Sql2oSmallAnimalItemsDao(sql2o);
        catItemsDao = new Sql2oCatItemsDao(sql2o);
        conn = sql2o.open();
    }
    public CatItems setupCatItems(){
        return new CatItems(599, 0, "salmon jerky");
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add() {
        CatItems catItems = setupCatItems();
        int idofOriginal = catItems.getId();
        catItemsDao.add(catItems);
        assertNotEquals(idofOriginal, catItems.getId());
    }

    @Test
    public void getAll() {
        CatItems catItems = setupCatItems();
        CatItems catItemsTwo = setupCatItems();
        catItemsDao.add(catItems);
        catItemsDao.add(catItemsTwo);
        assertEquals(2, catItemsDao.getAll().size());
    }

    @Test
    public void findById() {
        CatItems catItems = setupCatItems();
        catItemsDao.add(catItems);
        CatItems foundCatItems = catItemsDao.findById(catItems.getId());
        assertEquals(catItems, foundCatItems);
    }

    @Test
    public void deleteById() {
        CatItems catItems = setupCatItems();
        CatItems catItemsTwo = setupCatItems();
        catItemsDao.add(catItems);
        catItemsDao.add(catItemsTwo);
        catItemsDao.deleteById(1);
        assertEquals(1, catItemsDao.getAll().size());
    }

    @Test
    public void clearAll() {
        CatItems catItems = setupCatItems();
        CatItems catItemsTwo = setupCatItems();
        SmallAnimalItems smallAnimalItems = new SmallAnimalItems(399, 15, "fish tank gravel");
        catItemsDao.add(catItems);
        catItemsDao.add(catItemsTwo);
        smallAnimalItemsDao.add(smallAnimalItems);
        catItemsDao.clearAll();
        assertEquals(1, smallAnimalItemsDao.getAllFromAllTypes().size());
    }

    @Test
    public void updateChangesValues() {
        CatItems catItems = setupCatItems();
        catItemsDao.add(catItems);
        System.out.println(catItems.getDiscountAsPercentage());
        HashMap<String, Object> updateContent = new HashMap<>();
        updateContent.put("priceInCents", 699);
        updateContent.put("description", "a thing");
        catItemsDao.update(catItems.getId(), updateContent);
        CatItems updatedItem = catItemsDao.findById(catItems.getId());
        assertEquals("a thing", updatedItem.getDescription());
        assertEquals(699, updatedItem.getPriceInCents());
        assertEquals(0, updatedItem.getDiscountAsPercentage());
    }
}