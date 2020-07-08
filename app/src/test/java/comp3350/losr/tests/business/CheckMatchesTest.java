package comp3350.losr.tests.business;

import junit.framework.TestCase;

import java.util.ArrayList;

import static comp3350.losr.business.CheckMatches.matchPercentage;

public class CheckMatchesTest extends TestCase {
    private ArrayList<Boolean> list1;
    private ArrayList<Boolean> list2;

    public void setUp()
    {
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
    }

    public void testEmpty()
    {
        assertEquals(0, matchPercentage(list1, list2));
    }

    public void testOneEmpty()
    {
        list1.add(Boolean.FALSE);
        assertEquals(0, matchPercentage(list1, list2));

        list1.clear();

        list2.add(Boolean.TRUE);
        assertEquals(0, matchPercentage(list1, list2));
    }

    public void testNull()
    {

    }
}
