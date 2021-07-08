package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class pop extends AppCompatActivity {

    Button delete , cancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pop);

        delete = findViewById(R.id.buttondelete);
        cancle = findViewById(R.id.buttoncancle);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        getWindow().setLayout((int)(width*.9),(int)(width*0.9));
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        final String password = getIntent().getStringExtra("Password");
        final String application = getIntent().getStringExtra("Application");
        final String username = getIntent().getStringExtra("Username");
        final String id = getIntent().getStringExtra("Id");

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager_Vault db = new dbManager_Vault(getApplicationContext());
                db.delete(id);
                Intent intent = new Intent(pop.this,Add_password.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pop.this,Add_password.class);
                startActivity(intent);
            }
        });

    }

}
