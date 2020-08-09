package comp3350.losr.tests;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;

import comp3350.losr.tests.acceptance.AccountAcceptanceTests;
import comp3350.losr.tests.acceptance.ProfileAcceptanceTests;

@RunWith(Suite.class)
//@Suite.SuiteClasses({AccountAcceptanceTests.class, ProfileAcceptanceTests.class})
@Suite.SuiteClasses({AccountAcceptanceTests.class})
//@Suite.SuiteClasses({ProfileAcceptanceTests.class})
public class RunAcceptanceTests
{
    public RunAcceptanceTests()
    {
        System.out.println("Acceptance tests");
    }
}
