package com.rich_it.library.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.rich_it.library.Interface.BkashJSInterfaceClass;
import com.rich_it.library.Model.PaymentRequest;
import com.rich_it.library.Others.DialogCaller;
import com.rich_it.library.R;

public class BkashActivity extends AppCompatActivity {
    private static final String TAG = BkashActivity.class.getName();
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
        setWebView();
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

        bkashWebView.setClickable(true);
        bkashWebView.getSettings().setDomStorageEnabled(true);
        bkashWebView.getSettings().setAppCacheEnabled(false);
        bkashWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        bkashWebView.clearCache(true);
        bkashWebView.getSettings().setAllowFileAccessFromFileURLs(true);
        bkashWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);

        bkashWebView.addJavascriptInterface(new BkashJSInterfaceClass(BkashActivity.this), "KinYardsPaymentData");

        // Load the initial URL
        bkashWebView.loadUrl("https://www.bkash.com/terms-and-conditions");

        // Configure the client to use when opening URLs
        bkashWebView.setWebViewClient(new checkOutWebViewClient());
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

    private class checkOutWebViewClient extends WebViewClient {
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d(TAG, "shouldOverrideUrlLoading: " + url);
            if(url.equals("https://www.bkash.com/terms-and-conditions")){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            dialogCaller.showLoading();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            String paymentRequest = "{paymentRequest:" + request + "}";
            bkashWebView.loadUrl("javascript:callReconfigure(" + paymentRequest + " )");
            bkashWebView.loadUrl("javascript:clickPayButton()");
            dialogCaller.dismissDialog();
        }
    }

    @Override
    public void onBackPressed() {
        showCancelAlert();
    }

    private void showCancelAlert() {
        DialogCaller.showDialog(BkashActivity.this, "Alert", "Do you want to cancel the payment?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BkashActivity.super.onBackPressed();
            }
        });
    }
}