package com.rich_it.library.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.rich_it.library.R;

public class AddBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        String setting = sharedPreferences.getString("name", "");
        Toast.makeText(this, setting, Toast.LENGTH_SHORT).show();

        if (TextUtils.isEmpty(setting)) {
            Toast.makeText(this, "You are not registered. Please register with Ref code", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddBookActivity.this, PhoneNumberActivity.class);
            startActivity(intent);
            return; // or break, continue, throw
        }
    }
}