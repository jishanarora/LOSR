package comp3350.losr.tests.business;

import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.losr.objects.Question;

import static comp3350.losr.business.CheckMatches.matchPercentage;

//all null tests will throw exceptions because the answers should never be null
//all answers lists are of size 5 and start with all false values
public class CheckMatchesTest extends TestCase
{
    private ArrayList<Question> list1;
    private ArrayList<Question> list2;

    private int numQuestions = 5;

    public void setUp()
    {
        list1 = new ArrayList<>(numQuestions);
        list2 = new ArrayList<>(numQuestions);
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

        list2.add(new Question(Boolean.FALSE));
        list2.add(new Question(Boolean.FALSE));
        list2.add(new Question(Boolean.FALSE));
        list2.add(new Question(Boolean.FALSE));
        list2.add(new Question(Boolean.FALSE));

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
        list1.add(null);
        list1.add(null);
        list1.add(null);
        list1.add(null);

        list2.add(null);
        list2.add(null);
        list2.add(null);
        list2.add(null);
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

        list1.add(new Question(Boolean.TRUE));
        list1.add(new Question(Boolean.FALSE));
        list1.add(new Question(Boolean.TRUE));
        list1.add(new Question(Boolean.TRUE));
        list1.add(new Question(Boolean.TRUE));

        list2.add(new Question(Boolean.TRUE));
        list2.add(new Question(Boolean.FALSE));
        list2.add(new Question(Boolean.TRUE));
        list2.add(new Question(Boolean.TRUE));
        list2.add(new Question(Boolean.TRUE));

        assertEquals(100, matchPercentage(list1,list2));
        assertEquals(100, matchPercentage(list2,list1));
        assertEquals(100, matchPercentage(list1, list1));
        assertEquals(100, matchPercentage(list2,list2));

        System.out.println("testFullMatch complete");
    }

    public void test20Match()
    {
        System.out.println("Starting test20Match");

        list1.add(new Question(Boolean.TRUE));
        list1.add(new Question(Boolean.TRUE));
        list1.add(new Question(Boolean.TRUE));
        list1.add(new Question(Boolean.TRUE));
        list1.add(new Question(Boolean.TRUE));

        list2.add(new Question(Boolean.TRUE));
        list2.add(new Question(Boolean.FALSE));
        list2.add(new Question(Boolean.FALSE));
        list2.add(new Question(Boolean.FALSE));
        list2.add(new Question(Boolean.FALSE));

        assertEquals(20, matchPercentage(list1,list2));
        assertEquals(20, matchPercentage(list2,list1));

        System.out.println("test20Match complete");
    }

    public void test40Match()
    {
        System.out.println("Starting test40Match");

        list1.add(new Question(Boolean.FALSE));
        list1.add(new Question(Boolean.FALSE));
        list1.add(new Question(Boolean.FALSE));
        list1.add(new Question(Boolean.FALSE));
        list1.add(new Question(Boolean.TRUE));

        list2.add(new Question(Boolean.FALSE));
        list2.add(new Question(Boolean.FALSE));
        list2.add(new Question(Boolean.TRUE));
        list2.add(new Question(Boolean.TRUE));
        list2.add(new Question(Boolean.FALSE));

        assertEquals(40, matchPercentage(list1,list2));
        assertEquals(40, matchPercentage(list2,list1));

        System.out.println("test40Match complete");
    }

    public void test60Match()
    {
        System.out.println("Starting test60Match");

        list1.add(new Question(Boolean.FALSE));
        list1.add(new Question(Boolean.FALSE));
        list1.add(new Question(Boolean.FALSE));
        list1.add(new Question(Boolean.FALSE));
        list1.add(new Question(Boolean.TRUE));

        list2.add(new Question(Boolean.FALSE));
        list2.add(new Question(Boolean.FALSE));
        list2.add(new Question(Boolean.FALSE));
        list2.add(new Question(Boolean.TRUE));
        list2.add(new Question(Boolean.FALSE));

        assertEquals(60, matchPercentage(list1,list2));
        assertEquals(60, matchPercentage(list2,list1));

        System.out.println("test60Match complete");
    }

