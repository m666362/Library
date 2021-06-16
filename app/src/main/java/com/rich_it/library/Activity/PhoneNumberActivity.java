package com.rich_it.library.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rich_it.library.R;

public class PhoneNumberActivity extends AppCompatActivity implements View.OnClickListener{

    Button sendOtp;
    EditText phoneNoEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);
        initObjects();
        requiredTask();
    }

    private void initObjects() {
        sendOtp = findViewById(R.id.sendOtpButton);
        phoneNoEt = findViewById(R.id.phoneNoET);
    }

    private void requiredTask() {
        sendOtp.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sendOtpButton:
                // todo: show loading
                String phoneNumber = "+88" + phoneNoEt.getText().toString().trim();
                Intent intent = new Intent(this, VerifyOtpActivity.class);
                intent.putExtra("phoneNumber", phoneNumber);
                startActivity(intent);
                // sendVerificationCodeToUser(phoneNumber);
                break;
        }
    }
}