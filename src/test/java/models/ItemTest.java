package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {

    public Item setupNewItem(){
        return new Item(599, 0, "a chewy rope");
    }


    @Test
    public void setPriceInCents() {
        Item newItem =setupNewItem();
        newItem.setPriceInCents(399);
        assertEquals(399, newItem.getPriceInCents());
    }

    @Test
    public void setDiscountAsPercentage() {
        Item newItem =setupNewItem();
        newItem.setDiscountAsPercentage(10);
        assertEquals(10, newItem.getDiscountAsPercentage());
    }

    @Test
    public void setTimeOfSale() {
        Item newItem =setupNewItem();
        newItem.setTimeOfSale("2018-06-01 15:32:44");
        assertEquals("2018-06-01 15:32:44", newItem.getTimeOfSale());
    }

    @Test
    public void setDescription() {
        Item newItem =setupNewItem();
        newItem.setDescription("an iguana lamp");
        assertEquals("an iguana lamp", newItem.getDescription());
    }

    @Test
    public void setId() {
        Item newItem =setupNewItem();
        newItem.setId(1);
        assertEquals(1, newItem.getId());
    }

    @Test
    public void setType() {
        Item newItem =setupNewItem();
        newItem.setType("dog");
        assertEquals("dog", newItem.getType());
    }
}