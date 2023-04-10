package Groopr;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppLocalesMetadataHolderService;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Groopr.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Groopr.Model.ApplicationAdapter;
import Groopr.Model.ApplicationInterface;
import Groopr.Model.Project;
import Groopr.Model.ProjectSupport;
import Groopr.Model.RecruitmentAdapter;
import Groopr.Model.Student;
import Groopr.Model.StudentSupport;

public class Applications extends AppCompatActivity implements ApplicationInterface {
    private ArrayList<String> applicationList;

    private ArrayList<Student> studentsList;

    private ArrayList<Student> allStudentList;

    private TextView groupInfo;

    private DatabaseReference mDatabase;

    private FirebaseUser user;

    private ProjectSupport projectSupport;

    private String projectID;

    private ProjectSupport projectSupport1;

    private int track;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applications_pending);

        Intent init_intent = getIntent();
        this.projectID = init_intent.getStringExtra(ManageGroups.TAG);
        Log.d("Check 5",this.projectID);

        this.groupInfo=findViewById(R.id.moduleNameApplication);

        this.mDatabase= FirebaseDatabase.getInstance().getReference();

        this.user= FirebaseAuth.getInstance().getCurrentUser();

        this.applicationList=new ArrayList<String>();

        this.studentsList=new ArrayList<Student>();

        this.track=0;


        this.mDatabase.child("Project").child(this.projectID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Applications.this.projectSupport = snapshot.getValue(ProjectSupport.class);
                Applications.this.groupInfo.setText(Applications.this.projectSupport.getProjectName());
                Log.d("Check 5",Applications.this.projectID);
                Log.d("Check 7", Applications.this.projectSupport.toString());
                for (String st : Applications.this.projectSupport.getApplicationsList()) {
                    if (st.length() > 2) {
                        Applications.this.applicationList.add(st);
                    }
                }

                Log.d("Check 8",Applications.this.applicationList.toString());

                Applications.this.mDatabase.child("Student").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Applications.this.allStudentList=new ArrayList<Student>();
                        for(DataSnapshot ob: snapshot.getChildren()){
                            StudentSupport stu=ob.getValue(StudentSupport.class);
                            stu.setStudentUID(ob.getKey());
                            Applications.this.allStudentList.add(stu);
                        }

                        Log.d("CHECK 13",Applications.this.allStudentList.toString());

                        for(String st:Applications.this.applicationList){
                            for(Student stu: Applications.this.allStudentList){
                                if(((StudentSupport)stu).getStudentUID().equalsIgnoreCase(st)){
                                    Applications.this.studentsList.add(stu);
                                    break;
                                }
                            }
                        }

//                        Log.d("Check 14",)

                        RecyclerView recyclerView = findViewById(R.id.recycleViewApplication);
                        ApplicationAdapter adapter = new ApplicationAdapter(Applications.this, Applications.this.studentsList, Applications.this);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Applications.this));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }



    @Override
    public void clickAcceptButton(int position) {
        Intent init_intent = getIntent();
        this.projectID = init_intent.getStringExtra(ManageGroups.TAG);
        Log.d("Check 5",this.projectID);

        String clickStudentID=((StudentSupport)Applications.this.studentsList.get(position)).getStudentUID();
        for(String ob:Applications.this.projectSupport.getApplicationsList()) {
            if (ob.equalsIgnoreCase(clickStudentID)) {
                Applications.this.projectSupport.getApplicationsList().remove(ob);
                break;
            }
        }

        Applications.this.projectSupport.getStudentList().add(clickStudentID);

        Applications.this.mDatabase.child("Project").child(Applications.this.projectID).setValue(Applications.this.projectSupport);


    }

    @Override
    public void clickRejectButton(int position) {
        Intent init_intent = getIntent();
        this.projectID = init_intent.getStringExtra(ManageGroups.TAG);
        Log.d("Check 5",this.projectID);

        String clickStudentID=((StudentSupport)Applications.this.studentsList.get(position)).getStudentUID();
        for(String ob:Applications.this.projectSupport.getApplicationsList()) {
            if (ob.equalsIgnoreCase(clickStudentID)) {
                Applications.this.projectSupport.getApplicationsList().remove(ob);
                break;
            }
        }

        Applications.this.mDatabase.child("Project").child(Applications.this.projectID).setValue(Applications.this.projectSupport);

    }
}
