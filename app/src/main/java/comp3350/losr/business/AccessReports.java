package comp3350.losr.business;

import java.util.List;

import comp3350.losr.application.DatabaseService;
import comp3350.losr.application.Main;
import comp3350.losr.objects.Report;
import comp3350.losr.persistence.DataAccess;

public class AccessReports {
    private DataAccess dataAccess;

    public AccessReports() {
        dataAccess = DatabaseService.getDataAccess(Main.dbName);
    }

    public void report(String reportee)
    {
        dataAccess.report(reportee);
    }

    public List<Report> getReports()
    {
        return dataAccess.getReports();
    }

}
