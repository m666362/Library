package com.rich_it.library.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.rich_it.library.R;

public class VerifyUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_user);
        String phoneNumber = getIntent().getStringExtra("phoneNumber");

    }
}