    public void test80Match()
    {
        System.out.println("Starting test80Match");

        list1.add(new Question(Boolean.FALSE));
        list1.add(new Question(Boolean.FALSE));
        list1.add(new Question(Boolean.FALSE));
        list1.add(new Question(Boolean.FALSE));
        list1.add(new Question(Boolean.TRUE));

        list2.add(new Question(Boolean.FALSE));
        list2.add(new Question(Boolean.FALSE));
        list2.add(new Question(Boolean.FALSE));
        list2.add(new Question(Boolean.FALSE));
        list2.add(new Question(Boolean.FALSE));

        assertEquals(80, matchPercentage(list1,list2));
        assertEquals(80, matchPercentage(list2,list1));

        System.out.println("test80Match complete");
    }

    public void testMissingAnswers()
    {
        System.out.println("Starting testMissingAnswers");

        list1.add(new Question(Boolean.FALSE));
        list1.add(new Question(Boolean.FALSE));
        list1.add(null);
        list1.add(new Question(Boolean.TRUE));
        list1.add(new Question(Boolean.TRUE));

        list2.add(new Question(Boolean.TRUE));
        list2.add(new Question(Boolean.FALSE));
        list1.add(null);
        list2.add(new Question(Boolean.TRUE));
        list1.add(new Question(Boolean.TRUE));

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

    public void testAverageList()
    {
        System.out.println("Starting testAverageList");

        list1.add(new Question(Boolean.FALSE));
        list1.add(new Question(Boolean.TRUE));
        list1.add(new Question(Boolean.TRUE));
        list1.add(new Question(Boolean.TRUE));
        list1.add(new Question(Boolean.FALSE));

        list2.add(new Question(Boolean.TRUE));
        list2.add(new Question(Boolean.TRUE));
        list2.add(new Question(Boolean.TRUE));
        list2.add(new Question(Boolean.FALSE));
        list2.add(new Question(Boolean.TRUE));

        assertEquals(40, matchPercentage(list1,list2));

        System.out.println("testAverageList completed");
    }


    public void testMaxWeightMatch()
    {
        System.out.println("Starting testMaxWeightMatch");

        list1.add(new Question(Boolean.TRUE,5));
        list1.add(new Question(Boolean.TRUE,5));
        list1.add(new Question(Boolean.TRUE,5));
        list1.add(new Question(Boolean.TRUE,5));
        list1.add(new Question(Boolean.TRUE,5));

        list2.add(new Question(Boolean.TRUE,5));
        list2.add(new Question(Boolean.TRUE,5));
        list2.add(new Question(Boolean.TRUE,5));
        list2.add(new Question(Boolean.TRUE,5));
        list2.add(new Question(Boolean.TRUE,5));

        assertEquals(100, matchPercentage(list1,list2));

        System.out.println("testMaxWeightMatch completed");
    }

    public void testSameWeightMatch()
    {
        System.out.println("Starting testSameWeightMatch");

        list1.add(new Question(Boolean.TRUE,5));
        list1.add(new Question(Boolean.FALSE,4));
        list1.add(new Question(Boolean.TRUE,4));
        list1.add(new Question(Boolean.TRUE,3));
        list1.add(new Question(Boolean.TRUE,2));

        list2.add(new Question(Boolean.TRUE,5));
        list2.add(new Question(Boolean.FALSE,4));
        list2.add(new Question(Boolean.TRUE,4));
        list2.add(new Question(Boolean.TRUE,3));
        list2.add(new Question(Boolean.TRUE,2));

        assertEquals(100, matchPercentage(list1,list2));

        System.out.println("testSameWeightMatch completed " + matchPercentage(list1,list2));
    }

    public void testMinWeightMatch()
    {
        System.out.println("Starting testMinWeightMatch");

        list1.add(new Question(Boolean.TRUE,5));
        list1.add(new Question(Boolean.TRUE,5));
        list1.add(new Question(Boolean.TRUE,5));
        list1.add(new Question(Boolean.TRUE,5));
        list1.add(new Question(Boolean.TRUE,5));

        list2.add(new Question(Boolean.TRUE,1));
        list2.add(new Question(Boolean.TRUE,1));
        list2.add(new Question(Boolean.TRUE,1));
        list2.add(new Question(Boolean.TRUE,1));
        list2.add(new Question(Boolean.TRUE,1));

        assertEquals(20, matchPercentage(list1,list2));

        System.out.println("testMinWeightMatch completed "+ matchPercentage(list1,list2));
    }

    public void test24WeightMatch()
    {
        System.out.println("Starting test24WeightMatch");

        list1.add(new Question(Boolean.FALSE,5));
        list1.add(new Question(Boolean.TRUE,4));
        list1.add(new Question(Boolean.TRUE,2));
        list1.add(new Question(Boolean.FALSE,1));
        list1.add(new Question(Boolean.TRUE,5));

        list2.add(new Question(Boolean.TRUE,5));
        list2.add(new Question(Boolean.TRUE,2));
        list2.add(new Question(Boolean.TRUE,5));
        list2.add(new Question(Boolean.TRUE,3));
        list2.add(new Question(Boolean.TRUE,1));

        assertEquals(24, matchPercentage(list1,list2));

        System.out.println("testMinWeightMatch completed " + matchPercentage(list1,list2));
    }

    public void test16WeightMatch()
    {
        System.out.println("Starting test16WeightMatch");

        list1.add(new Question(Boolean.FALSE,5));
        list1.add(new Question(Boolean.FALSE,4));
        list1.add(new Question(Boolean.TRUE,4));
        list1.add(new Question(Boolean.FALSE,3));
        list1.add(new Question(Boolean.TRUE,5));

        list2.add(new Question(Boolean.TRUE,5));
        list2.add(new Question(Boolean.TRUE,5));
        list2.add(new Question(Boolean.FALSE,2));
        list2.add(new Question(Boolean.FALSE,1));
        list2.add(new Question(Boolean.TRUE,1));

        assertEquals(16, matchPercentage(list1,list2));

        System.out.println("test16WeightMatch completed " + matchPercentage(list1,list2));
    }

    public void test56WeightMatch()
    {
        System.out.println("Starting test56WeightMatch");

        list1.add(new Question(Boolean.TRUE,5));
        list1.add(new Question(Boolean.TRUE,4));
        list1.add(new Question(Boolean.TRUE,2));
        list1.add(new Question(Boolean.TRUE,1));
        list1.add(new Question(Boolean.TRUE,5));

        list2.add(new Question(Boolean.TRUE,5));
        list2.add(new Question(Boolean.TRUE,2));
        list2.add(new Question(Boolean.TRUE,5));
        list2.add(new Question(Boolean.TRUE,3));
        list2.add(new Question(Boolean.TRUE,1));

        assertEquals(56, matchPercentage(list1,list2));

        System.out.println("test56WeightMatch completed " + matchPercentage(list1,list2));
    }

    public void test72WeightMatch()
    {
        System.out.println("Starting test72WeightMatch");

        list1.add(new Question(Boolean.TRUE,5));
        list1.add(new Question(Boolean.TRUE,4));
        list1.add(new Question(Boolean.TRUE,2));
        list1.add(new Question(Boolean.TRUE,2));
        list1.add(new Question(Boolean.TRUE,5));

        list2.add(new Question(Boolean.TRUE,5));
        list2.add(new Question(Boolean.TRUE,5));
        list2.add(new Question(Boolean.TRUE,5));
        list2.add(new Question(Boolean.TRUE,3));
        list2.add(new Question(Boolean.TRUE,3));

        assertEquals(72, matchPercentage(list1,list2));

        System.out.println("test72WeightMatch completed " + matchPercentage(list1,list2));
    }

    public void testOppositeWeightMatch()
    {
        System.out.println("Starting testOppositeWeightMatch");

        list1.add(new Question(Boolean.TRUE,5));
        list1.add(new Question(Boolean.TRUE,5));
        list1.add(new Question(Boolean.TRUE,5));
        list1.add(new Question(Boolean.TRUE,5));
        list1.add(new Question(Boolean.TRUE,5));

        list2.add(new Question(Boolean.FALSE,5));
        list2.add(new Question(Boolean.FALSE,4));
        list2.add(new Question(Boolean.FALSE,3));
        list2.add(new Question(Boolean.FALSE,2));
        list2.add(new Question(Boolean.FALSE,1));

        assertEquals(0, matchPercentage(list1,list2));

        System.out.println("testOppositeWeightMatch completed");
    }


}
