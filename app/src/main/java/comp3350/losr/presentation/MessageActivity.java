package comp3350.losr.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.losr.R;

public class MessageActivity extends AppCompatActivity {
    private TextView phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("View Match's Phone Number");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MessageActivity.this, NavigationPageActivity.class);
                intent.putExtra("value", 1);
                startActivity(intent);
            }
        });

        phoneNumber = findViewById(R.id.phoneNum);


        Intent intent = new Intent(this, MessageFragment.class);
        phoneNumber.setText(intent.getStringExtra("phoneNumber"));

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MessageActivity.this, NavigationPageActivity.class);
        intent.putExtra("value", 1);
        startActivity(intent);
    }
}