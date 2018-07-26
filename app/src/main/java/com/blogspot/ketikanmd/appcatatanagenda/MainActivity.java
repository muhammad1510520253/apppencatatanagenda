package com.blogspot.ketikanmd.appcatatanagenda;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {
    AnimationDrawable animationDrawable;

    LinearLayout daftaragenda, agendapenting, about;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        daftaragenda = findViewById(R.id.daftaragenda);
        about = findViewById(R.id.about);
        getSupportActionBar().hide();
        agendapenting = findViewById(R.id.agendapenting);
        daftaragenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DaftarAgenda.class));
            }
        });
        agendapenting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AgendaPenting.class));
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, About.class));
            }
        });


    }


}
