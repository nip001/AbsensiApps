package com.batch9.absensiapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button btnIn,btnOut,btnHistory,btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnHistory = findViewById(R.id.btnHistory);
        btnIn = findViewById(R.id.btnIn);
        btnOut = findViewById(R.id.btnOut);
        btnLogout = findViewById(R.id.btnLogout);

        btnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this,CheckInActivity.class);
                i.putExtra("username",getIntent().getStringExtra("username"));
                startActivity(i);
            }
        });
        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this,CheckInActivity.class);
                i.putExtra("username",getIntent().getStringExtra("username"));
                startActivity(i);
            }
        });
    }
}