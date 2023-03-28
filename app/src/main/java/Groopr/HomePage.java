package Groopr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Groopr.R;

public class HomePage extends AppCompatActivity {

    Button myGroupsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        myGroupsButton = findViewById(R.id.myGroupsButton);

        myGroupsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( HomePage.this, MyGroupsActivity.class);
                startActivity(intent);
            }
        });

    }
}
