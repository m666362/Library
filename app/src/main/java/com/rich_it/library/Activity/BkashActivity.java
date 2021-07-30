package com.rich_it.library.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.rich_it.library.Model.PaymentRequest;
import com.rich_it.library.Others.DialogCaller;
import com.rich_it.library.R;

public class BkashActivity extends AppCompatActivity {
    Intent intent;
    String amountInString;
    String request;
    TextView textView;
    WebView bkashWebView;
    WebSettings webSettings;
    PaymentRequest paymentRequest;

    String dialogTitle = "ERROR!!!";
    String dialogMessage = "1. Wrong refer code! \n2. Please check your internet Connection";
    DialogCaller dialogCaller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bkash);

        if(getIntent().getExtras() == null){
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            return;
        }
        initObject();
        requiredTask();
        setWebView();
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

        bkashWebView.setClickable(true);
        bkashWebView.getSettings().setDomStorageEnabled(true);
        bkashWebView.getSettings().setAppCacheEnabled(false);
        bkashWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        bkashWebView.clearCache(true);
        bkashWebView.getSettings().setAllowFileAccessFromFileURLs(true);
        bkashWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);

//        bkashWebView.addJavascriptInterface(new BkashJava);

        // Load the initial URL
        bkashWebView.loadUrl("https://www.example.com");

        // Configure the client to use when opening URLs
        bkashWebView.setWebViewClient(new WebViewClient());
    }

    private void initObject() {
        intent = getIntent();
        textView = findViewById(R.id.amountInString);
        paymentRequest = new PaymentRequest();
        dialogCaller = new DialogCaller(BkashActivity.this);

    }

    private void requiredTask() {
        amountInString = intent.getStringExtra("amountInString");
        textView.setText(amountInString);
        paymentRequest.setAmount(amountInString);
        paymentRequest.setIntent("sale");
        Gson gson = new Gson();
        request = gson.toJson(paymentRequest);
        webSettings = bkashWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}