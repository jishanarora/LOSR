package comp3350.losr.presentation;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import comp3350.losr.R;
import comp3350.losr.business.AccessUsers;
import comp3350.losr.objects.Question;
import comp3350.losr.objects.User;


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
    private Button dateButton;
    private EditText dateText;
    private EditText firstName;
    private EditText lastName;
    private EditText bio;
    private AccessUsers accessUsers;
    Calendar myCalendar = Calendar.getInstance();
    private int dateDay = 0;
    private int dateMonth = 0;
    private int dateYear = 0;
    private EditText weight1;
    private EditText weight2;
    private EditText weight3;
    private EditText weight4;
    private EditText weight5;


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

                Intent intent = new Intent(EditProfile.this, NavigationPageActivity.class);
                intent.putExtra("value", 1);
                startActivity(intent);
            }
        });
        answer1 = findViewById(R.id.spinner1);
        answer2 = findViewById(R.id.spinner2);
        answer3 = findViewById(R.id.spinner3);
        answer4 = findViewById(R.id.spinner4);
        answer5 = findViewById(R.id.spinner5);
        gender1 = findViewById(R.id.spinner6);
        gender2 = findViewById(R.id.spinner7);
        cancel = findViewById(R.id.edit_profile_cancel);
        save = findViewById(R.id.edit_profile_save);
        dateButton = findViewById(R.id.edit_profile_date_picker);
        dateText = findViewById(R.id.edit_profile_date);
        firstName = findViewById(R.id.edit_profile_first_name);
        lastName = findViewById(R.id.edit_profile_last_name);
        bio = findViewById(R.id.profile_bio);
        weight1 = findViewById(R.id.edit_profile_weight1);
        weight2 = findViewById(R.id.edit_profile_weight2);
        weight3 = findViewById(R.id.edit_profile_weight3);
        weight4 = findViewById(R.id.edit_profile_weight4);
        weight5 = findViewById(R.id.edit_profile_weight5);
        accessUsers = new AccessUsers();
        ArrayAdapter<String> answersAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, answers);
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, gender);
        answer1.setAdapter(answersAdapter);
        answer2.setAdapter(answersAdapter);
        answer3.setAdapter(answersAdapter);
        answer4.setAdapter(answersAdapter);
        answer5.setAdapter(answersAdapter);
        gender1.setAdapter(genderAdapter);
        gender2.setAdapter(genderAdapter);

        firstName.setText(accessUsers.getCurrentUser().getUserFirstName());
        lastName.setText(accessUsers.getCurrentUser().getUserLastName());
        bio.setText(accessUsers.getCurrentUser().getUserProfile().getBio());
        dateText.setText(accessUsers.getCurrentUser().getUserProfile().dateOfBirth());
        if (accessUsers.getCurrentUser().getUserProfile().genderToString().equals("male")) {
            gender1.setSelection(0);
        } else {
            gender1.setSelection(1);
        }

        if (accessUsers.getCurrentUser().getUserProfile().genderPrefToString().equals("male")) {
            gender2.setSelection(0);
        } else {
            gender2.setSelection(1);
        }

        final ArrayList<Question> userAnswers = accessUsers.getCurrentUser().getUserProfile().getAnswers();
        if (userAnswers.get(0).getAnswer() == true) {
            answer1.setSelection(0);
        } else {
            answer1.setSelection(1);
        }
        if (userAnswers.get(1).getAnswer() == true) {
            answer2.setSelection(0);
        } else {
            answer2.setSelection(1);
        }
        if (userAnswers.get(2).getAnswer() == true) {
            answer3.setSelection(0);
        } else {
            answer3.setSelection(1);
        }
        if (userAnswers.get(3).getAnswer() == true) {
            answer4.setSelection(0);
        } else {
            answer4.setSelection(1);
        }
        if (userAnswers.get(4).getAnswer() == true) {
            answer5.setSelection(0);
        } else {
            answer5.setSelection(1);
        }

        weight1.setText(Integer.toString(userAnswers.get(0).getWeight()));
        weight2.setText(Integer.toString(userAnswers.get(1).getWeight()));
        weight3.setText(Integer.toString(userAnswers.get(2).getWeight()));
        weight4.setText(Integer.toString(userAnswers.get(3).getWeight()));
        weight5.setText(Integer.toString(userAnswers.get(4).getWeight()));


        weight1.setInputType(InputType.TYPE_CLASS_NUMBER);
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(1);
        weight1.setFilters(FilterArray);
        weight1.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

                String added_number = weight1.getText().toString();
                if (added_number.length() != 0) {
                    int number = Integer.parseInt(added_number);

                    if (number > 5) {
                        weight1.setText("5");
                        Toast.makeText(getApplicationContext(), "Not more than 5", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        weight2.setInputType(InputType.TYPE_CLASS_NUMBER);
        weight2.setFilters(FilterArray);
        weight2.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

                String added_number = weight2.getText().toString();
                if (added_number.length() != 0) {
                    int number = Integer.parseInt(added_number);

                    if (number > 5) {
                        weight2.setText("5");
                        Toast.makeText(getApplicationContext(), "Not more than 5", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });


        weight3.setInputType(InputType.TYPE_CLASS_NUMBER);
        weight3.setFilters(FilterArray);
        weight3.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

                String added_number = weight3.getText().toString();
                if (added_number.length() != 0) {
                    int number = Integer.parseInt(added_number);

                    if (number > 5) {
                        weight3.setText("5");
                        Toast.makeText(getApplicationContext(), "Not more than 5", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });


        weight4.setInputType(InputType.TYPE_CLASS_NUMBER);
        weight4.setFilters(FilterArray);
        weight4.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

                String added_number = weight4.getText().toString();
                if (added_number.length() != 0) {
                    int number = Integer.parseInt(added_number);

                    if (number > 5) {
                        weight4.setText("5");
                        Toast.makeText(getApplicationContext(), "Not more than 5", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });


        weight5.setInputType(InputType.TYPE_CLASS_NUMBER);
        weight5.setFilters(FilterArray);
        weight5.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

                String added_number = weight5.getText().toString();
                if (added_number.length() != 0) {
                    int number = Integer.parseInt(added_number);

                    if (number > 5) {
                        weight5.setText("5");
                        Toast.makeText(getApplicationContext(), "Not more than 5", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfile.this, NavigationPageActivity.class);
                intent.putExtra("value", 1);
                startActivity(intent);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accessUsers.getCurrentUser().setUserFirstName(firstName.getText().toString());
                accessUsers.getCurrentUser().setUserLastName(lastName.getText().toString());
                accessUsers.getCurrentUser().getUserProfile().setBio(bio.getText().toString());
                if (dateDay != 0 && dateMonth != 0 && dateYear != 0)
                    accessUsers.getCurrentUser().getUserProfile().setDateOfBirth(dateYear, dateMonth, dateDay);
                if (gender1.getSelectedItem().equals("Male")) {
                    accessUsers.getCurrentUser().getUserProfile().setGender(User.user_gender.Male);
                } else {
                    accessUsers.getCurrentUser().getUserProfile().setGender(User.user_gender.Female);
                }
                if (gender2.getSelectedItem().equals("Male")) {
                    accessUsers.getCurrentUser().getUserProfile().setGenderPreference(User.user_gender.Male);
                } else {
                    accessUsers.getCurrentUser().getUserProfile().setGenderPreference(User.user_gender.Female);
                }
                accessUsers.getCurrentUser().getUserProfile().updateAllAnswers(Boolean.parseBoolean(answer1.getSelectedItem().toString()), Boolean.parseBoolean(answer2.getSelectedItem().toString()), Boolean.parseBoolean(answer3.getSelectedItem().toString()), Boolean.parseBoolean(answer4.getSelectedItem().toString()), Boolean.parseBoolean(answer5.getSelectedItem().toString()), Integer.parseInt(weight1.getText().toString()), Integer.parseInt(weight2.getText().toString()), Integer.parseInt(weight3.getText().toString()), Integer.parseInt(weight4.getText().toString()), Integer.parseInt(weight5.getText().toString()));
                accessUsers.updateUser(accessUsers.getCurrentUser());
                Intent intent = new Intent(EditProfile.this, NavigationPageActivity.class);
                intent.putExtra("value", 1);
                startActivity(intent);

            }
        });

        final DatePickerDialog.OnDateSetListener datePicker = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                dateDay = dayOfMonth;
                dateMonth = monthOfYear + 1;
                dateYear = year;
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditProfile.this, R.style.DialogTheme, datePicker, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateText.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EditProfile.this, NavigationPageActivity.class);
        intent.putExtra("value", 1);
        startActivity(intent);
    }
}