package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class add extends AppCompatActivity {



        EditText appnamebtn,passbtn,userbtn;
        Button enterbtn;
        TextView titlebtn;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
            getSupportActionBar().hide(); // hide the title bar
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

            setContentView(R.layout.activity_add);

            userbtn = findViewById(R.id.add_username);
            appnamebtn = findViewById(R.id.add_appname);
            passbtn = findViewById(R.id.add_password);
            enterbtn = findViewById(R.id.buttonok);
            titlebtn = findViewById(R.id.title);

            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            int width = dm.widthPixels;
            getWindow().setLayout((int)(width*.9),(int)(width*1.2));
            getWindow().setBackgroundDrawableResource(android.R.color.transparent);


            enterbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(appnamebtn.getText().toString().trim().equals("")){
                        Toast.makeText(getApplicationContext(),"Enter Appliction Name",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(passbtn.getText().toString().trim().equals("")){
                        Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(!appnamebtn.getText().toString().trim().equals("SavePass")){

                        dbManager_Vault vault = new dbManager_Vault(getApplicationContext());
                        vault.addData(new Vault_data_POJO(passbtn.getText().toString().trim(),userbtn.getText().toString().trim(),appnamebtn.getText().toString().trim()));

                        Intent intent = new Intent(add.this,Add_password.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Change Application Name",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


