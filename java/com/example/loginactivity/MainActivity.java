package com.example.loginactivity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mTextCnfPassword = (EditText)findViewById(R.id.edittext_cnf_password);
        mButtonRegister = (Button)findViewById(R.id.button_register);




                //last code start
        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);
        if(isFirstRun)
        {
            mButtonRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String user = mTextUsername.getText().toString().trim();
                    String pwd = mTextPassword.getText().toString().trim();
                    String cnf_pwd = mTextCnfPassword.getText().toString().trim();


                    if (pwd.equals(cnf_pwd) && user != null && !user.isEmpty() && !user.equals("null") && pwd != null && !pwd.isEmpty() && !pwd.equals("null") && cnf_pwd != null && !cnf_pwd.isEmpty() && !cnf_pwd.equals("null"))
                    {


                        long val = db.addUser(user, pwd);

                        if (val > 0) {
                            Toast.makeText(MainActivity.this, "you are Registered ", Toast.LENGTH_SHORT).show();
                            Intent moveToLogin = new Intent(MainActivity.this, RegisterActivity.class);
                            startActivity(moveToLogin);
                            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", false).commit();
                        } else {
                            Toast.makeText(MainActivity.this, "can't able to Register", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        if(mTextUsername.getText().toString().trim().equals("")){
                            Toast.makeText(getApplicationContext(),"Enter Username",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(mTextPassword.getText().toString().trim().equals("")){
                            Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(mTextCnfPassword.getText().toString().trim().equals("")){

                            Toast.makeText(getApplicationContext(),"Enter Confirm Password",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Enter Same Passwords", Toast.LENGTH_SHORT).show();
                        }
                    }
                }


            });
        }
        else
        {
            Intent moveToLogin = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(moveToLogin);

        }


    }
}








































































































































































