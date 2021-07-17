package com.example.thinkinggumv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button randomBut, viewBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        randomBut = (Button) findViewById(R.id.randomBut);
        viewBut = (Button) findViewById(R.id.viewBut);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == randomBut.getId()) {
            randomAction();
        }
        if (id == viewBut.getId()) {
            viewAction();
        }
    }

    private void viewAction() {
        Intent i = new Intent(this, TopicActivity.class);
        startActivity(i);
    }

    private void randomAction() {

    }
}