package comp3350.losr.objects;

public class Report {
    private String reporterEmail;
    private String reporteeEmail;

    public Report(String reporterEmail, String reporteeEmail)
    {
        this.reporterEmail = reporterEmail;
        this.reporteeEmail = reporteeEmail;
    }

    public String getReporter()
    {
        return reporterEmail;
    }

    public String getReporteeEmail()
    {
        return reporteeEmail;
    }
}
