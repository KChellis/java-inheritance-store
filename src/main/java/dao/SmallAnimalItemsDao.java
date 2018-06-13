package dao;

import models.Item;
import models.SmallAnimalItems;

import java.util.HashMap;
import java.util.List;

public interface SmallAnimalItemsDao {

    //create
    void add(SmallAnimalItems smallAnimalItems);

    //read
    List<SmallAnimalItems> getAll();
    List<Item>getAllFromAllTypes();
    SmallAnimalItems findById( int id);


    //update
    void update(int id, HashMap<String, Object> updateContent);

    //delete
    void deleteById(int id);
    void clearAll();

}
