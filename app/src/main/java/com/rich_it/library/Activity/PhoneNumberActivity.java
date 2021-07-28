package com.rich_it.library.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
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
import com.google.gson.Gson;
import com.rich_it.library.Model.Book;
import com.rich_it.library.Model.User;
import com.rich_it.library.Others.DialogCaller;
import com.rich_it.library.Others.MyConnectionManager;
import com.rich_it.library.R;
import com.rich_it.library.ServerCalling.UserServerCalling;

public class PhoneNumberActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = PhoneNumberActivity.class.getName();
    TextInputLayout refCodeTextInput ;
    TextInputEditText refCodeEditText;
    TextInputLayout numberTextInput ;
    TextInputEditText numberEditText;
    MaterialButton sendOtpButton ;
    MaterialButton skipButton ;

    String phoneNumber;
    String refCode;

    String dialogTitle = "ERROR!!!";
    String dialogMessage = "1. Wrong refer code! \n2. Please check your internet Connection";
    DialogCaller dialogCaller;

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
        dialogCaller = new DialogCaller(PhoneNumberActivity.this);
    }

    private void requiredTask() {
        if(!MyConnectionManager.isNetworkConnected(PhoneNumberActivity.this)){
            dialogMessage = "Please check your internet connection";
            DialogCaller.showErrorAlert(PhoneNumberActivity.this, dialogTitle, dialogMessage);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                }
            }, 2000 );
//            DialogCaller.showErrorAlert(PhoneNumberActivity.this, dialogTitle, dialogMessage);
        }
        sendOtpButton.setOnClickListener(this::onClick);
        skipButton.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sendOtpButton:
                // todo: show loading
                // Before moving to next activity hit api to check wheter the ref code exit or not
                if(MyConnectionManager.isNetworkConnected(PhoneNumberActivity.this)){
                    dialogCaller.showLoading();
                    validateRefAndNumber();
                }else{
                    dialogMessage = "You are not connected to Internet";
                    DialogCaller.showErrorAlert(PhoneNumberActivity.this, dialogTitle, dialogMessage);
                }
                break;
            case R.id.skip_button:
                dialogTitle = "Warning!";
                dialogMessage = "If you skip u can show content but can't post your books";
                DialogCaller.showDialog(PhoneNumberActivity.this, dialogTitle, dialogMessage, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(PhoneNumberActivity.this, NavigationActivity.class);
                        startActivity(intent);
                    }
                });
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
                dialogCaller.dismissDialog();
                User user = new Gson().fromJson(response, User.class);

                Intent intent = new Intent(PhoneNumberActivity.this, UserInformationActivity.class);
                intent.putExtra("phoneNumber", phoneNumber);
                intent.putExtra("refererId", user.get_id());
                startActivity(intent);
            }

            @Override
            public void onError(ANError anError) {
                dialogCaller.dismissDialog();
                DialogCaller.showErrorAlert(PhoneNumberActivity.this, dialogTitle, dialogMessage);
            }
        });
    }
}