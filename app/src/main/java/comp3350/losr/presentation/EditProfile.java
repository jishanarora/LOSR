package comp3350.losr.presentation;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import comp3350.losr.R;


public class EditProfile extends AppCompatActivity {

    private Spinner answer1;
    private Spinner answer2;
    private Spinner answer3;
    private Spinner answer4;
    private Spinner answer5;
    private Spinner gender1;
    private Spinner gender2;
    private String[] answers = new String[]{"True", "False"};
    private String[] gender = new String[]{"Male", "Female"};
    private Button cancel;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(EditProfile.this,NavigationPageActivity.class);
                intent.putExtra("value",1);
                startActivity(intent);
            }
        });
        answer1= findViewById(R.id.spinner1);
        answer2= findViewById(R.id.spinner2);
        answer3= findViewById(R.id.spinner3);
        answer4= findViewById(R.id.spinner4);
        answer5= findViewById(R.id.spinner5);
        gender1= findViewById(R.id.spinner6);
        gender2= findViewById(R.id.spinner7);
        cancel=findViewById(R.id.edit_profile_cancel);
        save=findViewById(R.id.edit_profile_save);
        ArrayAdapter<String> answersAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, answers);
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, gender);
        answer1.setAdapter(answersAdapter);
        answer2.setAdapter(answersAdapter);
        answer3.setAdapter(answersAdapter);
        answer4.setAdapter(answersAdapter);
        answer5.setAdapter(answersAdapter);
        gender1.setAdapter(genderAdapter);
        gender2.setAdapter(genderAdapter);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(EditProfile.this,NavigationPageActivity.class);
                intent.putExtra("value",1);
                startActivity(intent);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(EditProfile.this,NavigationPageActivity.class);
                intent.putExtra("value",1);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent= new Intent(EditProfile.this,NavigationPageActivity.class);
        intent.putExtra("value",1);
        startActivity(intent);
    }
}