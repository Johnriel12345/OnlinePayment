package com.example.cruddatebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    TextView textViewSignUp;
    EditText inputUser,inputEmail,inputPW,inputConfirmPW;
    Button btnRegister;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewSignUp = findViewById(R.id.textViewSignUp);
        inputUser = findViewById(R.id.inputUser);
        inputEmail = findViewById(R.id.inputEmail);
        inputPW = findViewById(R.id.inputPW);
        inputConfirmPW = findViewById(R.id.inputConfirmPW);

        btnRegister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("profile");



                String Uname = inputUser.getText().toString();
               String Mail = inputEmail.getText().toString();
               String Pword = inputPW.getText().toString();
               String Cpword = inputUser.getText().toString();

                UserHelperClass helperClass = new UserHelperClass(Uname,Mail,Pword,Cpword);

               reference.setValue("First data storage");


            }
        });

        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Register.class));
            }
        });
    }
}