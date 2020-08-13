package comp3350.losr.presentation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import comp3350.losr.R;
import comp3350.losr.business.AccessMatches;
import comp3350.losr.objects.Match;


public class MessageFragment extends Fragment {

    private ListView matchesListView;
    private ImageView profile;
    private View rootView;
    public MessageFragment() {
        // Required empty public constructor
    }

    public static MessageFragment newInstance() {

        Bundle args = new Bundle();

        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static MessageFragment newInstance(String param1, String param2) {
        MessageFragment fragment = new MessageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_message, container, false);



        return rootView;
    }

    public class MatchesAdapter extends ArrayAdapter<Match> {
        public MatchesAdapter(Context context, ArrayList<Match> matches) {
            super(context, 0, matches);
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            Match currMatch = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.matches_listview_detail, parent, false);
                profile = convertView.findViewById(R.id.profileButton);
                File imgFile = new File(currMatch.getMatchedUser().getUserProfile().getProfilePicture()); //this will be grabbed from database

                if(!currMatch.getCurrentUser().getUserProfile().getBlindMode()) {
                    if (imgFile.exists()) {
                        try {
                            FileInputStream fis = new FileInputStream(imgFile);
                            Bitmap myBitmap = BitmapFactory.decodeStream(fis);
                            profile.setImageBitmap(myBitmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        profile.setImageResource(R.mipmap.profile);
                    }
                }
                convertView.setClickable(true);
                profile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), MatchedProfileActivity.class);
                        intent.putExtra("value", position);
                        startActivity(intent);
                    }
                });
            }

            TextView matchName = convertView.findViewById(R.id.matchName);
            TextView matchPercent = convertView.findViewById(R.id.matchPercent);

            matchName.setText(String.format("  %s %s", currMatch.getMatchedUser().getUserFirstName(), currMatch.getMatchedUser().getUserLastName()));
            matchPercent.setText(Integer.toString(currMatch.getMatchPercent()) + "% Match");

            return convertView;
        }


    }

    public void refreshMessageFragment()
    {
        AccessMatches matchesAccess = new AccessMatches();
        ArrayList<Match> matchList = (ArrayList<Match>) matchesAccess.getMatches();
        MatchesAdapter matchAdapter = new MatchesAdapter(getContext(), matchList);

        matchesListView = (ListView) rootView.findViewById(R.id.matchesListView);
        matchesListView.setAdapter(matchAdapter);
    }

}