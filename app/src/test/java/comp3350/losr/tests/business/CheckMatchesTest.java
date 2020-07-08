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
        list1 = null;
        list2 = null;

        try
        {
            matchPercentage(list1, list2);
            fail();
        }
        catch(NullPointerException npe){}
    }

    public void testOneNull()
    {
        list1 = null;
        list2.add(Boolean.TRUE);

        try
        {
            matchPercentage(list1, list2);
            fail();
        }
        catch(NullPointerException npe){}

        try
        {
            matchPercentage(list2, list1);
            fail();
        }
        catch(NullPointerException npe){}
    }

    public void testFullMatch()
    {
        list1.add(Boolean.TRUE);
        list1.add(Boolean.FALSE);
        list1.add(Boolean.TRUE);

        list2.add(Boolean.TRUE);
        list2.add(Boolean.FALSE);
        list2.add(Boolean.TRUE);

        assertEquals(100, matchPercentage(list1,list2));
        assertEquals(100, matchPercentage(list2,list1));
        assertEquals(100, matchPercentage(list1, list1));
        assertEquals(100, matchPercentage(list2,list2));
    }

    public void testHalfMatch()
    {
        list1.add(Boolean.TRUE);
        list1.add(Boolean.TRUE);
        list1.add(Boolean.TRUE);
        list1.add(Boolean.TRUE);

        list2.add(Boolean.TRUE);
        list2.add(Boolean.FALSE);
        list2.add(Boolean.TRUE);
        list2.add(Boolean.FALSE);

        assertEquals(50, matchPercentage(list1,list2));
        assertEquals(50, matchPercentage(list2,list1));
    }

    public void testQuarterMatch()
    {
        list1.add(Boolean.TRUE);
        list1.add(Boolean.TRUE);
        list1.add(Boolean.TRUE);
        list1.add(Boolean.TRUE);

        list2.add(Boolean.FALSE);
        list2.add(Boolean.FALSE);
        list2.add(Boolean.TRUE);
        list2.add(Boolean.FALSE);

        assertEquals(25, matchPercentage(list1,list2));
        assertEquals(25, matchPercentage(list2,list1));
    }

    public void testDiffLengths()
    {
        list1.add(Boolean.TRUE);
        list1.add(Boolean.FALSE);
        list1.add(Boolean.FALSE);
        list1.add(Boolean.TRUE);

        list2.add(Boolean.FALSE);
        list2.add(Boolean.FALSE);


        assertEquals(25, matchPercentage(list1,list2));
        assertEquals(25, matchPercentage(list2,list1));
    }
}
