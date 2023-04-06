package Groopr;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Groopr.R;

public abstract class AppCompatWithToolbar extends AppCompatActivity {
    /**
     * Superclass to contain Menu code since every activity uses a universal menu
     **/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);

        // Using template method design pattern to remove unnecessary menu items
        int currentMenuId = getCurrentMenuId();
        if (currentMenuId != 0) {
            menu.findItem(currentMenuId).setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    protected abstract int getCurrentMenuId();

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.RecruitmentPage:
                Toast.makeText(this, "To Recruitments Page", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, RecruimentHomePage.class);
                startActivity(intent);
                return true;
            case R.id.MyGroupsPage:
                Toast.makeText(this, "To My Groups Page", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(this, MyGroupsActivity.class);
                startActivity(intent1);
                return true;
            case R.id.ProfilePage:
                Toast.makeText(this, "To Profile Page", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(this, MyProfile.class);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
