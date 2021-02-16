package com.example.cruddatebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    TextView textViewSignUp;
    EditText inputUser,inputPW;
    private FirebaseAuth mAuth;
    private Button btnLogin;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewSignUp = findViewById(R.id.textViewSignUp);
        inputUser = findViewById(R.id.inputUser);
        inputPW = findViewById(R.id.inputPW);
        btnLogin = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userLogin();
            }
        });

        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Register.class));
            }
        });

    }



    private void userLogin() {
        String valEmail = inputUser.getText().toString().trim();
        String valPassword = inputPW.getText().toString().trim();

        if (valEmail.isEmpty()) {
            inputUser.setError("Email is required!");
            inputUser.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(valEmail).matches()) {
            inputUser.setError("Invalid Email!");
            inputUser.requestFocus();
            return;
        }
        if (valPassword.isEmpty()) {
            inputPW.setError("Password is required!");
            inputPW.requestFocus();
            return;
        }
        if (valPassword.length() < 6) {
            inputPW.setError("Min password length should be 6 characters");
            inputPW.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(valEmail,valPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if (user.isEmailVerified()) {
                        startActivity(new Intent(MainActivity.this, HomePage.class));
                        finish();
                    }else{
                        user.sendEmailVerification();
                        Toast.makeText(MainActivity.this,"Check your email to verify your account!",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this,"Email or Password did not match!",Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.GONE);


            }
        });

    }
}