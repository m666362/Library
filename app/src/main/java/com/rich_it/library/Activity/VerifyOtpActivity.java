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
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.rich_it.library.R;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class VerifyOtpActivity extends AppCompatActivity implements View.OnClickListener {
    Button verifyOtpButton;
    EditText verifyOtpEt;
    private FirebaseAuth mAuth;
    String verificationCodeBySystem = "";
    String code = "";
    String phoneNumber = "";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        initObjects();
        requiredTask();
        sendVerificationCodeToUser(phoneNumber);
    }

    private void requiredTask() {
        verifyOtpButton.setOnClickListener(this::onClick);
    }

    private void verifyCode(String codeByUser) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeBySystem, codeByUser);
        signInTheUserByCredentials(credential);
    }

    private void signInTheUserByCredentials(PhoneAuthCredential credential) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(VerifyOtpActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                Intent intent = new Intent(VerifyOtpActivity.this, UserInformationActivity.class);
                intent.putExtra("phoneNumber", phoneNumber);
                startActivity(intent);
            }
        });
    }

    private void initObjects() {
        intent = getIntent();
        phoneNumber = intent.getStringExtra("phoneNumber");
        verifyOtpButton = findViewById(R.id.verifyOtpButton);
        mAuth = FirebaseAuth.getInstance();
        verifyOtpEt = findViewById(R.id.otpEt);
    }

    // callback fun of PhoneAuthProvider.verifyPhoneNumber(options)
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                @Override
                public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    verificationCodeBySystem = s;
                }

                @Override
                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                    code = phoneAuthCredential.getSmsCode();
                    verifyCode(code);
                }

                @Override
                public void onVerificationFailed(FirebaseException e) {
                    Toast.makeText(VerifyOtpActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.verifyOtpButton:
                code = verifyOtpEt.getText().toString().trim();
                verifyCode(code);
                break;
        }
    }

    private void sendVerificationCodeToUser(String phoneNumber) {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneNumber)       // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(VerifyOtpActivity.this)                 // Activity (for callback binding)
                .setCallbacks(mCallback)          // OnVerificationStateChangedCallbacks
                .build();

        PhoneAuthProvider.verifyPhoneNumber(options);
    }

}