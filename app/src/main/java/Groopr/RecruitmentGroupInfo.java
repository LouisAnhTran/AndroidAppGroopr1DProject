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
import com.google.android.gms.tasks.Task;
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
    TextView grp_name = findViewById(R.id.grp_name);
    TextView mod_name = findViewById(R.id.mod_name);
    TextView grp_desc = findViewById(R.id.grp_desc);
    Button apply = findViewById(R.id.apply);

    String projectID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recruitments_grp_info_pg);

        /** #TODO Fill up the somePage class
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecruitmentGroupInfo.this, somePage.class);
                startActivity(intent);
            }
        });
         */

        // Firebase RealTime Database
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // TODO: Get ProjectID from previous page
        mDatabase.child("Project").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                projectID = "-NRfwW-3-ErA8V6LtqpC";
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
    }

}
