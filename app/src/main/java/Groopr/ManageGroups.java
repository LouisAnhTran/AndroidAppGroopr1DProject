package Groopr;

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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import Groopr.Model.MembersAdapter;
import Groopr.Model.Project;
import Groopr.Model.RecruitmentAdapter;
import Groopr.Model.Student;


public class ManageGroups extends AppCompatActivity {
    private String projectID;
    private DatabaseReference mDatabase;
    RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_groups_page);

        // Attributes
        // TODO: ProjectID from prev page
        projectID = "-NSMCDuj1claJ6b_Q6nw";

        mDatabase = FirebaseDatabase.getInstance().getReference();
        // TODO: Get ProjectID from previous page
        mDatabase.child("Project").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Project project = snapshot.child(projectID).getValue(Project.class);


                // Inserting members into recycle view
                recyclerView.setLayoutManager( new LinearLayoutManager(RecruitmentGroupInfo.this));
                RecyclerView.Adapter<MembersAdapter.MembersHolder> adapter
                        = new MembersAdapter(RecruitmentGroupInfo.this, student_names);
                recyclerView.setAdapter( adapter );


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
