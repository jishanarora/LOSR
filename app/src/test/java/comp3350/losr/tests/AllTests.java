package comp3350.losr.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import comp3350.losr.tests.business.CheckMatchesTest;
import comp3350.losr.tests.objects.MatchTest;
import comp3350.losr.tests.objects.ProfileTest;
import comp3350.losr.tests.objects.UserTest;

public class AllTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("All tests");
        testObjects();
        testBusiness();
        return suite;
    }

    private static void testObjects()
    {
        suite.addTestSuite(UserTest.class);
        suite.addTestSuite(ProfileTest.class);
        suite.addTestSuite(MatchTest.class);
    }

    private static void testBusiness()
    {
        suite.addTestSuite(CheckMatchesTest.class);
    }
}
