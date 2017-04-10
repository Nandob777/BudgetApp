package comp3350.budgetapp.acceptance;

import junit.framework.Assert;

import com.robotium.solo.Solo;

import comp3350.budgetapp.presentation.MainActivity;

import android.test.ActivityInstrumentationTestCase2;

public class PurchaseHistoryTests extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;

    public PurchaseHistoryTests()
    {
        super(MainActivity.class);
    }

    public void setUp() throws Exception
    {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception
    {
        solo.finishOpenedActivities();
    }

    public void testPurchaseItem(){
        String price = "" + randomPrice();
        String name = randomString();

        getToActivity();

        solo.enterText(0,name);
        solo.enterText(1,price);
        solo.clickOnButton("Add");

        solo.enterText(0,name);
        solo.clickOnButton("Purchase");

        solo.goBack();
        solo.clickOnButton("Expenses");

        Assert.assertTrue(solo.searchText(name,true)); // sometimes fails here, too fast

        solo.enterText(0,name);
        solo.clickOnButton("Delete");
    }

    private void getToActivity(){
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("Wish List");
    }

    private double randomPrice(){
        double price = Math.random()*1000;
        price = ((double)((int)(price*100)))/100;

        return price;
    }

    private String randomString(){
        String result = "";

        for(int i = 0; i < 12; i++){
            int key = (int)(Math.random()*52) + 'A';
            result += (char)key;
        }

        return result;
    }
}
