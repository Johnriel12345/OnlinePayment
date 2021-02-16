package com.example.cruddatebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;

public class Register extends AppCompatActivity {

    private Button btnRegisterUser;
    private ProgressBar progressBar;
    private EditText textUsername, textEmail, textPassword, textPasswordConfirm;
    private FirebaseAuth mAuth;
    String valUsername, valEmail, valPassword, valConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegisterUser = findViewById(R.id.btnRegister);
        progressBar = findViewById(R.id.progressBar2);
        mAuth = FirebaseAuth.getInstance();
        textUsername = findViewById(R.id.inputUser);
        textEmail = findViewById(R.id.inputEmail);
        textPassword = findViewById(R.id.inputPW);
        textPasswordConfirm = findViewById(R.id.inputConfirmPW);

        btnRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });


    }


    private void Register() {
        valUsername = textUsername.getText().toString().trim();
        valEmail = textEmail.getText().toString().trim();
        valPassword = textPassword.getText().toString().trim();
        valConfirmPassword = textPasswordConfirm.getText().toString().trim();

        if(valUsername.isEmpty()){
            textUsername.setError("Username is required!");
            textUsername.requestFocus();
            return;
        }

        if(valEmail.isEmpty()){
            textEmail.setError("Email is required!");
            textEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(valEmail).matches()){
            textEmail.setError("Invalid Email!");
            textEmail.requestFocus();
            return;
        }
        if(valPassword.isEmpty()){
            textPassword.setError("Password is required!");
            textPassword.requestFocus();
            return;
        }

        if(valPassword.length()<6){
            textPassword.setError("Min password length should be 6 characters");
            textPassword.requestFocus();
            return;
        }

        if(!valConfirmPassword.equals(valPassword)){
            textPasswordConfirm.setError("Password did not Matched");
            textPasswordConfirm.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(valEmail,valPassword)
                .addOnCompleteListener(task -> {

                    if (task.isSuccessful()) {
                        UserHelperClass user = new UserHelperClass(valUsername, valEmail);

                        FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .child("Account")
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {
                                    String text = "Registered Successfully \n Please log in to verify your email";
                                    Intent intent = new Intent(Register.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();


                                } else {
                                    Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                    } else {
                        Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                    progressBar.setVisibility(View.GONE);
                });
    }
}