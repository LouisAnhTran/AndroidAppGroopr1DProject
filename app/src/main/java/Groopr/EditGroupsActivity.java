package Groopr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.Groopr.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Groopr.Model.ProjectSupport;

public class EditGroupsActivity extends AppCompatWithToolbar {
    Button EditMemberButton, EditGroupButton;
    TextInputEditText inputGroupSkill, inputGroupDescription, groupNameInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_group_page);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar_editgroup);

        // using toolbar as ActionBar
        setSupportActionBar(myToolbar);

        EditMemberButton = findViewById(R.id.EditMemberButton);
        EditGroupButton = findViewById(R.id.EditGroupButton);
        inputGroupSkill = findViewById(R.id.inputGroupSkill);
        inputGroupDescription = findViewById(R.id.inputGroupDescription);
        groupNameInput = findViewById(R.id.groupNameInput);

        EditGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean success = editGroup();
                if (success) {
                    Intent intent = new Intent(EditGroupsActivity.this, MyGroupsActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    boolean editGroup(){
        String groupSkill = inputGroupSkill.getText().toString();
        String groupDescription = inputGroupDescription.getText().toString();
        String groupName = groupNameInput.getText().toString();

        Intent intent = getIntent();
        String projectID = intent.getStringExtra(ManageGroups.TAG);
        DatabaseReference projectRef = FirebaseDatabase.getInstance().getReference().child("Project").child(projectID);

        boolean isValidated = validateData(groupName, groupDescription, groupSkill);
        if(!isValidated) return false;
        projectRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ProjectSupport project = snapshot.getValue(ProjectSupport.class);
                if (!groupSkill.isEmpty()) project.setSkillNeeded(groupSkill);
                if (!groupDescription.isEmpty()) project.setMessage(groupDescription);
                if (!groupName.isEmpty()) project.setProjectName(groupName);
                projectRef.setValue(project);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return true;
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
