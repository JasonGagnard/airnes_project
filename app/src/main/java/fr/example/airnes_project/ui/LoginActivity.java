package fr.example.airnes_project.ui;

import static fr.example.airnes_app.R.id.motdepasse_login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import fr.example.airnes_project.MainActivity;
import fr.example.airnes_project.R;

public class LoginActivity extends AppCompatActivity {
    Button signIn;
    EditText email, motdepasse;

    TextView signUp;
    private int mdp_login;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signUp = findViewById(R.id.sign_up);
        email = findViewById(R.id.email_login);
        motdepasse = findViewById(mdp_login);
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
                String userEmail = email.getText().toString();
                String userMotdepasse = motdepasse.getText().toString();

                if (userEmail.isEmpty() || userMotdepasse.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isValid = db.checkUser(userEmail, userMotdepasse);
                    if (isValid) {
                        Toast.makeText(LoginActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                        // Rediriger vers l'activité principale ou une autre activité
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }

}
