package comp3350.losr.business;


import java.util.ArrayList;
import java.util.List;

import comp3350.losr.application.DatabaseService;
import comp3350.losr.application.Main;
import comp3350.losr.objects.Match;
import comp3350.losr.objects.User;
import comp3350.losr.persistence.DataAccess;

import static comp3350.losr.business.CheckMatches.matchPercentage;

public class AccessMatches
{

    private DataAccess dataAccess;
    private AccessUsers userAccess;

    public AccessMatches()
    {
        dataAccess = DatabaseService.getDataAccess(Main.dbName);
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
                allMatches.add(position(allMatches, matchCheck), new Match(currentUser, potentialMatches.get(i)));
            }

        }
        return allMatches;
    }

    private int position(List<Match> matches, float target)
    {
        int left = 0;
        int right = matches.size();

        while (left < right)
        {
            int mid = (right + left) / 2;
            if (matches.get(mid).getMatchPercent() == target)
                right = mid;
            else if (matches.get(mid).getMatchPercent() > target)
                left = mid + 1;
            else if (matches.get(mid).getMatchPercent() < target)
                right = mid;
        }
        return left;
    }

}
