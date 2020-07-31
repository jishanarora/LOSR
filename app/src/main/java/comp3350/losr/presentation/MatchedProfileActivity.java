package comp3350.losr.presentation;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.losr.R;
import comp3350.losr.business.AccessMatches;
import comp3350.losr.business.AccessUsers;
import comp3350.losr.objects.Match;
import comp3350.losr.objects.Question;
import comp3350.losr.objects.User;

public class MatchedProfileActivity extends AppCompatActivity {

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matched_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("You got a Match");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Intent intent = new Intent(getIntent());
        int value = intent.getIntExtra("value", -1);


        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Intent intent= new Intent(MatchedProfileActivity.this,NavigationPageActivity.class);
                intent.putExtra("value",2);
                startActivity(intent);
            }
        });

        AccessMatches matchesAccess = new AccessMatches();
        ArrayList<Match> matchList = (ArrayList<Match>) matchesAccess.getMatches();
        User matchedProfile=matchList.get(value).getMatchedUser();
        final ArrayList<Question> userAnswers=matchedProfile.getUserProfile().getAnswers();

        name=findViewById(R.id.matched_profile_name);
        name.setText(matchedProfile.getUserFirstName() + " " + matchedProfile.getUserLastName());

        email = findViewById(R.id.matched_profile_email);
        email.setText(matchedProfile.getUserEmail());

        gender=findViewById(R.id.matched_profile_gender);
        gender.setText(matchedProfile.getUserProfile().getGender().toString());

        genderPreference=findViewById(R.id.matched_profile_gender_preference);
        genderPreference.setText(matchedProfile.getUserProfile().getGenderPreference().toString());

        bio=findViewById(R.id.matched_profile_bio);
        bio.setText(matchedProfile.getUserProfile().getBio());

        dob= findViewById(R.id.matched_profile_date_of_birth);
        dob.setText(matchedProfile.getUserProfile().dateOfBirth());

        answer1=findViewById(R.id.matched_profile_answer1);
        if (userAnswers.get(0).getAnswer() == true) {
            answer1.setText("Yes");
        } else {
            answer1.setText("No");
        }
        answer2=findViewById(R.id.matched_profile_answer2);
        if (userAnswers.get(1).getAnswer() == true) {
            answer2.setText("Yes");
        } else {
            answer2.setText("No");
        }
        answer3=findViewById(R.id.matched_profile_answer3);
        if (userAnswers.get(2).getAnswer() == true) {
            answer3.setText("Yes");
        } else {
            answer3.setText("No");
        }
        answer4=findViewById(R.id.matched_profile_answer4);
        if (userAnswers.get(3).getAnswer() == true) {
            answer4.setText("Yes");
        } else {
            answer4.setText("No");
        }
        answer5=findViewById(R.id.matched_profile_answer5);
        if (userAnswers.get(4).getAnswer() == true) {
            answer5.setText("Yes");
        } else {
            answer5.setText("No");
        }

        weight1=findViewById(R.id.matched_profile_weight1);
        weight1.setText(Integer.toString(userAnswers.get(0).getWeight()));

        weight2=findViewById(R.id.matched_profile_weight2);
        weight2.setText(Integer.toString(userAnswers.get(1).getWeight()));

        weight3=findViewById(R.id.matched_profile_weight3);
        weight3.setText(Integer.toString(userAnswers.get(2).getWeight()));

        weight4=findViewById(R.id.matched_profile_weight4);
        weight4.setText(Integer.toString(userAnswers.get(3).getWeight()));

        weight5=findViewById(R.id.matched_profile_weight5);
        weight5.setText(Integer.toString(userAnswers.get(4).getWeight()));

    }
}