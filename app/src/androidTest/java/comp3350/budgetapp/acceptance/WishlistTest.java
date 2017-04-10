package comp3350.budgetapp.acceptance;

import junit.framework.Assert;

import com.robotium.solo.Solo;

import comp3350.budgetapp.presentation.MainActivity;

import android.test.ActivityInstrumentationTestCase2;

public class WishlistTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;

    public WishlistTest()
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

    public void testAddItem()
    {

        System.out.println("Getting to wishlist activity for addItem test");

        String price = "" + randomPrice();
        String name = randomString();

        getToActivity();

        solo.clearEditText(0);
        solo.enterText(0,name);
        solo.clearEditText(1);
        solo.enterText(1,price);
        solo.clickOnButton("Add");
        solo.clearEditText(0);
        solo.clearEditText(1);
        Assert.assertTrue(solo.searchText(name));
        Assert.assertTrue(solo.searchText(price));

        //cleanup
        solo.enterText(0,name);
        solo.clickOnButton("Delete");
    }

    public void testEditItem(){

    }

    public void testDeleteItem(){

    }

    public void testAccess() // tests whether user can access and see relevent info
    {
        solo.waitForActivity("MainActivity");
        Assert.assertTrue(solo.searchButton("Wish List", true)); // only searches visible buttons
        solo.clickOnButton("Wish List");
        solo.assertCurrentActivity("Expected activity WishlistActivity", "WishlistActivity");

        // Assert.assertTrue(solo.searchText("BudgIt: Wishlist")); // may need to be more specific, an entry may be called that
        Assert.assertTrue(solo.searchButton("Delete"));
        Assert.assertTrue(solo.searchButton("Add"));
        Assert.assertTrue(solo.searchButton("Update"));
        Assert.assertTrue(solo.searchButton("Purchased"));

        Assert.assertTrue(solo.searchText("Total:"));
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
            int key = (int)(Math.random()*52) + 'a';
            result += (char)key;
        }

        return result;
    }
}
