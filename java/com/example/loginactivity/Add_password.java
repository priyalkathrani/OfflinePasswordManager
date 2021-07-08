package com.example.loginactivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;


public class Add_password extends AppCompatActivity {


    ListView listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_addpassword);



        listview = findViewById(R.id.listview);

        dbManager_Vault db = new dbManager_Vault(this);
        List<Vault_data_POJO> allData = db.getAllData();
        final String[] application = new String[allData.size()];
        final String[] username = new String[allData.size()];
        final String[] password = new String[allData.size()];
        final String[] ids = new String[allData.size()];

        for(int i=0;i<allData.size();i++){
            application[i] = allData.get(i).application;
            username[i] = allData.get(i).username;
            password[i] = allData.get(i).password;
            ids[i] = allData.get(i).id;
        }

        MyListAdapter adapter=new MyListAdapter(this,application,username,password);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {


                Intent intent = new Intent(getApplicationContext(),pop.class);
                intent.putExtra("Application",application[position]);
                intent.putExtra("Password",password[position]);
                intent.putExtra("Username",username[position]);
                intent.putExtra("Id",ids[position]);
                startActivity(intent);



            }
        });



        findViewById(R.id.button_to_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add_password.this,add.class);
                startActivity(intent);
                getWindow().setBackgroundDrawableResource(android.R.color.transparent);

            }
        });
    }

}



