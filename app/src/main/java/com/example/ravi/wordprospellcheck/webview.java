package com.example.ravi.wordprospellcheck;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class webview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String dis = getIntent().getExtras().getString("value");
        WebView display = new WebView(this);
        setContentView(display);
        String coll = "http://dictionary.reference.com/browse/"+dis;
        display.loadUrl(coll);
    }

}
