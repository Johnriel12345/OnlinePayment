package com.example.cruddatebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class sevenElevenb extends AppCompatActivity {


    ImageView mainImageView;
    TextView title, description;

    String data, data2;
    int myImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven_elevenb);

        mainImageView = findViewById(R.id.mainImageView);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);

        getData();
        setData();
    }

    private void getData(){
    if(getIntent().hasExtra("myImage")&& getIntent().hasExtra("data") &&
    getIntent().hasExtra("data2")){

        data = getIntent().getStringExtra("data");
        data2 = getIntent().getStringExtra("data2");
        myImage = getIntent().getIntExtra("myImage", 1);


    }else{
        Toast.makeText(this,"no Data.", Toast.LENGTH_SHORT).show();

    }

    }
    private void setData(){
    title.setText(data);
    description.setText(data2);
    mainImageView.setImageResource(myImage);

    }

}