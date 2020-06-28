package comp3350.losr.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import comp3350.losr.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void buttonRegisterOnClick(View v)
    {
        Intent navigationIntent = new Intent(RegisterActivity.this, NavigationPageActivity.class);
        startActivity(navigationIntent);
    }
}