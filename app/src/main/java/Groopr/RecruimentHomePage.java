package Groopr;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

public class RecruimentHomePage extends AppCompatActivity {
    private Button module1;
    private Button module2;
    private Button module3;
    private Button module4;

    private DatabaseReference mDatabase;

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
        project2d=findViewById(R.id.project2Dbutton);


        mDatabase= FirebaseDatabase.getInstance().getReference();
        user= FirebaseAuth.getInstance().getCurrentUser();

        mDatabase.child("Student").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                RecruimentHomePage.this.st=snapshot.getValue(Student.class);
                ShapeFactory st=new ShapeFactory();
                Pillar pl=st.getProduct(RecruimentHomePage.this.st.getPillar().toString(),RecruimentHomePage.this.st.getTerm().toString());

                ArrayList<Button> b1=new ArrayList<Button>();
                b1.add(module1);
                b1.add(module2);
                b1.add(module3);
                b1.add(module4);

                int i=0;
                for(String ob:pl.getModuleList()){
                    b1.get(i).setText(ob);
                    i++;
                }
                for(int j=i;j<b1.size();j++){
                    b1.get(j).setText("You only have "+pl.getModuleList().size());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







    }
}
