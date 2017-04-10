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
}
