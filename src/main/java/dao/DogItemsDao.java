package dao;

import models.DogItems;

import java.util.List;

public interface DogItemsDao {
    //create
    void add(DogItems dogItems);

    //read
    List<DogItems> getAll();
    DogItems findById( int id);


    //update
    //omit for now

    //delete
    void deleteById(int id);
    void clearAll();
}
