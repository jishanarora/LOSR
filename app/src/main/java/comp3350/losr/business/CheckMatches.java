package comp3350.losr.business;

import java.util.ArrayList;

import comp3350.losr.objects.User;

public class CheckMatches {

    // checks gender compatibility and % of same answers to return an int match % number
    public static int matchPercentage(ArrayList<Boolean> curr, ArrayList<Boolean> match){
        int overall_compatibility;

        int similar = 0;
        for (int i = 0; i < match.size(); i++) {
            if (curr.get(i) == match.get(i)){
                similar++;
            }
        }
        overall_compatibility = similar/match.size();

        return overall_compatibility; // should this even be null?
    }

    // compares genders and gender preference to ensure compatibility
    public static boolean areGenderCompatible(User curr, User match){



        return curr.getUserProfile().getGenderPreference() == match.getUserProfile().getGender() &&
                curr.getUserProfile().getGender() == match.getUserProfile().getGenderPreference();
    }

}
