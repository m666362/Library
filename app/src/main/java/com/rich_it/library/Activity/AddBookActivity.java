package com.rich_it.library.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.gson.Gson;
import com.rich_it.library.Model.User;
import com.rich_it.library.Others.DialogCaller;
import com.rich_it.library.R;
import com.rich_it.library.ServerCalling.UserServerCalling;

public class AddBookActivity extends AppCompatActivity {

    private static final String TAG = AddBookActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        String _id = sharedPreferences.getString("_id", "");

        if (TextUtils.isEmpty(_id)) {
            Toast.makeText(this, "You are not registered. Please register with Ref code", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddBookActivity.this, PhoneNumberActivity.class);
            startActivity(intent);
            return; // or break, continue, throw
        }else{
            UserServerCalling.getUser(_id, new StringRequestListener() {
                @Override
                public void onResponse(String response) {
                    User user = new Gson().fromJson(response, User.class);
                    Toast.makeText(AddBookActivity.this, user.getName(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "getName: " + user.getName() );
                    Log.d(TAG, "getEmail: " + user.getEmail() );
                    Log.d(TAG, "getPassword: " + user.getPassword() );
                    Log.d(TAG, "getLocation: " + user.getLocation() );
                }

                @Override
                public void onError(ANError anError) {
                    DialogCaller.showErrorAlert(AddBookActivity.this, "Error", "No user");
                }
            });
        }

        Log.d(TAG, "onCreate: " + _id);
    }
}