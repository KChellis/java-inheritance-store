package dao;

import models.CatItems;

import java.util.HashMap;
import java.util.List;

public interface CatItemsDao {
    //create
    void add(CatItems catItems);

    //read
    List<CatItems> getAll();
    CatItems findById( int id);


    //update
    void update(int id, HashMap<String, Object> updateContent);

    //delete
    void deleteById(int id);
    void clearAll();
}
