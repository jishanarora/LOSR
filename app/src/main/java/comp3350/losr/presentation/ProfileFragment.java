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

import java.util.ArrayList;

import comp3350.losr.R;
import comp3350.losr.business.AccessUsers;
import comp3350.losr.objects.Question;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment
{
    private TextView name;
    private TextView email;
    private TextView gender;
    private TextView genderPreference;
    private TextView bio;
    private TextView dob;
    private ImageView settings;
    private TextView answer1;
    private TextView answer2;
    private TextView answer3;
    private TextView answer4;
    private TextView answer5;
    private TextView weight1;
    private TextView weight2;
    private TextView weight3;
    private TextView weight4;
    private TextView weight5;

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
        final ArrayList<Question> userAnswers=accessUsers.getCurrentUser().getUserProfile().getAnswers();

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

        answer1=view.findViewById(R.id.profile_answer1);
        if (userAnswers.get(0).getAnswer() == true) {
            answer1.setText("Yes");
        } else {
            answer1.setText("No");
        }
        answer2=view.findViewById(R.id.profile_answer2);
        if (userAnswers.get(1).getAnswer() == true) {
            answer2.setText("Yes");
        } else {
            answer2.setText("No");
        }
        answer3=view.findViewById(R.id.profile_answer3);
        if (userAnswers.get(2).getAnswer() == true) {
            answer3.setText("Yes");
        } else {
            answer3.setText("No");
        }
        answer4=view.findViewById(R.id.profile_answer4);
        if (userAnswers.get(3).getAnswer() == true) {
            answer4.setText("Yes");
        } else {
            answer4.setText("No");
        }
        answer5=view.findViewById(R.id.profile_answer5);
        if (userAnswers.get(4).getAnswer() == true) {
            answer5.setText("Yes");
        } else {
            answer5.setText("No");
        }

        weight1=view.findViewById(R.id.profile_weight1);
        weight1.setText(Integer.toString(userAnswers.get(0).getWeight()));

        weight2=view.findViewById(R.id.profile_weight2);
        weight2.setText(Integer.toString(userAnswers.get(1).getWeight()));

        weight3=view.findViewById(R.id.profile_weight3);
        weight3.setText(Integer.toString(userAnswers.get(2).getWeight()));

        weight4=view.findViewById(R.id.profile_weight4);
        weight4.setText(Integer.toString(userAnswers.get(3).getWeight()));

        weight5=view.findViewById(R.id.profile_weight5);
        weight5.setText(Integer.toString(userAnswers.get(4).getWeight()));

        return view;
    }

}