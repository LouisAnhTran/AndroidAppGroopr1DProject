package Groopr;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import Groopr.Model.Student;
import Groopr.Model.StudentSupport;

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

    /** drop down menu code **/
    final String[] term = {"4", "5"};
    final String[] pillar = {"CSD", "DAI", "ASD", "EPD", "ESD"};

    private AutoCompleteTextView dropDownTerm;
    private AutoCompleteTextView dropDownPillar;
    ArrayAdapter<String> adapterTerm;
    ArrayAdapter<String> adapterPillar;

    private ArrayList<StudentSupport> universalStudentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        editTextEmail = (EditText)findViewById(R.id.inputEmail);
        editTextFullName = (EditText)findViewById(R.id.inputPersonName);
        editTextStudentID = (EditText)findViewById(R.id.inputStudentID);
        editTextUserName = (EditText)findViewById(R.id.inputUserName);
        dropDownTerm = findViewById(R.id.inputTerm);
        dropDownPillar = findViewById(R.id.inputPillar);
        // editTextTerm = (EditText)findViewById(R.id.inputTerm);
        // editTextPillar = (EditText)findViewById(R.id.inputPillar);
        editTextPassWord = (EditText)findViewById(R.id.inputPassword);
        clickToSignUp = (Button)findViewById(R.id.buttonToSignUp);
        haveAccount=(TextView) findViewById(R.id.haveaccount2);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        /** drop down code **/

        adapterTerm = new ArrayAdapter<>(this, R.layout.list_items, term);
        dropDownTerm.setAdapter(adapterTerm);
        dropDownTerm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(SignUp.this, "Term: " + item, Toast.LENGTH_SHORT).show();
            }
        });

        adapterPillar = new ArrayAdapter<>(this, R.layout.list_items, pillar);
        dropDownPillar.setAdapter(adapterPillar);
        dropDownPillar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(SignUp.this, "Pillar: " + item, Toast.LENGTH_SHORT).show();
            }
        });

//        Navigate to sign in page if have account already
        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUp.this,MainActivity.class);
                startActivity(intent);
            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Student").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                SignUp.this.universalStudentList=new ArrayList<StudentSupport>();

                for(DataSnapshot ob: snapshot.getChildren()){
                    SignUp.this.universalStudentList.add(ob.getValue(StudentSupport.class));
                    Log.d("Check 100",ob.getValue(StudentSupport.class).toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        clickToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUp.this.performAuthentification();


            }
        });
    }

    private boolean checkOverlapAttribute(String fieldName,String attributeValue){
        if(fieldName.equalsIgnoreCase("studentID")){
            for(StudentSupport ob: this.universalStudentList){
                if(ob.getStudentID().equalsIgnoreCase(attributeValue)){
                    return false;
                }
            }
        }
        else if(fieldName.equalsIgnoreCase("userName")){
            for(StudentSupport ob:this.universalStudentList){
                if(ob.getUserName().equalsIgnoreCase(attributeValue)){
                    return false;
                }
            }
        }
        return true;
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
        String term=dropDownTerm.getText().toString();
        String pillar=dropDownPillar.getText().toString();
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
        if(!this.checkOverlapAttribute("userName",userName)){
            editTextUserName.setError("User name already exists");
            check=false;
        }
        if(this.checkStudentIDExpression(studentID)){
            editTextStudentID.setError("Student ID does not match format");
            check=false;
        }
        else if(!this.checkOverlapAttribute("studentID",studentID)){
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
                        mDatabase.child("Student").child(mAuth.getUid()).setValue(new Student(editTextStudentID.getText().toString(),editTextFullName.getText().toString(),dropDownPillar.getText().toString(),dropDownTerm.getText().toString(),editTextEmail.getText().toString(),editTextUserName.getText().toString(),editTextPassWord.getText().toString()));
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
