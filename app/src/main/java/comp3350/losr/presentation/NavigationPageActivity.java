package comp3350.losr.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import comp3350.losr.R;

public class NavigationPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_page);
    }

    public void buttonProfileOnClick(View v)
    {
        Intent profileIntent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(profileIntent);
    }
}