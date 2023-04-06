package Groopr;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.List;

import Groopr.Model.Project;
import Groopr.Model.RecruitmentAdapter;

public class RecruitmentGroupInfo extends AppCompatActivity {
    private DatabaseReference mDatabase;
    TextView grp_name;
    TextView mod_name;
    TextView grp_desc;
    Button apply;

    String projectID;
    String curr_UID;
    Integer group_max_size;
    ArrayList<String> application_list;
    List<String> member_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recruitments_grp_info_pg);

        Intent intent=getIntent();
        String projectID=intent.getStringExtra(RecruimentShowGroup.TAG);
        Log.d("Check 5",projectID);

//        // UI Views
//        grp_name = findViewById(R.id.grp_name);
//        mod_name = findViewById(R.id.mod_name);
//        grp_desc = findViewById(R.id.grp_desc);
//        apply = findViewById(R.id.apply);
//
//
//        /**
//         * Loading group details from firebase
//         */
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        // TODO: Get ProjectID from previous page
//        mDatabase.child("Project").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                projectID = "-NRv56aotjPTxkYtPNO8";
////                Project project = snapshot.child(projectID).getValue(Project.class);
//
////                // Set details on frontend using project details
////                grp_name.setText(project.getProjectName());
////                mod_name.setText(project.getModuleID());
////                grp_desc.setText(project.getMessage());
////
////                // saving attributes
////                group_max_size = project.getMaxNumberOfMember();
////                application_list = project.getApplicationsList();
////                member_list = project.getStudentList();
////
////                // Getting members
////                for (String member: project.getStudentList()) {
////                    Log.d(member, member);
////                    // TODO: List/Recycle view stuff
////                }
////            }
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////
////            }
//        });
//
//
//
//        /** APPLYING TO A GROUP
//         * If user presses the `Apply` button
//         *  Adds current user to the pending approval list of the group if criterias are met:
//         *  1. User is not already pending
//         *  2. Group size is not full
//         */
//
//        apply.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                // Apply if user exists
//                if (user != null) {
//                    curr_UID = user.getUid();
//                    user_apply(curr_UID, projectID);
//                }
//                user_apply("SUP", projectID);
//            }
//        });
//    }
//
//    /** user_apply()
//     * helps current user apply to the selected group
//     * takes current project data from firebase
//     * updates its application list if user isnt already in it
//     */
//    void user_apply(String curr_UID, String projectID) {
//        String update = "";
//        // Check for valid array, else create one
//        if (application_list == null) {
//            application_list = new ArrayList<>();
//        }
//
//        // Update Application List of class
//        // Checks if User exists in Array first and if group is already full
//        // Updates Toast's String according to what happened here
//        if (!application_list.contains(curr_UID) && application_list.size() < group_max_size && !member_list.contains(curr_UID)) {
//            application_list.add(curr_UID);
//            // Update Database
//            mDatabase.child("Project").child(projectID).child("applicationsList").setValue(application_list);
//            update = "Application Successful!";
//        } else if (!(member_list.size() < group_max_size)) {
//            update = "Group is full!";
//        } else if (application_list.contains(curr_UID)) {
//            update = "You have already applied!";
//        } else if (member_list.contains(curr_UID)) {
//            update = "You're already a member of this group!";
//        }
//
//        // Show toast
//        Toast toast = Toast.makeText(getApplicationContext(), update, Toast.LENGTH_SHORT);
//        toast.show();
//
//    }
    }
}

