package comp3350.losr.tests.objects;

import junit.framework.TestCase;

import comp3350.losr.objects.Report;

public class ReportTest extends TestCase {

    public void testCreate() {
        System.out.println("Starting testCreate");
        assertNotNull(new Report("",""));
        System.out.println("testCreate complete");
    }

    public void testStandard() {
        System.out.println("Starting testStandard");
        Report report = new Report("firstemail", "secondemail");

        assertEquals("firstemail", report.getReporter());
        assertEquals("secondemail", report.getReportee());

        System.out.println("testStandard complete");
    }
}
