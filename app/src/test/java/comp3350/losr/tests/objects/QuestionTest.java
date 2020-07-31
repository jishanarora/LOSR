package comp3350.losr.tests.objects;

import junit.framework.TestCase;

import comp3350.losr.objects.Question;

public class QuestionTest extends TestCase {
    Question q;

    public void testCreation() {
        System.out.println("Starting testCreation");

        q = new Question(Boolean.FALSE);

        assertNotNull(q);
        assertEquals(0, q.getqNum());
        assertEquals("test", q.getQuestion());
        assertEquals(Boolean.FALSE, q.getAnswer());
        assertEquals(2, q.getWeight());

        System.out.println("testCreation complete");
    }

    public void testStandard() {
        System.out.println("Starting testStandard");

        q = new Question(1, "Q1", Boolean.FALSE, 2);

        assertNotNull(q);
        assertEquals(1, q.getqNum());
        assertEquals("Q1", q.getQuestion());
        assertEquals(Boolean.FALSE, q.getAnswer());
        assertEquals(2, q.getWeight());

        System.out.println("testStandard complete");
    }

    public void testEquals() {
        System.out.println("Starting testEquals");

        q = new Question(1, "Q1", Boolean.FALSE, 2);
        assertTrue(q.equals(new Question(1, "Q1", Boolean.FALSE, 2)));

        System.out.println("testEquals complete");
    }

    public void testNotEquals() {
        System.out.println("Starting testNotEquals");

        q = new Question(1, "Q1", Boolean.FALSE, 2);
        Question q2 = new Question(2, "Q1", Boolean.FALSE, 2);

        assertFalse(q.equals(q2));

        q2 = new Question(1, "Q2", Boolean.FALSE, 2);
        assertFalse(q.equals(q2));

        q2 = new Question(1, "Q1", Boolean.TRUE, 2);
        assertFalse(q.equals(q2));

        q2 = new Question(1, "Q1", Boolean.FALSE, 3);
        assertFalse(q.equals(q2));

        q2 = new Question(2, "Q2", Boolean.TRUE, 1);
        assertFalse(q.equals(q2));

        System.out.println("testNotEquals complete");
    }
}
