package comp3350.budgetapp.tests.persistence;

import junit.framework.TestCase;

import comp3350.budgetapp.persistence.*;

public class DataAccessFactoryTest extends TestCase {

    public void testStubCreation(){

        System.out.println("\nStarting testStubCreation for data access factory");

        DataAccess stub = DataAccessFactory.getDataAccess("test",true);
        assertTrue(stub instanceof DataAccessStub);

        System.out.println("finished testStubCreation for data access factory");
    }

    public void testDBCreation(){
        System.out.println("\nStarting testDBCreation for data access factory");

        DataAccess stub = DataAccessFactory.getDataAccess("test");
        assertTrue(stub instanceof DataAccessObject);

        System.out.println("Finished testDBCreation for data access factory");
    }

}
