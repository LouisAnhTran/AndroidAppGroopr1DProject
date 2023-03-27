package com.example.Groopr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Groopr.Model.Student;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    private TextView editTextEmail,editTextFullName,editTextStudentID, editTextUserName,editTextTerm,editTextPillar, editTextPassWord,clickToSignUp;
    private DatabaseReference mDatabase;

    // drop down menu code
    final String[] term = {"4", "5"};
    final String[] pillar = {"CSD", "DAI", "ASD", "EPD", "ESD"};

    private AutoCompleteTextView dropDownTerm;
    private AutoCompleteTextView dropDownPillar;
    ArrayAdapter<String> adapterTerm;
    ArrayAdapter<String> adapterPillar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        dropDownTerm = findViewById(R.id.inputTerm);
        adapterTerm = new ArrayAdapter<>(this, R.layout.list_items, term);
        dropDownTerm.setAdapter(adapterTerm);
        dropDownTerm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(SignUp.this, "Term: " + item, Toast.LENGTH_SHORT).show();
            }
        });

        dropDownPillar = findViewById(R.id.inputPillar);
        adapterPillar = new ArrayAdapter<>(this, R.layout.list_items, pillar);
        dropDownPillar.setAdapter(adapterPillar);
        dropDownPillar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(SignUp.this, "Pillar: " + item, Toast.LENGTH_SHORT).show();
            }
        });

        editTextEmail = findViewById(R.id.inputEmail);
        editTextFullName = findViewById(R.id.inputPersonName);
        editTextStudentID = findViewById(R.id.inputStudentID);
        editTextUserName = findViewById(R.id.inputUserName);
        //editTextTerm = findViewById(R.id.inputTerm);
        //editTextPillar = findViewById(R.id.inputPillar);
        editTextPassWord = findViewById(R.id.inputPassword);
        clickToSignUp = findViewById(R.id.buttonToSignUp);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        clickToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("Student").push().setValue(new Student(editTextStudentID.getText().toString(),editTextFullName.getText().toString(),dropDownPillar.getText().toString(),dropDownTerm.getText().toString(),editTextEmail.getText().toString(),editTextUserName.getText().toString(),editTextPassWord.getText().toString()));
                Intent intent=new Intent(SignUp.this,MainActivity.class);

            }
        });









    }
}
