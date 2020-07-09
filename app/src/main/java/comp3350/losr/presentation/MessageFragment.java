package comp3350.losr.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import comp3350.losr.R;


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
        ArrayList<String> matchItems = getMatchesList();
        matchesListView = (ListView)rootView.findViewById(R.id.matchesListView);
        matchesListView.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.matches_listview_detail, matchItems));


        return rootView;
    }

    private ArrayList<String> getMatchesList(){
//        AccessMatches matchesAccess = new AccessMatches();

//        ArrayList<Match> matchList = (ArrayList<Match>) matchesAccess.getMatches();
//        ArrayList<ListviewContactItem> contactlist = new ArrayList<ListviewContactItem>();
        ArrayList<String> matches = new ArrayList<String>();

        String match = new String();

        match = "Guy 1";
        matches.add(match);

        match = new String();
        match = "Guy 2";
        matches.add(match);

        match = new String();
        match = "Guy 3";
        matches.add(match);

        return matches;
    }

}