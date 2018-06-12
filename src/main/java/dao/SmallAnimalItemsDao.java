package dao;

import models.Item;
import models.SmallAnimalItems;

import java.util.List;

public interface SmallAnimalItemsDao {

    //create
    void add(SmallAnimalItems smallAnimalItems);

    //read
    List<SmallAnimalItems> getAll();
    List<Item>getAllFromAllTypes();
    SmallAnimalItems findById( int id);


    //update
    //omit for now

    //delete
    void deleteById(int id);
    void clearAll();

}
