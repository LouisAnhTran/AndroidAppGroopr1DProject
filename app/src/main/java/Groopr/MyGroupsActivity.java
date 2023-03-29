package Groopr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Groopr.R;
import com.google.firebase.database.DatabaseReference;

public class MyGroupsActivity extends AppCompatActivity {
    Button createGroupButton;

    private DatabaseReference mDatabase;

/*    public final static String ID_KEY = "ID_KEY";*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_groups);

        createGroupButton = findViewById(R.id.createGroupButton);

        createGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MyGroupsActivity.this, CreateGroupActivity.class);
                startActivity(intent);
            }
        });
    }
}