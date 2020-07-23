package comp3350.losr.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import comp3350.losr.R;
import comp3350.losr.application.Main;
import comp3350.losr.business.AccessUsers;
import comp3350.losr.persistence.DataAccessObject;
import comp3350.losr.persistence.DataAccessStub;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    private GridView gridView;
    private ImageView imageView;
    private DataAccessStub dataAccessStub;


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ProfileFragment fragment = new ProfileFragment();
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
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        AccessUsers accessUsers= new AccessUsers();
        View view=inflater.inflate(R.layout.fragment_profile, container, false);

        TextView name=view.findViewById(R.id.profile_name);
        name.setText(accessUsers.getCurrentUser().getUserFirstName() + " " + accessUsers.getCurrentUser().getUserLastName());

        TextView email = view.findViewById(R.id.profile_email);
        email.setText(accessUsers.getCurrentUser().getUserEmail());

        TextView gender= view.findViewById(R.id.profile_gender);
        gender.setText(accessUsers.getCurrentUser().getUserProfile().getGender().toString());

        TextView genderPreference= view.findViewById(R.id.profile_gender_preference);
        genderPreference.setText(accessUsers.getCurrentUser().getUserProfile().getGenderPreference().toString());

        TextView bio= view.findViewById(R.id.profile_bio);
        bio.setText(accessUsers.getCurrentUser().getUserProfile().getBio());

        TextView dob= view.findViewById(R.id.profile_date_of_birth);
        dob.setText(accessUsers.getCurrentUser().getUserProfile().dateOfBirth());

        DataAccessObject test = new DataAccessObject("Users");
        test.openConnection(Main.dbPathName);
        test.getGenderedUsers();

        return view;
    }

}