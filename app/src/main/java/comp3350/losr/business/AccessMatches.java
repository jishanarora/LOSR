package comp3350.losr.business;


import java.util.ArrayList;
import java.util.List;

import comp3350.losr.application.DatabaseService;
import comp3350.losr.application.Main;
import comp3350.losr.objects.Matches;
import comp3350.losr.objects.User;
import comp3350.losr.persistence.DataAccessStub;

import static comp3350.losr.business.CheckMatches.areGenderCompatible;
import static comp3350.losr.business.CheckMatches.matchPercentage;

public class AccessMatches {

    private DataAccessStub dataAccess;
    private AccessUsers userAccess;

    public AccessMatches()
    {
        dataAccess = (DataAccessStub) DatabaseService.getDataAccess(Main.dbName);
        userAccess = new AccessUsers();
    }

    public List<Matches> getMatches()
    {
        List<Matches> allMatches = new ArrayList<>();
        List<User> potentialMatches = userAccess.getGenderedUsers();
        User currentUser = dataAccess.getCurrentUser();

        int matchCheck;

        for(int i = 0; i < potentialMatches.size(); i++)
        {
            matchCheck = matchPercentage(currentUser.getAnswers(), potentialMatches.get(i).getAnswers());

            if(matchCheck > 0)
            {
                allMatches.add(new Matches(currentUser, potentialMatches.get(i)));
            }

        }
        return allMatches;
    }

}
