package dao;

import models.DogItems;

import java.util.HashMap;
import java.util.List;

public interface DogItemsDao {
    //create
    void add(DogItems dogItems);

    //read
    List<DogItems> getAll();
    DogItems findById( int id);


    //update
    void update(int id, HashMap<String, Object> updateContent);

    //delete
    void deleteById(int id);
    void clearAll();
}
