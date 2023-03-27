package Groopr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Groopr.Model.Student;

import com.example.Groopr.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    Button b1;
    private TextView editTextUserName;
    private TextView editTextPassWord;
    private TextView signUpNavigate;
    private TextView buttonSignIn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        editTextUserName=(EditText)findViewById(R.id.inputUserName);
        editTextPassWord=(EditText)findViewById(R.id.inputPassword);
        signUpNavigate=(TextView) findViewById(R.id.signUp);
        buttonSignIn=(Button)findViewById(R.id.buttonSignIN);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase=FirebaseDatabase.getInstance().getReference();
//                query=query.orderByChild("userName").equalTo(editTextUserName.getText().toString());
                mDatabase.child("Student").orderByChild("password").equalTo(editTextPassWord.getText().toString()).orderByChild("username").equalTo(editTextUserName.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot==null){
                            Toast.makeText(MainActivity.this, R.string.invalid_login_information,Toast.LENGTH_LONG).show();
                        }
                        else {
                            ArrayList<Student> l1=new ArrayList<Student>();
                            for(DataSnapshot t1 : snapshot.getChildren()){
                                Student st=t1.getValue(Student.class);
                                l1.add(st);
                            }
                            Log.d("OurInfo",l1.toString());
                            Intent intent=new Intent(MainActivity.this,SignUp.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });






//        mDatabase = FirebaseDatabase.getInstance().getReference();
//
//
//        Student student=new Student("100032","nadas","CSD","4","dsad@gmail.com","dada","daasd");
//        student.getProjectList().add("");
//
//        mDatabase.child("Student").push().setValue(student);
//
//        mDatabase.child("Student").orderByChild("studentID").equalTo("100032").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                ArrayList<Student> l1=new ArrayList<Student>();
//                for(DataSnapshot l2:snapshot.getChildren()){
//                    Student s1=l2.getValue(Student.class);
//                    l1.add(s1);
//                }
//                String statement="";
//                for(Student s1: l1){
//                    statement+=s1.getPassword();
//                }
//                Log.d("Nana",statement);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });



    }

}