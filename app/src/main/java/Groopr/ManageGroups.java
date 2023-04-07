package Groopr;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Groopr.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import Groopr.Model.MembersAdapter;
import Groopr.Model.Project;
import Groopr.Model.RecruitmentAdapter;
import Groopr.Model.Student;



public class ManageGroups extends AppCompatWithToolbar {
    private String projectID;
    private DatabaseReference mDatabase;
    RecyclerView recyclerView;
    private String teamleader_id;
    private String curr_UID;
    private boolean is_admin;
    private List<String> member_list;

    private TextView manageGroups;
    private TextView youAreAdmin;
    private TextView groupName;
    private TextView modName;
    private TextView info;
    private TextView skillList;
    private Button editGroup;
    private Button leaveGroup;
    private Button manageApps;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_groups_page);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar_managegroups);

        // using toolbar as ActionBar
        setSupportActionBar(myToolbar);

        // Attributes
        Intent init_intent = getIntent();
        projectID = init_intent.getStringExtra(RecruitmentGroupInfo.TAG);
        // For testing purposes, hardcoded projectID
        //Log.d("Check 5",projectID);
        // projectID = "-NSMevoBVnet7Ulj8-Jo";
        is_admin = false;
        curr_UID = "";

        // IDs
        manageGroups = findViewById(R.id.ManageGroups);
        youAreAdmin = findViewById(R.id.YouAreAdmin);
        editGroup = findViewById(R.id.editGroup);
        leaveGroup = findViewById(R.id.leaveGroup);
        manageApps = findViewById(R.id.manageApplications);
        groupName = findViewById(R.id.groupName);
        modName = findViewById(R.id.modName);
        info = findViewById(R.id.info);
        recyclerView = findViewById(R.id.recyclerView);
        skillList = findViewById(R.id.skillList2);

        // Hiding edit and manage initially
        editGroup.setVisibility(View.INVISIBLE);
        manageApps.setVisibility(View.INVISIBLE);

        // Connection to Database
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Project").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Project project = snapshot.child(projectID).getValue(Project.class);
                teamleader_id = project.getTeamLeaderId();
                groupName.setText(project.getProjectName());
                modName.setText(project.getModuleID());
                info.setText(project.getMessage());
                member_list = project.getStudentList();
                skillList.setText(project.getSkillNeeded());

                // check for admin
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    curr_UID = user.getUid();
                    is_admin = admin_check(curr_UID);
                }

                if (is_admin) {
                    admin_mode();
                }

                // Inserting members into recycle view
                recyclerView.setLayoutManager( new LinearLayoutManager(ManageGroups.this));
                RecyclerView.Adapter<MembersAdapter.MembersHolder> adapter
                        = new MembersAdapter(ManageGroups.this, member_list);
                recyclerView.setAdapter( adapter );



            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /** TODO
         * Button Logic for:
         * 1. Leave Group
         * 2. manage group
         * 3. edit group
         */
        leaveGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String update = "Leaving was unsuccessful";
                // Check if UID exists, and remove if so
                if (!curr_UID.equals("")) {
                    if (member_list.contains(curr_UID)) {
                        member_list.remove(curr_UID);
                        mDatabase.child("Project").child(projectID).child("studentList").setValue(member_list);
                        update = "You have successfully left the group.";
                    }
                }
                // Toast to show updates
                Toast toast = Toast.makeText(getApplicationContext(), update, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        editGroup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageGroups.this, EditGroupsActivity.class);
                startActivity(intent);
            }
        });

        manageApps.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageGroups.this, Applications.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Check if curr user is admin
     * @param curr_UID
     * @return
     */
    boolean admin_check(String curr_UID) {
        if (teamleader_id.equals(curr_UID)) {
            return true;
        }

        return false;
    }

    /**
     * Activate admin rights
     */
    @SuppressLint("SetTextI18n")
    void admin_mode() {
        manageGroups.setText("Groups: Admin");
        youAreAdmin.setText("You are an admin.You can edit group.");
        manageApps.setVisibility(View.VISIBLE);
        editGroup.setVisibility(View.VISIBLE);
        Toast toast = Toast.makeText(getApplicationContext(), "Welcome, team leader!", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    protected int getCurrentMenuId() {
        return 0;
    }
}
