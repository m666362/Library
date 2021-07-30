package com.rich_it.library.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.rich_it.library.R;

public class PaymentSuccessfullActivity extends AppCompatActivity {

    TextView successTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_successfull);
        initObject();
        requiredTask();
    }

    private void initObject() {
        successTV = findViewById(R.id.payment_Success_TV);
    }

    private void requiredTask() {
        successTV.setText("Something");
    }
}