package comp3350.losr.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import comp3350.losr.R;
import comp3350.losr.application.Main;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Main.startUp();

        setContentView(R.layout.activity_signin_page);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        Main.shutDown();
    }

    public void buttonLoginOnClick(View v)
    {
        Intent navigationIntent = new Intent(SignInActivity.this, NavigationPageActivity.class);
        startActivity(navigationIntent);
    }

    public void textViewRegisterOnClick(View v)
    {
        Intent registerIntent = new Intent(SignInActivity.this, RegisterActivity.class);
        startActivity(registerIntent);
    }
}