package comp3350.losr.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import comp3350.losr.tests.business.BusinessTests;
import comp3350.losr.tests.objects.ObjectsTests;
import comp3350.losr.tests.persistence.PersistenceTests;

public class RunUnitTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Unit tests");
        suite.addTest(ObjectsTests.suite());
        suite.addTest(BusinessTests.suite());
        suite.addTest(PersistenceTests.suite());
        return suite;
    }
}
