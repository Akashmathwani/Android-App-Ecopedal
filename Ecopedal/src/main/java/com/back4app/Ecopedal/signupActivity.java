package com.back4app.Ecopedal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class signupActivity extends AppCompatActivity {


    public void alertDisplayer(String title, String message, final boolean error){
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        if(!error) {
                            Intent intent = new Intent(getApplicationContext(), loginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }

    public void SignupClick(View view){
        EditText signupUsername = (EditText) findViewById(R.id.SignupUsername);
        EditText signupPassword = (EditText) findViewById(R.id.SignupPassword);
        EditText signupCPassword = (EditText) findViewById(R.id.SignupCPassword);

        //Check if both password matches


        if(!signupPassword.getText().toString().equals(signupCPassword.getText().toString())){

            Toast.makeText(getApplicationContext(),"Password Don't Match",Toast.LENGTH_SHORT).show();
            signupPassword.setText("");
            signupCPassword.setText("");

        }else{

            //If password matches check for sign up

            try {
                // Reset errors
                signupUsername.setError(null);
                signupPassword.setError(null);

                // Sign up with Parse
                ParseUser user = new ParseUser();
                user.setUsername(signupUsername.getText().toString());
                user.setPassword(signupPassword.getText().toString());
                user.setEmail(signupUsername.getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            ParseUser.logOut();
                            Toast.makeText(getApplicationContext(),"Account Created Successfully!"+"Please verify your email before Login", Toast.LENGTH_SHORT).show();
                            Log.i("SIGN UP"," Successfull!");
                            Intent intent = new Intent(getApplicationContext(),loginActivity.class);
                            startActivity(intent );
                            //alertDisplayer("A","B?",true);
                        } else {
                            ParseUser.logOut();
                            Toast.makeText(getApplicationContext(),"Error Account Creation failed" + " :" + e.getMessage(), Toast.LENGTH_LONG).show();
                            Log.i("SIGN UP", " FAILED ");
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();

            }

        }
        //end of else

    }//end of signup method


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "new features to be added", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        getSupportActionBar().hide();
    }

}
