package com.rich_it.library.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.rich_it.library.R;

public class PhoneNumberActivity extends AppCompatActivity implements View.OnClickListener{

    TextInputLayout numberTextInput ;
    TextInputEditText numberEditText;
    MaterialButton sendOtpButton ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);
        initObjects();
        requiredTask();
    }

    private void initObjects() {
        numberTextInput = findViewById(R.id.number_text_input);
        numberEditText = findViewById(R.id.number_edit_text);
        sendOtpButton = findViewById(R.id.sendOtpButton);
    }

    private void requiredTask() {
        sendOtpButton.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sendOtpButton:
                // todo: show loading
                String phoneNumber = numberEditText.getText().toString().trim();
                Intent intent = new Intent(this, VerifyOtpActivity.class);
                intent.putExtra("phoneNumber", phoneNumber);
                startActivity(intent);
                // sendVerificationCodeToUser(phoneNumber);
                break;
        }
    }
}