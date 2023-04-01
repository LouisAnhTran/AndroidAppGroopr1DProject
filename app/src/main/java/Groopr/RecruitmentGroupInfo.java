package Groopr;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

import java.util.ArrayList;

import Groopr.Model.Project;
import Groopr.Model.RecruitmentAdapter;

public class RecruitmentGroupInfo extends AppCompatActivity {
    private DatabaseReference mDatabase;
    TextView grp_name;
    TextView mod_name;
    TextView grp_desc;
    Button apply;

    String projectID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recruitments_grp_info_pg);

        grp_name = findViewById(R.id.grp_name);
        mod_name = findViewById(R.id.mod_name);
        grp_desc = findViewById(R.id.grp_desc);
        apply = findViewById(R.id.apply);


        /**
         * Loading group details from firebase
         */
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // TODO: Get ProjectID from previous page
        mDatabase.child("Project").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                projectID = "-NRv56aotjPTxkYtPNO8";
                Project project = snapshot.child(projectID).getValue(Project.class);

                // Set details on frontend using project details
                grp_name.setText(project.getProjectName());
                mod_name.setText(project.getModuleID());
                grp_desc.setText(project.getMessage());

                // Getting members
                for (String member: project.getStudentList()) {
                    Log.d(member, member);
                    // List view stuff
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /** APPLYING TO A GROUP
         * If user presses the `Apply` button
         *  Adds current user to the pending approval list of the group
         */
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                // Apply if user exists
                if (user != null) {
                    String curr_UID = user.getUid();
                    user_apply(curr_UID, projectID);
                }
            }
        });
    }

    /** user_apply()
     * helps current user apply to the selected group
     * takes current project data from firebase
     * updates its application list
     */
    void user_apply(String curr_UID, String projectID) {
        mDatabase.child("Project").child(projectID).child("applicationsList").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                // Variables needed
                // Get project data, and update the application list
                ArrayList<String> application_list = (ArrayList<String>) task.getResult().getValue();


                // Check for valid array, else create one
                if (application_list == null) {
                    application_list = new ArrayList<>();
                }

                // Update Application List of class
                application_list.add(curr_UID);

                // Update Firebase
                mDatabase.child("Project").child(projectID).child("applicationsList").setValue(application_list);
            }
        });
        // Update Database

        }
}

