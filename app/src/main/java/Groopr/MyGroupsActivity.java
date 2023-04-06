package Groopr;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Groopr.Model.FactoryDesignForPillar.Pillar;
import Groopr.Model.FactoryDesignForPillar.ShapeFactory;
import Groopr.Model.MyGroupsAdapter;
import Groopr.Model.Project;
import Groopr.Model.ProjectSupport;
import Groopr.Model.ShowGroupRecycleViewInterface;
import Groopr.Model.Student;

public class MyGroupsActivity extends AppCompatActivity implements ShowGroupRecycleViewInterface {
    RecyclerView recyclerView;
    Button createGroupButton;

    private DatabaseReference mDatabase;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = user.getUid();

    private ArrayList<ProjectSupport> projects;

    public static final String TAG="PassProjectID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_groups_page_revised);
        createGroupButton = findViewById(R.id.createGroupButton);

/*      module1 = findViewById(R.id.module1);
        module2 = findViewById(R.id.module2);
        module3 = findViewById(R.id.module3);
        module4 = findViewById(R.id.module4);
        module1Button = findViewById(R.id.module1Button);
        module2Button = findViewById(R.id.module2Button);
        module3Button = findViewById(R.id.module3Button);
        module4Button = findViewById(R.id.module4Button);

        TextView[] moduleTextViewList = {module1, module2, module3, module4};
        Button[] moduleButtonList = {module1Button, module2Button, module3Button, module4Button};*/

        // Create Group button
        createGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MyGroupsActivity.this, CreateGroupActivity.class);
                startActivity(intent);
            }
        });

        DatabaseReference projectsRef = FirebaseDatabase.getInstance().getReference().child("Project");
        Query query = projectsRef.orderByChild("studentList");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                MyGroupsActivity.this.projects =new ArrayList<>();
                for (DataSnapshot projectSnapshot: snapshot.getChildren()){
                    ProjectSupport project = projectSnapshot.getValue(ProjectSupport.class);
                    if (project.getStudentList().contains(uid)){
                        project.setProjectID(projectSnapshot.getKey());
                        projects.add(project);
                    }
                }
                Log.d("MyGroupsActivity", "projects list size: " + projects.size());
                recyclerView = findViewById(R.id.nRecycleView);
                MyGroupsAdapter adapter = new MyGroupsAdapter(MyGroupsActivity.this, projects, MyGroupsActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                recyclerView.setLayoutManager(new LinearLayoutManager(MyGroupsActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void OnItemClick(int pos) {
        Intent intent=new Intent(this,EditGroupsActivity.class);
        intent.putExtra(TAG,this.projects.get(pos).getProjectID().toString());
        startActivity(intent);
    }

        // Select module headers to show depending on pillar and term

/*        DatabaseReference studentRef = FirebaseDatabase.getInstance().getReference("Student").child(uid);

        studentRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Retrieve the pillar and term attributes from the Student node
                String pillar = dataSnapshot.child("pillar").getValue().toString();
                String term = dataSnapshot.child("term").getValue().toString();
                ShapeFactory st = new ShapeFactory();
                Pillar p = st.getProduct(pillar, term);
                List<String> moduleList = p.getModuleList();
                int i = 0;
                for (String s: moduleList){
                    moduleTextViewList[i].setText(s);
                    i++;
                }
                for (int j = i; j < moduleList.size(); j++){
                    moduleTextViewList[j].setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                studentRef.addListenerForSingleValueEvent(this);
            }
        });

        // Display relevant groups current student is a member of under the correct header

        studentRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Retrieve the pillar and term attributes from the Student node
                String pillar = dataSnapshot.child("pillar").getValue().toString();
                String term = dataSnapshot.child("term").getValue().toString();
                Student student = dataSnapshot.getValue(Student.class);
                ShapeFactory st = new ShapeFactory();
                Pillar p = st.getProduct(pillar, term);
                List<String> moduleList = p.getModuleList();
                Map<String, String> modProjectMapper = new HashMap<>();
                for (String i: moduleList){
                    modProjectMapper.put(i.substring(0, 6), "");
                }
                DatabaseReference projectRef = FirebaseDatabase.getInstance().getReference("Project");
                // iterate through projectsList and add corresponding modID - projectID key-value pairs to hashmap
                for (String projectID: student.getProjectList()){
                    if (!projectID.equals("")){
                        projectRef.child(projectID).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String modID = snapshot.child("moduleID").getValue().toString();
                                modProjectMapper.put(modID, projectID);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }

                }
                int i = 0;
                for (Map.Entry<String, String> entry: modProjectMapper.entrySet()){
                    String projectID  = entry.getValue();
                    if (projectID == ""){
                        moduleButtonList[i].setText(R.string.no_project);
                        i++;
                    }
                    else{
                        int finalI = i;
                        projectRef.child(projectID).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String projectName = snapshot.child("projectName").getValue().toString();
                                moduleButtonList[finalI].setText(projectName);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        i++;
                    }
                }
             }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


}

