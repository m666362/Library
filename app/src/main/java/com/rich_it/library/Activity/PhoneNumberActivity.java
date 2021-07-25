package com.rich_it.library.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.rich_it.library.R;
import com.rich_it.library.ServerCalling.UserServerCalling;

public class PhoneNumberActivity extends AppCompatActivity implements View.OnClickListener{

    TextInputLayout refCodeTextInput ;
    TextInputEditText refCodeEditText;
    TextInputLayout numberTextInput ;
    TextInputEditText numberEditText;
    MaterialButton sendOtpButton ;
    MaterialButton skipButton ;

    String phoneNumber;
    String refCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);
        initObjects();
        requiredTask();
    }

    private void initObjects() {
        refCodeTextInput = findViewById(R.id.ref_code_text_input);
        refCodeEditText = findViewById(R.id.ref_code_edit_text);
        numberTextInput = findViewById(R.id.number_text_input);
        numberEditText = findViewById(R.id.number_edit_text);
        sendOtpButton = findViewById(R.id.sendOtpButton);
        skipButton = findViewById(R.id.skip_button);
    }

    private void requiredTask() {

        sendOtpButton.setOnClickListener(this::onClick);
        skipButton.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sendOtpButton:
                // todo: show loading
                // Before moving to next activity hit api to check wheter the ref code exit or not
                validateRefAndNumber();
                // sendVerificationCodeToUser(phoneNumber);
                break;
            case R.id.skip_button:
                // todo: show loading
                // Before moving to next activity hit api to check wheter the ref code exit or not
                Intent intent = new Intent(PhoneNumberActivity.this, NavigationActivity.class);
                startActivity(intent);
                // sendVerificationCodeToUser(phoneNumber);
                break;
        }
    }

    private void validateRefAndNumber() {
        phoneNumber = numberEditText.getText().toString().trim();
        refCode = refCodeEditText.getText().toString().trim();
        if (TextUtils.isEmpty(refCode) || refCode.length() != 9 || phoneNumber.length() != 14) {
            Toast.makeText(this, "Either your ref code or phone number is not valid", Toast.LENGTH_SHORT).show();
            return; // or break, continue, throw
        }

        UserServerCalling.getReferer(refCode, new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                // Server calling to create a refer in db
                Toast.makeText(PhoneNumberActivity.this, "valid", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(PhoneNumberActivity.this, VerifyOtpActivity.class);
                intent.putExtra("phoneNumber", phoneNumber);
                startActivity(intent);
            }

            @Override
            public void onError(ANError anError) {
                Toast.makeText(PhoneNumberActivity.this, "Please Enter a valid refer Code", Toast.LENGTH_SHORT).show();
            }
        });
    }
}