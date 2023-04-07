package Groopr;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Groopr.Model.FactoryDesignForPillar.Pillar;
import Groopr.Model.FactoryDesignForPillar.ShapeFactory;
import Groopr.Model.Project;
import Groopr.Model.Student;


// TESTTEST
public class CreateGroupActivity extends AppCompatWithToolbar {
    //EditText groupNameInput, inputCapacity, inputGroupDescription, inputGroupSkill;

    TextInputEditText selectModule, groupNameInput, inputGroupDescription, inputGroupSkill;
    Spinner inputCapacity;
    Button createGroupFinalButton;

    Spinner modDropDownMenu;

    private DatabaseReference mDatabase;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = user.getUid();
    DatabaseReference studentRef = FirebaseDatabase.getInstance().getReference("Student").child(uid);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_group_page);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar_creategroup);

        // using toolbar as ActionBar
        setSupportActionBar(myToolbar);

        groupNameInput = findViewById(R.id.groupNameInput);
        inputCapacity = findViewById(R.id.spinner5);
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
                Intent intent = new Intent(CreateGroupActivity.this, MyGroupsActivity.class);
                startActivity(intent);
            }

        });

        // Adjust options of modDropDownMenu spinner
        DatabaseReference studentRef = FirebaseDatabase.getInstance().getReference("Student").child(uid);
        studentRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Retrieve the pillar and term attributes from the Student node
                String pillar = dataSnapshot.child("pillar").getValue().toString();
                String term = dataSnapshot.child("term").getValue().toString();
                ShapeFactory st = new ShapeFactory();
                Pillar p = st.getProduct(pillar, term);
                List<String> moduleList = p.getModuleList();
                // Create an array of options based on the pillar and term attributes
                ArrayList<String> options;
                options = (ArrayList)moduleList;
                // Set the options as the data source for the drop-down menu field
                ArrayAdapter<String> adapter = new ArrayAdapter<>(CreateGroupActivity.this, android.R.layout.simple_spinner_dropdown_item, options);
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
        Integer capacity = Integer.parseInt(inputCapacity.getSelectedItem().toString());
        String groupDescription = inputGroupDescription.getText().toString();
        String groupSkill = inputGroupSkill.getText().toString();
        String moduleID = modDropDownMenu.getSelectedItem().toString();


        boolean isValidated = validateData(groupName, groupDescription, groupSkill);
        if(!isValidated){
            return;
        }
        // If validated, proceed to create group
        studentRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get the user's email address
                String studentID = dataSnapshot.child("studentID").getValue(String.class);
                Log.d("Test UID", studentID);
                // Use the uid instead of studentID
                createGroupInFirebase(groupName, moduleID, new ArrayList<>(Arrays.asList(uid)), capacity, groupDescription, groupSkill, uid,new ArrayList<>(Arrays.asList("")));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors here
                Log.e("FirebaseError", "Error getting user data", databaseError.toException());
            }
        });

    }

    void createGroupInFirebase(String groupName, String moduleID, List<String> studentList, int capacity, String groupDescription, String groupSkill, String currentID,ArrayList<String> applicationsStudentIDList){

        Project p = new Project(groupName, moduleID, studentList, capacity, groupDescription, groupSkill, currentID,applicationsStudentIDList);
        String key = mDatabase.child("Project").push().getKey();
        mDatabase.child("Project").child(key).setValue(p);
        studentRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Student student = snapshot.getValue(Student.class);
                List<String> currentProjectList = student.getProjectList();
                currentProjectList.add(key);
                student.setProjectList(currentProjectList);
                studentRef.child("projectList").setValue(student.getProjectList());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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

    @Override
    protected int getCurrentMenuId() {
        return 0;
    }

}





