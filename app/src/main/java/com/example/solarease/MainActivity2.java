package com.example.solarease;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity2 extends AppCompatActivity {

    private String MainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button inicio = findViewById(R.id.Button2);

        inicio.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent next = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(next);
            }
        });
    }
}