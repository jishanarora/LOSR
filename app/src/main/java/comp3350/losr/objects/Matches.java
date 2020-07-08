package comp3350.losr.objects;

import static comp3350.losr.business.CheckMatches.matchPercentage;

public class Matches {
    private User currentUser;
    private User matchedUser;
    private int matchPercent;

    public Matches(User currentUser, User matchedUser)
    {
        this.currentUser = currentUser;
        this.matchedUser = matchedUser;
        matchPercent = matchPercentage(currentUser, matchedUser);
    }

    public int getMatchPercent()
    {
        return matchPercent;
    }

    public User getCurrentUser()
    {
        return currentUser;
    }

    public User getMatchedUser()
    {
        return matchedUser;
    }
}
