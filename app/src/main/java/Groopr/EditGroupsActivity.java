package Groopr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                editGroup();
                Intent intent = new Intent(EditGroupsActivity.this, MyGroupsActivity.class);
                startActivity(intent);
            }
        });

    }

    void editGroup(){
        String groupSkill = inputGroupSkill.getText().toString();
        String groupDescription = inputGroupDescription.getText().toString();
        String groupName = groupNameInput.getText().toString();

        Intent intent = getIntent();
        String projectID = intent.getStringExtra(MyGroupsActivity.TAG);
        DatabaseReference projectRef = FirebaseDatabase.getInstance().getReference().child("Project").child(projectID);
        projectRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ProjectSupport project = snapshot.getValue(ProjectSupport.class);
                project.setSkillNeeded(groupSkill);
                project.setMessage(groupDescription);
                project.setProjectName(groupName);
                projectRef.setValue(project);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    protected int getCurrentMenuId() {
        return 0;
    }
}
