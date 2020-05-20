package com.example.indianelection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = findViewById(R.id.button2);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == b)
        {
            Intent i= new Intent(MainActivity.this, Main2Activity.class);
            startActivity(i);

        }

    }
}
