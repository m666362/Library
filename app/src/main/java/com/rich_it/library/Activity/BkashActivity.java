package com.rich_it.library.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.rich_it.library.R;

public class BkashActivity extends AppCompatActivity {
    Intent intent;
    String amountInString;
    TextView textView;
    WebView bkashWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bkash);
        setWebView();
        initObject();
        requiredTask();
    }

    private void setWebView() {
        bkashWebView = (WebView) findViewById(R.id.bkash_payment_webview);
        // Configure related browser settings
        bkashWebView.getSettings().setLoadsImagesAutomatically(true);
        bkashWebView.getSettings().setJavaScriptEnabled(true);
        bkashWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        bkashWebView.getSettings().setSupportZoom(true);
        bkashWebView.getSettings().setBuiltInZoomControls(true); // allow pinch to zooom
        bkashWebView.getSettings().setDisplayZoomControls(false); // disable the default zoom controls on the page
        // Configure the client to use when opening URLs
        bkashWebView.setWebViewClient(new WebViewClient());
        // Load the initial URL
        bkashWebView.loadUrl("https://www.example.com");
    }

    private void initObject() {
        intent = getIntent();
        amountInString = intent.getStringExtra("amountInString");
        textView = findViewById(R.id.amountInString);
    }

    private void requiredTask() {
        textView.setText(amountInString);
    }
}