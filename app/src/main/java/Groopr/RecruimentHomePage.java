package Groopr;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
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

import Groopr.Model.FactoryDesignForPillar.Pillar;
import Groopr.Model.FactoryDesignForPillar.ShapeFactory;
import Groopr.Model.Student;

public class RecruimentHomePage extends AppCompatWithToolbar {
    private Button module1;
    private Button module2;
    private Button module3;
    private Button module4;

    private TextView title1;

    private DatabaseReference mDatabase;

    public static final String TAG="ModuleID";
    public static final String TAG1="FullModuleName";

    private Button project2d;

    private FirebaseUser user;
    public ArrayList<Student>  stList;
    public Student st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recruitment_page);

        module1=findViewById(R.id.module1);
        module2=findViewById(R.id.module2);
        module3=findViewById(R.id.module3);
        module4=findViewById(R.id.module4);
        title1=findViewById(R.id.titlePillarTerm);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar_recruitmentpage);

        // using toolbar as ActionBar
        setSupportActionBar(myToolbar);


        mDatabase= FirebaseDatabase.getInstance().getReference();
        user= FirebaseAuth.getInstance().getCurrentUser();

        module1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleClickButton(module1);
            }
        });

        module2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleClickButton(module2);
            }
        });

        module3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleClickButton(module3);
            }
        });

        module4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleClickButton(module4);
            }
        });


        mDatabase.child("Student").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                RecruimentHomePage.this.st=snapshot.getValue(Student.class);
                ShapeFactory st=new ShapeFactory();
                Pillar pl=st.getProduct(RecruimentHomePage.this.st.getPillar().toString(),RecruimentHomePage.this.st.getTerm().toString());

                RecruimentHomePage.this.title1.setText("Pillar: " + RecruimentHomePage.this.st.getPillar().toString()+ " - " + " Term: "+ RecruimentHomePage.this.st.getTerm().toString());
                ArrayList<Button> b1=new ArrayList<Button>();
                b1.add(RecruimentHomePage.this.module1);
                b1.add(RecruimentHomePage.this.module2);
                b1.add(RecruimentHomePage.this.module3);
                b1.add(RecruimentHomePage.this.module4);

                for(Button b: b1){
                    b.setEnabled(true);
                }

                int i=0;
                for(String ob:pl.getModuleList()){
                    b1.get(i).setText(ob);
                    i++;
                }
                for(int j=i;j<b1.size();j++){
                    b1.get(j).setText("You only have "+pl.getModuleList().size() + " core modules");
                    b1.get(j).setEnabled(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void handleClickButton(Button b1){
        Intent intent=new Intent(RecruimentHomePage.this,RecruimentShowGroup.class);
        intent.putExtra(RecruimentHomePage.TAG,b1.getText().subSequence(0,6));
        intent.putExtra(RecruimentHomePage.TAG1,b1.getText());
        startActivity(intent);
    }

    @Override
    protected int getCurrentMenuId() {
        return R.id.RecruitmentPage;
    }

}
