package comp3350.losr.tests.business;

import junit.framework.Test;
import junit.framework.TestSuite;

public class BusinessTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Business tests");
        suite.addTestSuite(CheckMatchesTest.class);
        suite.addTestSuite(AccessMatchesTest.class);
        suite.addTestSuite(AccessUsersTest.class);
        return suite;
    }
}
