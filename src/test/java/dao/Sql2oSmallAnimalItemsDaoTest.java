package dao;

import models.SmallAnimalItems;
import models.CatItems;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oSmallAnimalItemsDaoTest {
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

    public SmallAnimalItems setupSmallAnimalItems(){
        return new SmallAnimalItems(599, 0, "fish food");
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add() {
        SmallAnimalItems smallAnimalItems = setupSmallAnimalItems();
        int idofOriginal = smallAnimalItems.getId();
        smallAnimalItemsDao.add(smallAnimalItems);
        assertNotEquals(idofOriginal, smallAnimalItems.getId());
    }

    @Test
    public void getAll() {
        SmallAnimalItems smallAnimalItems = setupSmallAnimalItems();
        SmallAnimalItems smallAnimalItemsTwo = setupSmallAnimalItems();
        smallAnimalItemsDao.add(smallAnimalItems);
        smallAnimalItemsDao.add(smallAnimalItemsTwo);
        assertEquals(2, smallAnimalItemsDao.getAll().size());
    }

    @Test
    public void findById() {
        SmallAnimalItems smallAnimalItems = setupSmallAnimalItems();
        smallAnimalItemsDao.add(smallAnimalItems);
        SmallAnimalItems foundSmallAnimalItems = smallAnimalItemsDao.findById(smallAnimalItems.getId());
        assertEquals(smallAnimalItems, foundSmallAnimalItems);

    }

    @Test
    public void deleteById() {
        SmallAnimalItems smallAnimalItems = setupSmallAnimalItems();
        SmallAnimalItems smallAnimalItemsTwo = setupSmallAnimalItems();
        smallAnimalItemsDao.add(smallAnimalItems);
        smallAnimalItemsDao.add(smallAnimalItemsTwo);
        smallAnimalItemsDao.deleteById(2);
        assertEquals(1, smallAnimalItemsDao.getAll().size());
    }

    @Test
    public void clearAll() {
        SmallAnimalItems smallAnimalItems = setupSmallAnimalItems();
        SmallAnimalItems smallAnimalItemsTwo = setupSmallAnimalItems();
        CatItems catItems = new CatItems(599, 10, "a plastic ball with a jingle bell");
        catItemsDao.add(catItems);
        smallAnimalItemsDao.add(smallAnimalItems);
        smallAnimalItemsDao.add(smallAnimalItemsTwo);
        smallAnimalItemsDao.clearAll();
        assertEquals(1, smallAnimalItemsDao.getAllFromAllTypes().size());
    }
}