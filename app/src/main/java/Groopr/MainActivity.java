package Groopr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Groopr.Model.FactoryDesignForPillar.Pillar;
import Groopr.Model.FactoryDesignForPillar.ShapeFactory;

import com.example.Groopr.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    Button b1;
    private TextView editTextEmail;
    private TextView editTextPassWord;
    private TextView signUpNavigate;
    private TextView buttonSignIn;
    private final String emailPattern="^(.+)@(.+)$";
    private ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        editTextEmail=(EditText)findViewById(R.id.inputUserName);
        editTextPassWord=(EditText)findViewById(R.id.inputPassword);
        signUpNavigate=(TextView) findViewById(R.id.signUp);
        buttonSignIn=(Button)findViewById(R.id.buttonSignIN);
        signUpNavigate=(TextView)findViewById(R.id.signUp);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        signUpNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
            }
        });

//        ShapeFactory st=new ShapeFactory();
//        Pillar p1=st.getProduct("CSD");
//        Log.d("Test",p1.getTerm4ModuleList().toString());

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check=true;
                if(!editTextEmail.getText().toString().matches((MainActivity.this.emailPattern))){
                    editTextEmail.setError("Wrong Email Format");
                    check=false;
                }
                if(editTextPassWord.getText().toString().isEmpty() || editTextPassWord.getText().toString().length()<5){
                    editTextPassWord.setError("Password at least 6 characters wrong");
                    check=false;
                }
                if(check){
                    progressDialog.setMessage("Please wait while sign in...");
                    progressDialog.setTitle("Sign in in progress");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    mAuth.signInWithEmailAndPassword(editTextEmail.getText().toString(),editTextPassWord.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this,"Sign In successful",Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(MainActivity.this,HomePage.class);
                                startActivity(intent);
                            }
                            else{
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this,task.getException().toString(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });




    }

}