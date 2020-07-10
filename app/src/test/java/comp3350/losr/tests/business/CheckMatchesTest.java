package comp3350.losr.tests.business;

import junit.framework.TestCase;

import java.util.ArrayList;

import static comp3350.losr.business.CheckMatches.matchPercentage;

//all null tests will throw exceptions because the answers should never be null
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
        System.out.println("Starting testOneEmpty");

        list1.add(Boolean.FALSE);
        assertEquals(0, matchPercentage(list1, list2));

        list1.clear();

        list2.add(Boolean.TRUE);
        assertEquals(0, matchPercentage(list1, list2));

        System.out.println("testOneEmpty complete");
    }

    public void testOneMatch()
    {
        list1.add(Boolean.FALSE);
        list2.add(Boolean.FALSE);

        assertEquals(100, matchPercentage(list1, list2));
    }

    public void testOneNotMatch()
    {
        list1.add(Boolean.TRUE);
        list2.add(Boolean.FALSE);

        assertEquals(0, matchPercentage(list1, list2));
    }

    public void testNull()
    {
        System.out.println("Starting testNull");

        list1 = null;
        list2 = null;

        try
        {
            matchPercentage(list1, list2);
            fail();
        }
        catch(NullPointerException npe){System.out.println("null pointer");}

        System.out.println("testNull Complete");
    }

    public void testOneNull()
    {
        System.out.println("Starting testOneNull");

        list1 = null;
        list2.add(Boolean.TRUE);

        try
        {
            matchPercentage(list1, list2);
            fail();
        }
        catch(NullPointerException npe){System.out.println("null pointer");}

        try
        {
            matchPercentage(list2, list1);
            fail();
        }
        catch(NullPointerException npe){System.out.println("null pointer");}

        System.out.println("testOneNull complete");
    }

    public void testNullItems()
    {
        System.out.println("Starting testNullItems");

        list1.add(null);
        list2.add(null);

        try
        {
            matchPercentage(list1,list2);
            fail();
        }
        catch(NullPointerException npe){System.out.println("null pointer");}

        try
        {
            matchPercentage(list2,list1);
            fail();
        }
        catch(NullPointerException npe){System.out.println("null pointer");}

        System.out.println("testNullItems complete");
    }

    public void testFullMatch()
    {
        System.out.println("Starting testFullMatch");

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

        System.out.println("testFullMatch complete");
    }

    public void testHalfMatch()
    {
        System.out.println("Starting testHalfMatch");

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

        System.out.println("testHalfMatch complete");
    }

    public void testQuarterMatch()
    {
        System.out.println("Starting testQuarterMatch");

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

        System.out.println("testQuarterMatch complete");
    }

    public void testDiffLengths()
    {
        System.out.println("Starting testDiffLengths");

        list1.add(Boolean.TRUE);
        list1.add(Boolean.FALSE);
        list1.add(Boolean.FALSE);
        list1.add(Boolean.TRUE);

        list2.add(Boolean.FALSE);
        list2.add(Boolean.FALSE);


        assertEquals(25, matchPercentage(list1,list2));
        assertEquals(25, matchPercentage(list2,list1));

        System.out.println("testDiffLengths complete");
    }

    public void testMissingAnswers()
    {
        System.out.println("Starting testMissingAnswers");

        list1.add(Boolean.FALSE);
        list1.add(Boolean.FALSE);
        list1.add(null);
        list1.add(Boolean.TRUE);

        list2.add(Boolean.TRUE);
        list2.add(Boolean.FALSE);
        list1.add(null);
        list2.add(Boolean.TRUE);

        try
        {
            matchPercentage(list1,list2);
        }
        catch(NullPointerException npe)
        {
            System.out.println("null pointer");
        }

        System.out.println("testMissingAnswers complete");
    }

    public void testBigList()
    {
        System.out.println("Starting testBigList");

        list1.add(Boolean.FALSE);
        list1.add(Boolean.TRUE);
        list1.add(Boolean.TRUE);
        list1.add(Boolean.TRUE);
        list1.add(Boolean.FALSE);
        list1.add(Boolean.FALSE);
        list1.add(Boolean.TRUE);
        list1.add(Boolean.FALSE);
        list1.add(Boolean.TRUE);
        list1.add(Boolean.FALSE);
        list1.add(Boolean.TRUE);
        list1.add(Boolean.TRUE);

        list2.add(Boolean.TRUE);
        list2.add(Boolean.TRUE);
        list2.add(Boolean.TRUE);
        list2.add(Boolean.FALSE);
        list2.add(Boolean.TRUE);
        list2.add(Boolean.TRUE);
        list2.add(Boolean.TRUE);
        list2.add(Boolean.FALSE);
        list2.add(Boolean.TRUE);
        list2.add(Boolean.FALSE);
        list2.add(Boolean.TRUE);
        list2.add(Boolean.TRUE);

        assertEquals(66, matchPercentage(list1,list2));

        System.out.println("testBigList completed");
    }
}
