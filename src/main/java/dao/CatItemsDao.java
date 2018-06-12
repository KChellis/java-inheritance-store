package dao;

import models.CatItems;

import java.util.List;

public interface CatItemsDao {
    //create
    void add(CatItems catItems);

    //read
    List<CatItems> getAll();
    CatItems findById( int id);


    //update
    //omit for now

    //delete
    void deleteById(int id);
    void clearAll();
}
