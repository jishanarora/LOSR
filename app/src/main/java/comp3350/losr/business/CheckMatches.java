package comp3350.losr.business;

import java.util.ArrayList;

import comp3350.losr.objects.Question;
import comp3350.losr.objects.User;

public class CheckMatches
{

    // checks gender compatibility and % of same answers to return an int match % number
    public static int matchPercentage(ArrayList<Question> curr, ArrayList<Question> match)
    {
        //answers lists will always be of size 5 and will always have each spot initialized
        //(false if its a fresh account)

        int numQuestions = curr.size();
        float overall_compatibility;
        int similar = 0;
        int ansMatchVal;
        int diff;

        for (int i = 0; i < numQuestions; i++)
        {
            if (curr.get(i).getAnswer().equals(match.get(i).getAnswer()))
            {
                ansMatchVal = 0;
                // get the absolute difference between two weights
                diff = Math.abs(curr.get(i).getWeight() - match.get(i).getWeight());

                // "flip" that val to get a number that's higher for higher similarity and vice versa
                ansMatchVal = 5-diff;
                similar+= ansMatchVal;
            }
        }
        overall_compatibility = ((float)similar/(numQuestions*5))*100;

        return (int)overall_compatibility; // should this even be null?
    }

    // compares genders and gender preference to ensure compatibility
    public static boolean areGenderCompatible(User curr, User match)
    {
        return curr.getUserProfile().getGenderPreference() == match.getUserProfile().getGender() &&
                curr.getUserProfile().getGender() == match.getUserProfile().getGenderPreference();
    }

}
