package comp3350.losr.objects;

import static comp3350.losr.business.CheckMatches.matchPercentage;

//simple object to hold two matched users and how good their compatibility is.
public class Match {
    private User currentUser;
    private User matchedUser;
    private int matchPercent;

    public Match(User currentUser, User matchedUser)
    {
        this.currentUser = currentUser;
        this.matchedUser = matchedUser;
        matchPercent = matchPercentage(currentUser.getAnswers(), matchedUser.getAnswers());
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
