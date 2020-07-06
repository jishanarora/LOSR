package comp3350.losr.business;

import comp3350.losr.objects.User;

public class CheckMatches {

    // checks gender compatibility and % of same answers to return an int match % number
    public static int match_percentage(User curr, User match){
        int overall_compatibility = 0;
        if (are_gender_compatible(curr, match)){

            int similar = 0;
            for (int i = 0; i < match.getAnswers().size(); i++) {
                if (curr.getAnswers().get(i) == match.getAnswers().get(i)){
                    similar++;
                }
            }
            overall_compatibility = similar/match.getAnswers().size();
        }
        return overall_compatibility; // should this even be null?
    }

    // compares genders and gender preference to ensure compatibility
    public static boolean are_gender_compatible(User curr, User match){



        return curr.getUserProfile().getGenderPreference() == match.getUserProfile().getGender() &&
                curr.getUserProfile().getGender() == match.getUserProfile().getGenderPreference();
    }

}
