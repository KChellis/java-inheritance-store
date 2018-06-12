package models;

import org.junit.Test;
import static org.junit.Assert.*;

public class CatItemsTest {

    @Test
    public void instatiationOfCatItemObject() {
        CatItems newCatItem = new CatItems(999, 0, "fake bird on wand");
        assertEquals("cat", newCatItem.getType());
    }

}