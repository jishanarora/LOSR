package comp3350.losr.business;


import java.util.ArrayList;
import java.util.List;

import comp3350.losr.application.DatabaseService;
import comp3350.losr.application.Main;
import comp3350.losr.objects.Match;
import comp3350.losr.objects.User;
import comp3350.losr.persistence.DataAccessObject;

import static comp3350.losr.business.CheckMatches.matchPercentage;

public class AccessMatches {

    private DataAccessObject dataAccess;
    private AccessUsers userAccess;

    public AccessMatches()
    {
        dataAccess = (DataAccessObject) DatabaseService.getDataAccess(Main.dbName);
        userAccess = new AccessUsers();
    }


    public List<Match> getMatches()
    {
        List<Match> allMatches = new ArrayList<>();
        List<User> potentialMatches = userAccess.getGenderedUsers();
        User currentUser = dataAccess.getCurrentUser();

        float matchCheck;

        for(int i = 0; i < potentialMatches.size(); i++)
        {
            matchCheck = matchPercentage(currentUser.getAnswers(), potentialMatches.get(i).getAnswers());

            if(matchCheck > 0)
            {
                allMatches.add(new Match(currentUser, potentialMatches.get(i)));
            }

        }
        return allMatches;
    }

}
