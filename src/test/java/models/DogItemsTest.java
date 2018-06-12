package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DogItemsTest {


    @Test
    public void instantiationOfDogObject() {
        DogItems newDogItem = new DogItems(999,0, "yummy turkey treats");
        assertEquals("dog", newDogItem.getType());

    }
}