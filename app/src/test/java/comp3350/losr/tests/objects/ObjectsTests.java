package comp3350.losr.tests.objects;

import junit.framework.Test;
import junit.framework.TestSuite;

public class ObjectsTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Object tests");
        suite.addTestSuite(UserTest.class);
        suite.addTestSuite(ProfileTest.class);
        suite.addTestSuite(MatchTest.class);
        suite.addTestSuite(QuestionTest.class);
        return suite;
    }
}
