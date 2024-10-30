package fr.example.airnes_project.ui;

import static fr.example.airnes_project.R.id.mdp_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import fr.example.airnes_project.MainActivity;
import fr.example.airnes_project.R;

public class LoginActivity extends AppCompatActivity {
    Button signIn;
    EditText email, password;
    TextView signUp;
    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        signUp = findViewById(R.id.sign_up);
        email = findViewById(R.id.email_login);
        password = findViewById(mdp_login);
        signIn = findViewById(R.id.login_btn);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginUser();


            }
        });
    }

    private void loginUser() {

        String useremail = email.getText().toString();
        String userpassword = password.getText().toString();


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
        auth.signInWithEmailAndPassword(useremail,userpassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "compte valide", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "erreur"+task.getException(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

}
