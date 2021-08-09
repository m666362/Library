package com.rich_it.library.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.rich_it.library.Model.User;
import com.rich_it.library.Others.DialogCaller;
import com.rich_it.library.Others.MyConnectionManager;
import com.rich_it.library.R;
import com.rich_it.library.ServerCalling.UserServerCalling;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = PaymentActivity.class.getName();
    TextInputLayout amountTextInput ;
    TextInputEditText amountEditText;
    MaterialButton payWithBkashButton ;
    MaterialButton cancelPaymentButton ;

    String amountInString;
    Double amount;

    String dialogTitle = "ERROR!!!";
    String dialogMessage = "1. Wrong refer code! \n2. Please check your internet Connection";
    DialogCaller dialogCaller;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initObject();
        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        String _id = sharedPreferences.getString("_id", "");
        dialogCaller.showLoading();

        if (TextUtils.isEmpty(_id)) {
            Toast.makeText(this, "You are not registered. Please register with Ref code", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(PaymentActivity.this, PhoneNumberActivity.class);
            startActivity(intent);
            dialogCaller.dismissDialog();
//            return; // or break, continue, throw
        }else{
            UserServerCalling.getUser(_id, new StringRequestListener() {
                @Override
                public void onResponse(String response) {
                    user = new Gson().fromJson(response, User.class);
                    Toast.makeText(PaymentActivity.this, user.getName(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "getName: " + user.getName() );
                    Log.d(TAG, "getEmail: " + user.getEmail() );
                    Log.d(TAG, "getPassword: " + user.getPassword() );
                    Log.d(TAG, "getLocation: " + user.getLocation() );
                    dialogCaller.dismissDialog();
                }

                @Override
                public void onError(ANError anError) {
                    DialogCaller.showErrorAlert(PaymentActivity.this, "Error", "No user");
                }
            });
        }
        requiredTask();
    }

    private void initObject() {
        amountTextInput = findViewById(R.id.amount_text_input);
        amountEditText = findViewById(R.id.amount_edit_text);
        payWithBkashButton = findViewById(R.id.payWithBkashButton);
        cancelPaymentButton = findViewById(R.id.cancle_payment_button);
        dialogCaller = new DialogCaller(PaymentActivity.this);
    }

    private void requiredTask() {
        if(!MyConnectionManager.isNetworkConnected(PaymentActivity.this)){
            dialogMessage = "Please check your internet connection";
            DialogCaller.showErrorAlert(PaymentActivity.this, dialogTitle, dialogMessage);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                }
            }, 2000 );
        }
        payWithBkashButton.setOnClickListener(this::onClick);
        cancelPaymentButton.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.payWithBkashButton:
                // todo: show loading
                // Before moving to next activity hit api to check wheter the ref code exit or not
                if(MyConnectionManager.isNetworkConnected(PaymentActivity.this)){
                    getAmount();
                }else{
                    dialogMessage = "You are not connected to Internet";
                    DialogCaller.showErrorAlert(PaymentActivity.this, dialogTitle, dialogMessage);
                }
                break;
            case R.id.cancle_payment_button:
                dialogTitle = "Warning!";
                dialogMessage = "If you cancel, your order will be cancelled! Do you want to cancel order?";
                DialogCaller.showDialog(PaymentActivity.this, dialogTitle, dialogMessage, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(PaymentActivity.this, NavigationActivity.class);
                        startActivity(intent);
                    }
                });
                break;
        }
    }

    private void getAmount() {
        amountInString = amountEditText.getText().toString().trim();
        if (!TextUtils.isEmpty(amountInString)){
            try {
                amount = Double.parseDouble(amountInString);
            }catch (Exception e){
                amount = 0.0;
            }
            if(amount<10){
                DialogCaller.showErrorAlert(PaymentActivity.this, "Invalid Amount", "You have to pay at least 10 taka");
                amountEditText.requestFocus();
            }else{
                Intent intent = new Intent(PaymentActivity.this, BkashActivity.class);
                intent.putExtra("amountInString", String.valueOf(amount));
                startActivity(intent);
            }
        }else{
            DialogCaller.showErrorAlert(PaymentActivity.this, "Error", "Please enter proper amount");
        }
    }
}