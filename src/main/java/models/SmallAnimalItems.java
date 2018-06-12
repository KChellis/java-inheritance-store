package models;

import java.time.LocalDateTime;

public class SmallAnimalItems extends Item {
    private static final String DATABASE_TYPE = "small animal";

    public SmallAnimalItems(int priceInCents, int discountAsPercentage, String description){
        super(priceInCents, discountAsPercentage,  description);
        type = DATABASE_TYPE;
    }
}