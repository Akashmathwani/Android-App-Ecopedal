package com.back4app.Ecopedal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import static com.back4app.Ecopedal.MainActivity.isLogin;

public class complaintActivity extends AppCompatActivity {


    String sessionId;

    public void registerComplaint(View view){
        EditText complaintSubject= (EditText) findViewById(R.id.complaintSubject);
        EditText complaintMessage= (EditText) findViewById(R.id.complaintMessage);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sessionId = extras.getString("user_email");
            //The key argument here must match that used in the other activity
        }


        if(complaintMessage.getText().toString().equals("")||complaintSubject.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Please Fill All the fields ",Toast.LENGTH_SHORT).show();
        }else {


            ParseObject complaint = new ParseObject("Complaint");
            complaint.put("Subject", complaintSubject.getText().toString());
            complaint.put("Username", sessionId);
            complaint.put("Message", complaintMessage.getText().toString());
            complaint.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Log.i("COMPLAINT", "Registered Successfully");
                        Toast.makeText(getApplicationContext(), "Complaint Registered for  " + sessionId, Toast.LENGTH_SHORT).show();
                    } else {
                        Log.i("COMPLAINT", e.getMessage());
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }// end od method regiscomplaint

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
       // getSupportActionBar().hide();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.About:
                return true;

            case R.id.Logout:

                ParseUser currentUser = ParseUser.getCurrentUser();
                currentUser.logOut();
                isLogin="false";
                Intent intent = new Intent(getApplicationContext(),loginActivity.class);
                startActivity(intent);
                return true;

            case R.id.Quit:
                onBackPressed();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

}
