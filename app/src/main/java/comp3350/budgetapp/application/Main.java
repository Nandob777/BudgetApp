package comp3350.budgetapp.application;

/**
 * Created by tinahernando on 2017-02-27.
 */

public class Main
{
    public static void main(String[] args)
    {
        startUp();

        shutDown();
        System.out.println("All done");
    }

    public static void startUp()
    {
        Services.createDataAccess();
    }

    public static void shutDown()
    {
        Services.closeDataAccess();
    }

}
