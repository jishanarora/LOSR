package comp3350.losr.tests;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;

import comp3350.losr.tests.acceptance.AccountAcceptanceTests;
import comp3350.losr.tests.acceptance.BlindModeAcceptanceTests;
import comp3350.losr.tests.acceptance.MatchAcceptanceTests;
import comp3350.losr.tests.acceptance.ProfileAcceptanceTests;
import comp3350.losr.tests.acceptance.QuestionsAcceptanceTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({AccountAcceptanceTests.class, ProfileAcceptanceTests.class, MatchAcceptanceTests.class, BlindModeAcceptanceTests.class, QuestionsAcceptanceTests.class})
public class RunAcceptanceTests
{
    public RunAcceptanceTests()
    {
        System.out.println("Acceptance tests");
    }
}
