package comp3350.budgetapp.persistence.DBMSstub;

/**
 * Created by mardelmaduro on 2017-02-27.
 */
import java.util.*;

class Database extends HashMap<String, Object>{
    public String[] getAllKeys(){
        Object[] keys = keySet().toArray();
        String[] result = new String[keys.length];
        boolean allString = true;

        for(int i = 0; i < keys.length; i++){
            allString = (keys[i] instanceof String);
            if(allString){
                result[i] = (String)keys[i];
            }
        }

        return result;
    }

    public Object[] getAllValues(){
        return values().toArray();
    }

    public String randomNewKey(){
        String key = null;
        do{
            int intermediate = (int)(10000*Math.random());
            key = String.valueOf(intermediate);
        }while(!super.containsKey(key));

        return key;
    }

    public boolean contains(Object o)
    {
        return this.contains(o);
    }
}
