package com.example.Groopr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Groopr.Model.Student;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    private TextView editTextEmail,editTextFullName,editTextStudentID, editTextUserName,editTextTerm,editTextPillar, editTextPassWord,clickToSignUp;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        editTextEmail = findViewById(R.id.inputEmail);
        editTextFullName = findViewById(R.id.inputPersonName);
        editTextStudentID = findViewById(R.id.inputStudentID);
        editTextUserName = findViewById(R.id.inputUserName);
        editTextTerm = findViewById(R.id.inputTerm);
        editTextPillar = findViewById(R.id.inputPillar);
        editTextPassWord = findViewById(R.id.inputPassword);
        clickToSignUp = findViewById(R.id.buttonToSignUp);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        clickToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("Student").push().setValue(new Student(editTextStudentID.getText().toString(),editTextFullName.getText().toString(),editTextPillar.getText().toString(),editTextTerm.getText().toString(),editTextEmail.getText().toString(),editTextUserName.getText().toString(),editTextPassWord.getText().toString()));
                Intent intent=new Intent(SignUp.this,MainActivity.class);

            }
        });









    }
}
