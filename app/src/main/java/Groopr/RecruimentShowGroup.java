package Groopr;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Groopr.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Groopr.Model.Project;
import Groopr.Model.RecruitmentAdapter;

public class RecruimentShowGroup extends AppCompatActivity {
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recruitment_page_2_connect);



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

                RecruitmentAdapter adapter=new RecruitmentAdapter(RecruimentShowGroup.this,p1);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(RecruimentShowGroup.this));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}
