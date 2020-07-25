package comp3350.losr.business;

import java.util.ArrayList;

import comp3350.losr.objects.User;

public class CheckMatches
{

    // checks gender compatibility and % of same answers to return an int match % number
    public static int matchPercentage(ArrayList<Boolean> curr, ArrayList<Boolean> match)
    {
        float overall_compatibility = 0;

        int similar = 0;

        //bigger and smaller are used to track the lists if one has more answers than the other.
        ArrayList<Boolean> bigger = curr;
        ArrayList<Boolean> smaller = match;

        if(match.size() > curr.size())
        {
            bigger = match;
            smaller =  curr;
        }

        int difference = bigger.size() - smaller.size();

        if(bigger.size() > 0 && smaller.size() > 0) {
            for (int i = 0; i < smaller.size(); i++) {
                if (bigger.get(i).equals(smaller.get(i))) {
                    similar++;
                }
            }
            overall_compatibility = ((float)similar/((float)smaller.size()+difference))*100;
        }
        return (int)overall_compatibility; // should this even be null?
    }

    // compares genders and gender preference to ensure compatibility
    public static boolean areGenderCompatible(User curr, User match)
    {
        return curr.getUserProfile().getGenderPreference() == match.getUserProfile().getGender() &&
                curr.getUserProfile().getGender() == match.getUserProfile().getGenderPreference();
    }

}
