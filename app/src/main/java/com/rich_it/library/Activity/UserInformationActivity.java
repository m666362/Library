package com.rich_it.library.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.rich_it.library.R;

public class UserInformationActivity extends AppCompatActivity {

    String phoneNumber = "";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        initObjects();
        requiredTask();
    }

    private void initObjects() {
        intent = getIntent();
        phoneNumber = intent.getStringExtra("phoneNumber");
    }

    private void requiredTask() {
        Toast.makeText(this, phoneNumber, Toast.LENGTH_SHORT).show();
    }
}