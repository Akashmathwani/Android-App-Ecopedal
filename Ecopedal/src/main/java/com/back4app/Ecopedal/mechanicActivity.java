package com.back4app.Ecopedal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class mechanicActivity extends AppCompatActivity {

    /*
    String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Max OS X"};
    ArrayAdapter adapter;
    */
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic);
        /*

        adapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, mobileArray);

        ListView listView = (ListView) findViewById(R.id.Listview);
        listView.setAdapter(adapter);


        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        */

        webview = (WebView) findViewById(R.id.webview);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("https://back4app.com/");

        WebSettings websettings = webview.getSettings();
        websettings.setJavaScriptEnabled(true);

    }

    @Override
    public void onBackPressed() {
        if(webview.canGoBack()){
            webview.goBack();
        }else{
            super.onBackPressed();
        }

    }
}
