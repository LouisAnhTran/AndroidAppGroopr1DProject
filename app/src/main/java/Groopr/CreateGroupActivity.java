package Groopr;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Groopr.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Groopr.Model.Project;


// TESTTEST
public class CreateGroupActivity extends AppCompatActivity{
    //EditText groupNameInput, inputCapacity, inputGroupDescription, inputGroupSkill;

    TextInputEditText selectModule, groupNameInput, inputCapacity, inputGroupDescription, inputGroupSkill;
    Button createGroupFinalButton;

    Spinner modDropDownMenu;

    private DatabaseReference mDatabase;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = user.getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_group_page);

        groupNameInput = findViewById(R.id.groupNameInput);
        inputCapacity = findViewById(R.id.inputCapacity);
        inputGroupDescription = findViewById(R.id.inputGroupDescription);
        inputGroupSkill = findViewById(R.id.inputGroupSkill);
        selectModule = findViewById(R.id.selectModule);
        modDropDownMenu = findViewById(R.id.spinner2);
        createGroupFinalButton = findViewById(R.id.createGroupFinalButton);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        createGroupFinalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createGroup();
            }
        });

        // Adjust options of modDropDownMenu
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference studentRef = database.getReference("Student/" + uid);
        studentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Retrieve the pillar and term attributes from the Student node
                String pillar = dataSnapshot.child("pillar").getValue(String.class);
                int term = dataSnapshot.child("term").getValue(Integer.class);

                // Create an array of options based on the pillar and term attributes
                String[] options;
                if (term == 4) {
                    if (pillar.equals("CSD")) {
                        options = new String[] {"50.001 Information Systems & Programming", "50.002 Computation Structures", "50.004 Algorithms"};
                    } else {
                        options = new String[]{};
                    }
                } else if (term == 5) {
                    if (pillar.equals("CSD")) {
                        options = new String[] {"50.003 Elements of Software Construction", "50.005 Computer System Engineering"};
                    } else {
                        options = new String[] {};
                    }
                } else {
                    options = new String[] {};
                }

                // Set the options as the data source for the drop-down menu field
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(CreateGroupActivity.this, android.R.layout.simple_spinner_dropdown_item, options);
                modDropDownMenu.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle database error
                studentRef.addListenerForSingleValueEvent(this);

            }
        });

    }

    void createGroup(){
        String groupName = groupNameInput.getText().toString();
        Integer capacity = Integer.parseInt(inputCapacity.getText().toString());
        String groupDescription = inputGroupDescription.getText().toString();
        String groupSkill = inputGroupSkill.getText().toString();
        String moduleID = selectModule.getText().toString();


        boolean isValidated = validateData(groupName, groupDescription, groupSkill);
        if(!isValidated){
            return;
        }
        // If validated, proceed to create group
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("Student").child(uid);
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get the user's email address
                String studentID = dataSnapshot.child("studentID").getValue(String.class);
                Log.d("Test UID", studentID);
                // Use the studentID
                createGroupInFirebase(groupName, moduleID, new ArrayList<>(Arrays.asList(studentID)), capacity, groupDescription, groupSkill, studentID);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors here
                Log.e("FirebaseError", "Error getting user data", databaseError.toException());
            }
        });

    }

    void createGroupInFirebase(String groupName, String moduleID, List<String> studentList, int capacity, String groupDescription, String groupSkill, String currentID){

        Project p = new Project(groupName, moduleID, studentList, capacity, groupDescription, groupSkill, currentID);
        mDatabase.child("Project").push().setValue(p);

    }

    boolean validateData(String groupName, String groupDescription, String groupSkill){

        if(groupName.length() > 20){
            Toast.makeText(this, "Group name exceeds 20 characters", Toast.LENGTH_LONG).show();
            return false;
        }
        if (groupDescription.length() > 50){
            Toast.makeText(this, "Group description exceeds 50 characters", Toast.LENGTH_LONG).show();
            return false;
        }
        if (groupSkill.length() > 50){
            Toast.makeText(this, "Group skills exceed 50 characters", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}





