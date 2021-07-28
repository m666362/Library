package com.rich_it.library.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.rich_it.library.Others.DialogCaller;
import com.rich_it.library.Others.MyConnectionManager;
import com.rich_it.library.R;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = LogInActivity.class.getName();

    TextInputLayout passwordTextInput ;
    TextInputEditText passwordEditText;
    TextInputLayout numberTextInput ;
    TextInputEditText numberEditText;
    MaterialButton logInButton ;
    MaterialButton skipButton ;

    String phoneNumber;
    String password;

    String dialogTitle = "ERROR!!!";
    String dialogMessage = "1. Wrong refer code! \n2. Please check your internet Connection";
    DialogCaller dialogCaller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        initObjects();
        requiredTask();
    }

    private void initObjects() {
        passwordTextInput = findViewById(R.id.password_text_input_login);
        passwordEditText = findViewById(R.id.password_edit_text_login);
        numberTextInput = findViewById(R.id.number_text_input_logIn);
        numberEditText = findViewById(R.id.number_edit_text_logIn);
        logInButton = findViewById(R.id.sendOtpButton);
        skipButton = findViewById(R.id.skip_button_logIn);
        dialogCaller = new DialogCaller(LogInActivity.this);
    }

    private void requiredTask() {
        if(!MyConnectionManager.isNetworkConnected(LogInActivity.this)){
            dialogMessage = "Please check your internet connection";
            DialogCaller.showErrorAlert(LogInActivity.this, dialogTitle, dialogMessage);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                }
            }, 2000 );
//            DialogCaller.showErrorAlert(PhoneNumberActivity.this, dialogTitle, dialogMessage);
        }

        logInButton.setOnClickListener(this::onClick);
        skipButton.setOnClickListener(this::onClick);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logInButton:
                // todo: show loading
                // Before moving to next activity hit api to check wheter the ref code exit or not
                if(MyConnectionManager.isNetworkConnected(LogInActivity.this)){
                    dialogCaller.showLoading();
                    validatePasswordAndNumber();
                }else{
                    dialogMessage = "You are not connected to Internet";
                    DialogCaller.showErrorAlert(LogInActivity.this, dialogTitle, dialogMessage);
                }
                break;
            case R.id.skip_button:
                dialogTitle = "Warning!";
                dialogMessage = "If you skip u can show content but can't post your books";
                DialogCaller.showDialog(LogInActivity.this, dialogTitle, dialogMessage, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(LogInActivity.this, NavigationActivity.class);
                        startActivity(intent);
                    }
                });
                break;
        }
    }

    private void validatePasswordAndNumber() {

    }
}