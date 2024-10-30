package fr.example.airnes_project.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import fr.example.airnes_project.R;
import fr.example.airnes_project.models.UserModel;

public class RegisterActivity extends AppCompatActivity {
    Button signUP;
    EditText name,nom,email,password,phone;

    TextView signIn;

    FirebaseAuth auth;
    FirebaseDatabase database;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        signUP =findViewById(R.id.login_btn);
        nom =findViewById(R.id.nom_reg);
        name =findViewById(R.id.prenom_reg);
        email =findViewById(R.id.email_reg);
        password =findViewById(R.id.motdepasse_reg);
        phone =findViewById(R.id.telephone_reg);
        signIn = findViewById(R.id.sign_in);





        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

            }
        });

        signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateUser();
            }
        });
    }

    private void CreateUser() {
        String username = name.getText().toString();
        String useremail = email.getText().toString();
        String userpassword = password.getText().toString();

        if (TextUtils.isEmpty(username)){
            Toast.makeText(this,"remplir emplacement prénom", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(useremail)){
            Toast.makeText(this,"remplir emplacement email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userpassword)){
            Toast.makeText(this,"remplir emplacement mot de passe", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userpassword.length() < 6){
            Toast.makeText(this, "mot de passe trop court", Toast.LENGTH_SHORT).show();
            return;
        }
        auth.createUserWithEmailAndPassword(useremail,userpassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            UserModel userModel = new UserModel(username,useremail,userpassword);
                                    String id = task.getResult().getUser().getUid();
                                    database.getReference().child("Users").child(id).setValue(userModel);

                            Toast.makeText(RegisterActivity.this, "Enregistrement réussi", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "erreur"+task.getException(), Toast.LENGTH_SHORT).show();
                        }


                    }
                });
    }
}