package com.rich_it.library.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.rich_it.library.R;

public class SplashActivity extends AppCompatActivity {

    private static int splashTimeOut = 500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        AndroidNetworking.initialize(getApplicationContext());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new Handler().postDelayed(new Runnable() {
                    // code to fetch user profile and data from db
                    // then start HomeActivity
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashActivity.this, PhoneNumberActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, splashTimeOut);
            }
        }, splashTimeOut);
        Toast.makeText(this, "Splash Activity", Toast.LENGTH_LONG).show();

    }
}