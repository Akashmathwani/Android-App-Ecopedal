package com.back4app.Ecopedal;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import static com.back4app.Ecopedal.MainActivity.isLogin;

public class loginActivity extends AppCompatActivity {

    EditText loginUsername;
    EditText loginPassword;

    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit Application?");
        alertDialogBuilder
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }


    public void LoginClick(View view) {

        loginUsername = (EditText) findViewById(R.id.LoginUsername);
        loginPassword = (EditText) findViewById(R.id.LoginPassword);



        if (loginUsername.getText().toString().equals("ep_m") && loginPassword.getText().toString().equals("iit")) {
            Log.i("LOGIN","MECHANIC LOGIN");
            Intent intent = new Intent(getApplicationContext(), mechanicActivity.class);
            startActivity(intent);
        } else {


            loginPassword.setError(null);

            // Login with Parse
            ParseUser.logInInBackground(loginUsername.getText().toString(), loginPassword.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser parseUser, ParseException e) {
                    if (parseUser != null) {
                        if (parseUser.getBoolean("emailVerified")) {

                            Toast.makeText(getApplicationContext(), "Login Sucessful" + "Welcome, " + loginUsername.getText().toString() + "!", Toast.LENGTH_SHORT).show();
                            Log.i("LOGIN", "Successful");
                            Intent intent = new Intent(getApplicationContext(), complaintActivity.class);
                            String sessionId = loginUsername.getText().toString();
                            intent.putExtra("user_email", sessionId);

                            if (isLogin.equals("false") || isLogin.equals("")) {

                                isLogin = "true";
                                Log.i("IsLogin", "true from login activity");

                            }
                            startActivity(intent);

                        } else {

                            ParseUser.logOut();
                            Toast.makeText(getApplicationContext(), "LOGIN failed " + "Please Verify Your Email first", Toast.LENGTH_SHORT).show();
                            Log.i("LOGIN", "Please Verify Your Email first");

                        }
                    }else {

                        ParseUser.logOut();
                        Toast.makeText(getApplicationContext(), e.getMessage() + " Please re-try", Toast.LENGTH_SHORT).show();
                        Log.i("LOGIN", "FAIL" + e.getMessage() + " Please re-try");

                    }
                }
            });
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //getSupportActionBar().hide();
    }


}
