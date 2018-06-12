package models;


import java.time.LocalDateTime;

public class CatItems extends Item {
    private static final String DATABASE_TYPE = "cat";

    public CatItems(int priceInCents, int discountAsPercentage,  String description){
        super(priceInCents, discountAsPercentage, description);
        type = DATABASE_TYPE;
    }
}
