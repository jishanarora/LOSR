package comp3350.losr.tests.business;

import junit.framework.TestCase;

import comp3350.losr.application.DatabaseService;
import comp3350.losr.business.AccessMatches;
import comp3350.losr.tests.persistence.DataAccessStub;

public class AccessMatchesTest extends TestCase {
    AccessMatches am;

    public void setUp() {
        DatabaseService.createDataAccess(new DataAccessStub("Stub"));
        am = new AccessMatches();
    }

    public void testCreate() {
        System.out.println("Starting testCreate");
        assertNotNull(am);
        System.out.println("testCreate complete");
    }

    public void testGetMatches() {
        System.out.println("Starting testGetMatches");

        assertEquals(4, am.getMatches().size()); //these are sorted by best compatibility with the fake current user
        assertEquals("jessicafie@gmail.com", am.getMatches().get(0).getMatchedUser().getUserEmail());
        assertEquals("marypoppins@gmail.com", am.getMatches().get(1).getMatchedUser().getUserEmail());
        assertEquals("amykowall@gmail.com", am.getMatches().get(2).getMatchedUser().getUserEmail());
        assertEquals("laurastubbs@gmail.com", am.getMatches().get(3).getMatchedUser().getUserEmail());

        System.out.println("testGetMatches complete");
    }

    public void testBlindMatches() {

        am.getMatches();


    }
}
