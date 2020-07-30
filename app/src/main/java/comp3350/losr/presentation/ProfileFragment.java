package comp3350.losr.presentation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import comp3350.losr.R;
import comp3350.losr.business.AccessUsers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment
{
    TextView name;
    TextView email;
    TextView gender;
    TextView genderPreference;
    TextView bio;
    TextView dob;
    ImageView settings;

    public ProfileFragment()
    {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance()
    {
        
        Bundle args = new Bundle();
        
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        AccessUsers accessUsers= new AccessUsers();
        View view=inflater.inflate(R.layout.fragment_profile, container, false);

        name=view.findViewById(R.id.profile_name);
        name.setText(accessUsers.getCurrentUser().getUserFirstName() + " " + accessUsers.getCurrentUser().getUserLastName());

        email = view.findViewById(R.id.profile_email);
        email.setText(accessUsers.getCurrentUser().getUserEmail());

        gender= view.findViewById(R.id.profile_gender);
        gender.setText(accessUsers.getCurrentUser().getUserProfile().getGender().toString());

        genderPreference= view.findViewById(R.id.profile_gender_preference);
        genderPreference.setText(accessUsers.getCurrentUser().getUserProfile().getGenderPreference().toString());

        bio= view.findViewById(R.id.profile_bio);
        bio.setText(accessUsers.getCurrentUser().getUserProfile().getBio());

        dob= view.findViewById(R.id.profile_date_of_birth);
        dob.setText(accessUsers.getCurrentUser().getUserProfile().dateOfBirth());

        settings= view.findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent EditIntent = new Intent(getActivity(), EditProfile.class);
                startActivity(EditIntent);
                getActivity().finish();
            }
        });



        return view;
    }

}