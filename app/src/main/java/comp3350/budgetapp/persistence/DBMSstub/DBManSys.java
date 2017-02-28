package comp3350.budgetapp.persistence.DBMSstub;

import java.util.ArrayList;
import java.util.List;

import comp3350.budgetapp.objects.WishListItem;

/**
 * Created by mardelmaduro on 2017-02-27.
 */
public class DBManSys{
    private Database db;

    public DBManSys(){
        db = new Database();
    }

    public void loadDB(){

        //Placeholder values, when we want to test but don't want to load it ourselves

        WishListItem item;

        item = new WishListItem("laptop",600.00);
        db.put(item.getItemName(),item);
        item = new WishListItem("phone",400.00);
        db.put(item.getItemName(),item);

    }

    public void close()
    {
        System.out.println(" Closed database ");
    }

    public boolean put(String key, Object value){
        return db.put(key, value).equals(value);
    }

    public boolean put(Object value){
        String key = db.randomNewKey();

        return db.put(key, value).equals(value);
    }

    public Object get(String key){
        return db.get(key);
    }

    public void delete(String key){
        db.remove(key);
    }


    public String[] getAllKeys(){
        return db.getAllKeys();
    }

    public Object[] getAllValues(){
        return db.getAllValues();
    }

    public String addWishListItem(WishListItem item)
    {
        db.put(item.getItemName(),item);
        return null;
    }

    public String deleteWishListItem(WishListItem item)
    {
        db.remove(item.getItemName());
        return null;
    }

    public String updateWishListItem(WishListItem item)
    {
        db.put(item.getItemName(),item);
        return null;
    }

    public boolean contains(WishListItem item)
    {
        return db.contains(item);
    }

}
