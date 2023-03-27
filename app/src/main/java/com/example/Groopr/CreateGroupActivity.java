package com.example.Groopr;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Groopr.Model.Project;
import com.example.Groopr.Model.Student;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


// TESTTEST
public class CreateGroupActivity extends AppCompatActivity{
    EditText groupNameInput, inputCapacity, inputGroupDescription, inputGroupSkill;
    MaterialButton createGroup;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_group_page);

        groupNameInput = findViewById(R.id.groupNameInput);
        inputCapacity = findViewById(R.id.inputCapacity);
        inputGroupDescription = findViewById(R.id.inputGroupDescription);
        inputGroupSkill = findViewById(R.id.inputGroupSkill);
        createGroup = findViewById(R.id.createGroup);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        createGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if
            }
        });

    }

    void CreateGroup(){
        String groupName = groupNameInput.getText().toString();
        Integer capacity = Integer.parseInt(inputCapacity.getText().toString());
        String groupDescription = inputGroupDescription.getText().toString();
        String groupSkill = inputGroupSkill.getText().toString();

        boolean isValidated = validateData(groupName, groupDescription, groupSkill);
        if(!isValidated){
            return;
        }
    }

    void createGroupInFirebase(){


    }

  /*
     Project(String projectName, String moduleID, List<String> studentList, int maxNumberOfMember, String message, String skillNeeded,String teamLeaderID) {
        this.projectName = projectName;
     }
            this.moduleID = moduleID;
              this.studentList = studentList;
              this.maxNumberOfMember = maxNumberOfMember;
              this.message = message;
              this.skillNeeded = skillNeeded;
              this.teamLeaderId=teamLeaderID;
      @param groupName
      @param groupDescription
      @param groupSkill
*/





/*      FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();*/













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





