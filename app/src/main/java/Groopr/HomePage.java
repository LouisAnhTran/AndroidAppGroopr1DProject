package Groopr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.Groopr.R;

public class HomePage extends AppCompatWithToolbar {

    Button myGroupsButton;
    Button recruimentButton;
    Button profileButton;
    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar_home);

        // using toolbar as ActionBar
        setSupportActionBar(myToolbar);

        myGroupsButton = findViewById(R.id.myGroupsButton);
        recruimentButton=findViewById(R.id.recruimentButton);
        profileButton=findViewById(R.id.profileButton);
        logoutButton=findViewById(R.id.logOutButton);


        /** Navigate to recruitment page **/
        recruimentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomePage.this,RecruimentHomePage.class);
                startActivity(intent);
            }
        });


        /** Navigate to my group page **/
        myGroupsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( HomePage.this, MyGroupsActivity.class);
                startActivity(intent);
            }
        });

        /** Navigate to profile **/
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomePage.this,MyProfile.class);
                startActivity(intent);
            }
        });


        /** Navigate to log in page**/
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomePage.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected int getCurrentMenuId() {
        return R.id.HomePage;
    }
}
