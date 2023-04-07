package Groopr;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Groopr.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Groopr.Model.Project;
import Groopr.Model.ProjectSupport;
import Groopr.Model.RecruitmentAdapter;
import Groopr.Model.ShowGroupRecycleViewInterface;

public class RecruimentShowGroup extends AppCompatActivity implements ShowGroupRecycleViewInterface {
    private DatabaseReference mDatabase;

    private String moduleID;

    private String fullNameModule;

    private TextView title;

    private ArrayList<ProjectSupport> projectList;

    public static final String TAG="PassProjectID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recruitment_page_2_connect);

        Intent intent=getIntent();
        this.moduleID=intent.getStringExtra(RecruimentHomePage.TAG);
        this.fullNameModule=intent.getStringExtra(RecruimentHomePage.TAG1);


        mDatabase=FirebaseDatabase.getInstance().getReference();

        title=findViewById(R.id.moduleName);


        title.setText(this.fullNameModule.toString());

        mDatabase.child("Project").orderByChild("moduleID").equalTo(this.moduleID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                RecruimentShowGroup.this.projectList=new ArrayList<ProjectSupport>();
                for(DataSnapshot object:snapshot.getChildren()){
                    ProjectSupport t1=object.getValue(ProjectSupport.class);
                    t1.setProjectID(object.getKey());
                    projectList.add(t1);
                }


                RecyclerView recyclerView=findViewById(R.id.mRecycleView);

                RecruitmentAdapter adapter=new RecruitmentAdapter(RecruimentShowGroup.this,RecruimentShowGroup.this.projectList,RecruimentShowGroup.this);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(RecruimentShowGroup.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        mDatabase= FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Project").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Project> p1=new ArrayList<Project>();
                for(DataSnapshot d1: snapshot.getChildren()){
                    p1.add(d1.getValue(Project.class));
                }

                Log.d("Our Info",p1.get(0).getMessage());

                RecyclerView recyclerView=findViewById(R.id.mRecycleView);

                RecruitmentAdapter adapter=new RecruitmentAdapter(RecruimentShowGroup.this,RecruimentShowGroup.this.projectList,RecruimentShowGroup.this);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(RecruimentShowGroup.this));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void OnItemClick(int pos) {
        Intent intent=new Intent(this,RecruitmentGroupInfo.class);
        intent.putExtra(TAG,this.projectList.get(pos).getProjectID().toString());
        startActivity(intent);
    }
}
