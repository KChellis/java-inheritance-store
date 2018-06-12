package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class SmallAnimalItemsTest {

    @Test
    public void instatiationOfSmallAnimalItemObject() {
        SmallAnimalItems newSmallAnimalItem = new SmallAnimalItems(9999, 10, "fish tank");
        assertEquals("small animal", newSmallAnimalItem.getType());
    }

}