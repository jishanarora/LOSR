package comp3350.losr.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import comp3350.losr.R;

public class RegisterActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        frameLayout= findViewById(R.id.register_framelayout);
        setFragment(new SignInFragment());
    }

    private void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }

    public void buttonRegisterOnClick(View v)
    {
        Intent navigationIntent = new Intent(RegisterActivity.this, NavigationPageActivity.class);
        startActivity(navigationIntent);
    }
}