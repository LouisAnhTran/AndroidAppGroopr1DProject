package Groopr;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import Groopr.Model.Student;

public class MyProfile extends AppCompatActivity {

    private FirebaseUser user;

    private DatabaseReference mDatabase;

    private TextView userName;

    private TextView aboutYou;

    private TextView skill;

    private TextView email;

    private TextView id;

    private Button updateInfo;

    private ImageView imageProfile;

    private ArrayList<Integer> listImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile);

        user= FirebaseAuth.getInstance().getCurrentUser();

        mDatabase= FirebaseDatabase.getInstance().getReference();

        aboutYou=(EditText)findViewById(R.id.aboutYouProfile);
        skill=(EditText)findViewById(R.id.skillSetProfile);
        email=(EditText)findViewById(R.id.emailInputProfile);
        id=(EditText)findViewById(R.id.inputIdProfile);
        updateInfo=findViewById(R.id.profileButtonFinal);
        userName=findViewById(R.id.userNameProfile);
        imageProfile=findViewById(R.id.userImageProfile);
        listImage=new ArrayList<Integer>();
        listImage.add(R.drawable.robot8);
        listImage.add(R.drawable.robot7);
        listImage.add(R.drawable.robot6);
        listImage.add(R.drawable.robot5);
        listImage.add(R.drawable.robot4);
        listImage.add(R.drawable.robot3);
        listImage.add(R.drawable.robot2);
        listImage.add(R.drawable.robot1);



        mDatabase.child("Student").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Student st=snapshot.getValue(Student.class);
                userName.setText("@"+st.getUserName());
                aboutYou.setText(st.getAboutYou().toString());
                skill.setText(st.getSkillSet().toString());
                email.setText(st.getEmailAddress().toString());
                id.setText(st.getStudentID());
                email.setEnabled(false);
                id.setEnabled(false);
                Random rand=new Random();
                int upperbound=listImage.size();
                int int_random = rand.nextInt(upperbound);
                imageProfile.setImageResource(listImage.get(int_random));


                Log.d("TAG",aboutYou.getHint().toString());
                Log.d("TAG1",skill.getHint().toString());
                Log.d("TAG2",st.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        updateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyProfile.this.mDatabase=FirebaseDatabase.getInstance().getReference();

                MyProfile.this.user=FirebaseAuth.getInstance().getCurrentUser();

                MyProfile.this.mDatabase.child("Student").child(MyProfile.this.user.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Student st=snapshot.getValue(Student.class);
                        st.setAboutYou(MyProfile.this.aboutYou.getText().toString());
                        st.setSkillSet(MyProfile.this.skill.getText().toString());

                        MyProfile.this.mDatabase.child("Student").child(MyProfile.this.user.getUid()).setValue(st);

                        Intent intent=new Intent(MyProfile.this,HomePage.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });






    }

}
