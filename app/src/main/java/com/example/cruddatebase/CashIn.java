package com.example.cruddatebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;

public class CashIn extends AppCompatActivity {

    RecyclerView recyclerview;

    String s1[], s2[];
    int images[] ={R.drawable.svnelvn,R.drawable.ministop,R.drawable.puregold,R.drawable.alfamart,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_in);

        recyclerview = findViewById(R.id.recycleview);

        s1 = getResources().getStringArray(R.array.OvertheCounter);
        s2 = getResources().getStringArray(R.array.OvertheCounter);

        myAdapter myadater = new myAdapter(this,s1,s2,images);
        recyclerview.setAdapter(myadater);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
    }
}