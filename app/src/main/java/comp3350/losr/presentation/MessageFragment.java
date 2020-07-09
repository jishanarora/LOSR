package comp3350.losr.presentation;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import comp3350.losr.R;
import comp3350.losr.business.AccessMatches;
import comp3350.losr.objects.Match;


public class MessageFragment extends Fragment {

    public MessageFragment() {
        // Required empty public constructor
    }

    public static MessageFragment newInstance() {

        Bundle args = new Bundle();

        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MessageFragment newInstance(String param1, String param2) {
        MessageFragment fragment = new MessageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private ListView matchesListView;
//    private String[] matches;
//    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_message, container,false);

        AccessMatches matchesAccess = new AccessMatches();
        ArrayList<Match> matchList = (ArrayList<Match>) matchesAccess.getMatches();
        MatchesAdapter matchAdapter = new MatchesAdapter(getContext(), matchList);

        matchesListView = (ListView)rootView.findViewById(R.id.matchesListView);
        matchesListView.setAdapter(matchAdapter);

        return rootView;
    }

//    private ArrayList<String> getMatchesList(){
//
//        AccessMatches matchesAccess = new AccessMatches();
//        ArrayList<Match> matchList = (ArrayList<Match>) matchesAccess.getMatches();
//        MatchesAdapter matchAdapter = new MatchesAdapter(this, matchList);
//
//        return matches;
//    }

    public class MatchesAdapter extends ArrayAdapter<Match>{
        public MatchesAdapter(Context context, ArrayList<Match> matches){
            super(context, 0, matches);
        }

        public View getView(int position, View convertView, ViewGroup parent){

            Match currMatch = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.matches_listview_detail, parent, false);
            }

            TextView matchName = (TextView) convertView.findViewById(R.id.matchName);
            TextView matchPercent = (TextView) convertView.findViewById(R.id.matchPercent);

            matchName.setText(String.format("%s %s", currMatch.getMatchedUser().getUserFirstName(), currMatch.getMatchedUser().getUserLastName()));
            matchPercent.setText(Integer.toString(currMatch.getMatchPercent()) + "% Match");
           
            return convertView;
        }


    }

}