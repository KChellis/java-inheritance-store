package models;

import java.time.LocalDateTime;

public class DogItems extends Item {
    private static final String DATABASE_TYPE = "dog";

    public DogItems(int priceInCents, int discountAsPercentage,  String description){
        super(priceInCents, discountAsPercentage,  description);
        type = DATABASE_TYPE;
    }


}
