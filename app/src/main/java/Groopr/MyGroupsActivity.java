package Groopr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Groopr.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Groopr.Model.FactoryDesignForPillar.Pillar;
import Groopr.Model.FactoryDesignForPillar.ShapeFactory;

public class MyGroupsActivity extends AppCompatActivity {
    Button createGroupButton;

    TextView module1, module2, module3, module4;

    private DatabaseReference mDatabase;

/*    public final static String ID_KEY = "ID_KEY";*/
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = user.getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_groups);

        createGroupButton = findViewById(R.id.createGroupButton);
        module1 = findViewById(R.id.module1);
        module2 = findViewById(R.id.module2);
        module3 = findViewById(R.id.module3);
        module4 = findViewById(R.id.module4);

        TextView[] moduleTextViewList = {module1, module2, module3, module4};

        // Create Group button
        createGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MyGroupsActivity.this, CreateGroupActivity.class);
                startActivity(intent);
            }
        });

        // Select module headers to show depeneding on pillar and term

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
                int i = 0;
                for (String s: moduleList){
                    moduleTextViewList[i].setText(s);
                    i++;
                }
                for (int j = i; j < moduleList.size(); j++){
                    moduleTextViewList[j].setText("");
                }
            }
/*                for (int i=0; i < moduleList.size(); i++){
                    moduleTextViewList[i].setText(moduleList.get(i));
                }
                for (TextView t: moduleTextViewList){
                    if (t.getText() ==
                }*/



            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                studentRef.addListenerForSingleValueEvent(this);
            }


        });

    }
}

