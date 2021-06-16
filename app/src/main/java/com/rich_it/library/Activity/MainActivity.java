package com.rich_it.library.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.rich_it.library.R;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button sendOtp;
    Button verifyOtpButton;
    EditText phoneNoEditText;
    EditText verifyOtpEt;
    private FirebaseAuth mAuth;
    String verificationCodeBySystem = "";
    String code = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initObjects();
        requiredTask();
    }

    private void requiredTask() {
        sendOtp.setOnClickListener(this::onClick);
        verifyOtpButton.setOnClickListener(this::onClick);
    }

    private void verifyCode(String codeByUser) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeBySystem, codeByUser);
        signInTheUserByCredentials(credential);
    }

    private void signInTheUserByCredentials(PhoneAuthCredential credential) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initObjects() {
        sendOtp = findViewById(R.id.sendOtp);
        verifyOtpButton = findViewById(R.id.verifyOtpButton);
        mAuth = FirebaseAuth.getInstance();
        phoneNoEditText = findViewById(R.id.phoneNoET);
        verifyOtpEt = findViewById(R.id.otpEt);
    }

    // callback fun of PhoneAuthProvider.verifyPhoneNumber(options)
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                @Override
                public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                    verificationCodeBySystem = s;
                }

                @Override
                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                    code = phoneAuthCredential.getSmsCode();
                    verifyCode(code);
                    Toast.makeText(MainActivity.this, code, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onVerificationFailed(FirebaseException e) {
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sendOtp:
                Toast.makeText(this, phoneNoEditText.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                String phoneNumber = "+88" + phoneNoEditText.getText().toString().trim();
                sendVerificationCodeToUser(phoneNumber);
                break;
            case R.id.verifyOtpButton:
                Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
                code = verifyOtpEt.getText().toString().trim();
                verifyCode(code);
                break;
        }
    }

    private void sendVerificationCodeToUser(String phoneNumber) {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneNumber)       // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(MainActivity.this)                 // Activity (for callback binding)
                .setCallbacks(mCallback)          // OnVerificationStateChangedCallbacks
                .build();

        PhoneAuthProvider.verifyPhoneNumber(options);
    }

}