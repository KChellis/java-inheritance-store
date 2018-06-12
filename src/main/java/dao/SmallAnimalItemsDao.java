package dao;

import models.SmallAnimalItems;

import java.util.List;

public interface SmallAnimalItemsDao {

    //create
    void add(SmallAnimalItems smallAnimalItems);

    //read
    List<SmallAnimalItems> getAll();
    SmallAnimalItems findById( int id);


    //update
    //omit for now

    //delete
    void deleteById(int id);
    void clearAll();

}
