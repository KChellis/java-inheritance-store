package models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Item {
    private int priceInCents;
    private int discountAsPercentage;
    private String timeOfSale;
    private String description;
    private int id;
    public String type;

    public Item(int priceInCents, int discountAsPercentage,  String description){
        this.priceInCents = priceInCents;
        this.discountAsPercentage = discountAsPercentage;
        this.description = description;
    }

    public int getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(int priceInCents) {
        this.priceInCents = priceInCents;
    }

    public int getDiscountAsPercentage() {
        return discountAsPercentage;
    }

    public void setDiscountAsPercentage(int discountAsPercentage) {
        this.discountAsPercentage = discountAsPercentage;
    }

    public String getTimeOfSale() {
        return timeOfSale;
    }

    public void setTimeOfSale(String timeOfSale) {
        this.timeOfSale = timeOfSale;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return priceInCents == item.priceInCents &&
                discountAsPercentage == item.discountAsPercentage &&
                id == item.id &&
                Objects.equals(timeOfSale, item.timeOfSale) &&
                Objects.equals(description, item.description) &&
                Objects.equals(type, item.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(priceInCents, discountAsPercentage, timeOfSale, description, id, type);
    }
}
