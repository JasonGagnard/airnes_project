package fr.example.airnes_project.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import fr.example.airnes_project.R;

public class RegisterActivity extends AppCompatActivity {
    Button signUP;
    EditText prenom,nom,email,motdepasse,telephone;

    TextView signIn;

    ProgressBar progressBar;

    DatabaseHelper db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signUP =findViewById(R.id.login_btn);
        nom =findViewById(R.id.nom_reg);
        prenom =findViewById(R.id.prenom_reg);
        email =findViewById(R.id.email_reg);
        motdepasse =findViewById(R.id.motdepasse_reg);
        telephone =findViewById(R.id.telephone_reg);
        signIn = findViewById(R.id.sign_in);

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);




        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

            }
        });
        signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNom = nom.getText().toString();
                String userPrenom = prenom.getText().toString();
                String userEmail = email.getText().toString();
                String userMotdepasse = motdepasse.getText().toString();
                String userTelephone = telephone.getText().toString();

                // Vérifier si tous les champs sont remplis
                if (userNom.isEmpty() || userPrenom.isEmpty() || userEmail.isEmpty() || userMotdepasse.isEmpty() || userTelephone.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    // Vérifier si l'utilisateur existe déjà dans la base de données
                    boolean userExists = db.checkUser(userEmail);

                    if (userExists) {
                        // Si l'utilisateur existe déjà, afficher un message d'erreur
                        Toast.makeText(RegisterActivity.this, "Cet utilisateur existe déjà", Toast.LENGTH_SHORT).show();
                    } else {
                        // Si l'utilisateur est nouveau, l'ajouter dans la base de données
                        boolean insertSuccess = db.insertUser(userNom, userPrenom, userEmail, userMotdepasse, userTelephone);

                        if (insertSuccess) {
                            // Si l'insertion est réussie, afficher un message de confirmation
                            Toast.makeText(RegisterActivity.this, "Enregistré avec succès", Toast.LENGTH_SHORT).show();
                            // Rediriger vers l'activité de connexion
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        } else {
                            // Si l'insertion échoue, afficher un message d'erreur
                            Toast.makeText(RegisterActivity.this, "Échec de l'enregistrement", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}