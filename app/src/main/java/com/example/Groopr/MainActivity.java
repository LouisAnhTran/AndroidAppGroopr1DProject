package com.example.Groopr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.Groopr.Model.Student;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();


        Student student=new Student("100032","nadas","CSD","4","dsad@gmail.com","dada","daasd");
        student.getProjectList().add("");

        mDatabase.child("Student").push().setValue(student);

        mDatabase.child("Student").orderByChild("studentID").equalTo("100032").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Student> l1=new ArrayList<Student>();
                for(DataSnapshot l2:snapshot.getChildren()){
                    Student s1=l2.getValue(Student.class);
                    l1.add(s1);
                }
                String statement="";
                for(Student s1: l1){
                    statement+=s1.getPassword();
                }
                Log.d("Nana",statement);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}