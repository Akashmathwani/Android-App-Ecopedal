package com.back4app.Ecopedal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.parse.ParseInstallation;

public class MainActivity extends AppCompatActivity {

    //SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
    public static String isLogin="";

    public void LoginClick(View view){
        Button loginButton = (Button) findViewById(R.id.LoginButton);
        Intent intent = new Intent(getApplicationContext(),loginActivity.class);
        startActivity(intent);
    }

    public void SignupClick(View view){
        Button signupButton = (Button) findViewById(R.id.SignupButton);
        Intent intent = new Intent(getApplicationContext(),signupActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("IsLogin","## "+ isLogin);

        if(isLogin.equals("true")){
            Log.i("IsLogin","True");
            Intent intent = new Intent(MainActivity.this,complaintActivity.class);
            startActivity(intent);
        }

        // Save the current Installation to Back4App
        ParseInstallation.getCurrentInstallation().saveInBackground();
        getSupportActionBar().hide();

    }


}


