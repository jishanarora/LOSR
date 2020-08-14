package comp3350.losr.presentation;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import comp3350.losr.R;
import comp3350.losr.business.AccessUsers;
import comp3350.losr.objects.User;

public class SignUpDetails extends AppCompatActivity {

    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private Spinner spinner4;
    private Spinner spinner5;
    private Spinner spinner6;
    private Spinner spinner7;
    private TextView date;
    private Button selectDate;
    private Button proceed;
    private String[] answers = new String[]{"Choose", "True", "False"};
    private String[] gender = new String[]{"Choose", "Male", "Female"};
    private String[] weight = new String[]{"Choose", "1", "2", "3", "4", "5"};
    private Spinner weight1;
    private Spinner weight2;
    private Spinner weight3;
    private Spinner weight4;
    private Spinner weight5;
    private AccessUsers accessUsers;
    Calendar myCalendar = Calendar.getInstance();
    private int dateDay = 0;
    private int dateMonth = 0;
    private int dateYear = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_details);

        spinner1 = findViewById(R.id.signUp_spinner1);
        spinner1.setSelection(0);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView) parentView.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2 = findViewById(R.id.signUp_spinner2);
        spinner2.setSelection(0);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView) parentView.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner3 = findViewById(R.id.signUp_spinner3);
        spinner3.setSelection(0);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView) parentView.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner4 = findViewById(R.id.signUp_spinner4);
        spinner4.setSelection(0);
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView) parentView.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner5 = findViewById(R.id.signUp_spinner5);
        spinner5.setSelection(0);
        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView) parentView.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner6 = findViewById(R.id.signUp_spinner6);
        spinner6.setSelection(0);
        spinner6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView) parentView.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner7 = findViewById(R.id.signUp_spinner7);
        spinner7.setSelection(0);
        spinner7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView) parentView.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        date = findViewById(R.id.signUp_date);
        selectDate = findViewById(R.id.signUp_select_date);
        proceed = findViewById(R.id.signUp_proceed);
        weight1 = findViewById(R.id.signUp_spinner8);
        weight1.setSelection(0);
        weight1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView) parentView.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        weight2 = findViewById(R.id.signUp_spinner9);
        weight2.setSelection(0);
        weight2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView) parentView.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        weight3 = findViewById(R.id.signUp_spinner10);
        weight3.setSelection(0);
        weight3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView) parentView.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        weight4 = findViewById(R.id.signUp_spinner11);
        weight4.setSelection(0);
        weight4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView) parentView.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        weight5 = findViewById(R.id.signUp_spinner12);
        weight5.setSelection(0);
        weight5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView) parentView.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        accessUsers = new AccessUsers();

        ArrayAdapter<String> answersAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, answers);
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, gender);
        ArrayAdapter<String> weightAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, weight);

        spinner1.setAdapter(genderAdapter);
        spinner2.setAdapter(genderAdapter);
        spinner3.setAdapter(answersAdapter);
        spinner4.setAdapter(answersAdapter);
        spinner5.setAdapter(answersAdapter);
        spinner6.setAdapter(answersAdapter);
        spinner7.setAdapter(answersAdapter);
        weight1.setAdapter(weightAdapter);
        weight2.setAdapter(weightAdapter);
        weight3.setAdapter(weightAdapter);
        weight4.setAdapter(weightAdapter);
        weight5.setAdapter(weightAdapter);
        date.setText("Select Date");

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dateDay != 0 && dateMonth != 0 && dateYear != 0)
                    accessUsers.getCurrentUser().setUserDateOfBirth(dateYear, dateMonth, dateDay);
                else {
                    Toast.makeText(getApplicationContext(), "Select the Date of Birth", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (spinner1.getSelectedItem().toString().equals("Choose") || spinner2.getSelectedItem().toString().equals("Choose") || spinner3.getSelectedItem().toString().equals("Choose") || spinner4.getSelectedItem().toString().equals("Choose") || spinner5.getSelectedItem().toString().equals("Choose") || spinner6.getSelectedItem().toString().equals("Choose") || spinner7.getSelectedItem().toString().equals("Choose") || weight1.getSelectedItem().toString().equals("Choose") || weight2.getSelectedItem().toString().equals("Choose") || weight3.getSelectedItem().toString().equals("Choose") || weight4.getSelectedItem().toString().equals("Choose") || weight5.getSelectedItem().toString().equals("Choose")) {
                    Toast.makeText(getApplicationContext(), "Please answer all the questions", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (spinner1.getSelectedItem().equals("Male")) {
                        accessUsers.getCurrentUser().setUserGender(User.user_gender.Male);
                    } else {
                        accessUsers.getCurrentUser().setUserGender(User.user_gender.Female);
                    }
                    if (spinner2.getSelectedItem().equals("Male")) {
                        accessUsers.getCurrentUser().setUserGenderPreference(User.user_gender.Male);
                    } else {
                        accessUsers.getCurrentUser().setUserGenderPreference(User.user_gender.Female);
                    }
                    accessUsers.getCurrentUser().setUserAllAnswers(Boolean.parseBoolean(spinner3.getSelectedItem().toString()), Boolean.parseBoolean(spinner4.getSelectedItem().toString()), Boolean.parseBoolean(spinner5.getSelectedItem().toString()), Boolean.parseBoolean(spinner6.getSelectedItem().toString()), Boolean.parseBoolean(spinner7.getSelectedItem().toString()), Integer.parseInt(weight1.getSelectedItem().toString()), Integer.parseInt(weight2.getSelectedItem().toString()), Integer.parseInt(weight3.getSelectedItem().toString()), Integer.parseInt(weight4.getSelectedItem().toString()), Integer.parseInt(weight5.getSelectedItem().toString()));
                    accessUsers.updateUser(accessUsers.getCurrentUser());
                    Intent intent = new Intent(SignUpDetails.this, NavigationPageActivity.class);
                    intent.putExtra("value", 0);
                    startActivity(intent);

                }
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

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SignUpDetails.this, R.style.DialogTheme, datePicker, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        date.setText(sdf.format(myCalendar.getTime()));
    }


}
