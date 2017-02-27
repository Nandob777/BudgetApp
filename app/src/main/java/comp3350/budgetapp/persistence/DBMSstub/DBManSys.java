package comp3350.budgetapp.persistence.DBMSstub;

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
}
