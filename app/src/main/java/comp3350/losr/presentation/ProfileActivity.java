package comp3350.losr.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.List;

import comp3350.losr.R;
import comp3350.losr.business.AccessMatches;
import comp3350.losr.objects.Match;

public class ProfileActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
}