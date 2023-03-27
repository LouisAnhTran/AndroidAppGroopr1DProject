package Groopr;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import Groopr.Model.Student;

import com.example.Groopr.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SignUp extends AppCompatActivity {
    private TextView editTextEmail,editTextFullName,editTextStudentID, editTextUserName,editTextTerm,editTextPillar, editTextPassWord,clickToSignUp;
    private DatabaseReference mDatabase;

    private final String emailPattern="^(.+)@(.+)$";

    private TextView haveAccount;

    private ProgressDialog progressDialog;
    private boolean checkOverlap;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        editTextEmail = (EditText)findViewById(R.id.inputEmail);
        editTextFullName = (EditText)findViewById(R.id.inputPersonName);
        editTextStudentID = (EditText)findViewById(R.id.inputStudentID);
        editTextUserName = (EditText)findViewById(R.id.inputUserName);
        editTextTerm = (EditText)findViewById(R.id.inputTerm);
        editTextPillar = (EditText)findViewById(R.id.inputPillar);
        editTextPassWord = (EditText)findViewById(R.id.inputPassword);
        clickToSignUp = (Button)findViewById(R.id.buttonToSignUp);
        haveAccount=(TextView) findViewById(R.id.haveaccount2);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

//        Navigate to sign in page if have account already
        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUp.this,MainActivity.class);
                startActivity(intent);
            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference();



        clickToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUp.this.performAuthentification();


            }
        });
    }

    private boolean checkOverlapAttribute(String attributeValue){
        mDatabase=FirebaseDatabase.getInstance().getReference();
        checkOverlap=true;
        mDatabase.child("Student").orderByChild(attributeValue).equalTo(attributeValue).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Student> listStudent=new ArrayList<Student>();
                for(DataSnapshot object:snapshot.getChildren()){
                    listStudent.add(object.getValue(Student.class));
                }
                if(listStudent.size()==0){
                    Log.d("OurInfo",listStudent.toString());
                    SignUp.this.checkOverlap=false;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return false;
    }

    private boolean checkStudentIDExpression(String studentID){
        if(studentID.length()!=7 || !studentID.substring(0,3).equalsIgnoreCase(new String("100"))){
            return true;
        }
        return false;
    }

    private void performAuthentification() {
        String email=editTextEmail.getText().toString();
        String fullName=editTextFullName.getText().toString();
        String studentID=editTextStudentID.getText().toString();
        String userName=editTextUserName.getText().toString();
        String term=editTextTerm.getText().toString();
        String pillar=editTextPillar.getText().toString();
        String password=editTextPassWord.getText().toString();
        boolean check=true;
        if(!email.matches(this.emailPattern)){
            editTextEmail.setError("Wrong Email Format");
            check=false;
        }
        if(password.isEmpty() || password.length()<5){
            editTextPassWord.setError("Password at least 6 characters wrong");
            check=false;
        }
        if(this.checkOverlapAttribute(userName)){
            editTextUserName.setError("User name already exists");
        }
        if(this.checkStudentIDExpression(studentID)){
            editTextStudentID.setError("Student ID does not match format");
            check=false;
        }
        else if(this.checkOverlapAttribute(studentID)){
            editTextStudentID.setError("This student ID already exists");
            check=false;
        }

        if(check){
            progressDialog.setMessage("Please wait while registration...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        Toast.makeText(SignUp.this,"Registration successful",Toast.LENGTH_LONG).show();
                        mDatabase.child("Student").push().setValue(new Student(editTextStudentID.getText().toString(),editTextFullName.getText().toString(),editTextPillar.getText().toString(),editTextTerm.getText().toString(),editTextEmail.getText().toString(),editTextUserName.getText().toString(),editTextPassWord.getText().toString()));
                        Intent intent=new Intent(SignUp.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        progressDialog.dismiss();
                        Toast.makeText(SignUp.this,task.getException().toString(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }





    }
}
