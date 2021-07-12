package com.rich_it.library.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.rich_it.library.Model.User;
import com.rich_it.library.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInformationActivity extends AppCompatActivity implements View.OnClickListener {

//    String phoneNumber = "";
//    String name;
//    String gender;
//    String email;
//    String password;
//    Intent intent;
//
//    EditText nameEt;
//    EditText genderEt;
//    EditText emailEt;
//    EditText passwordEt;
//    Button saveButton;
//
//    User user = new User();
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
//        initObjects();
//        requiredTask();
    }

    @Override
    public void onClick(View v) {

    }
//
//    private void initObjects() {
//        intent = getIntent();
//        phoneNumber = intent.getStringExtra("phoneNumber");
//        saveButton = findViewById(R.id.saveButton);
//        nameEt = findViewById(R.id.nameET);
//        genderEt = findViewById(R.id.genderET);
//        emailEt = findViewById(R.id.emailET);
//        passwordEt = findViewById(R.id.passwordET);
//    }
//
//    private void requiredTask() {
//        saveButton.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.saveButton:
//                user = getUserInfo();
//                if(validateFrom(user)){
//                    Toast.makeText(this, "Account has been created successfully", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(this, HomeActivity.class);
//                    startActivity(intent);
//                }else{
//                    Toast.makeText(this, "Fullfill required doc", Toast.LENGTH_SHORT).show();
//                }
//                break;
//        }
//    }
//
//    private void saveUser(User user) {
//        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
//        Log.d("TAG", "saveUser: " + user.toString());
//    }
//
//    private User getUserInfo() {
//        name = nameEt.getText().toString().trim();
//        gender = genderEt.getText().toString().trim();
//        email = emailEt.getText().toString().trim();
//        password = passwordEt.getText().toString().trim();
//        user = new User(name, gender, email, phoneNumber, password);
//        return user;
//    }
//
//    private boolean validateFrom(User user) {
//        if (validateName(user.getName()) && validateEmail(user.getEmail()) && validateGender(user.getGender()) && validatePassword(user.getPassword())) {
//            return true;
//        }
//        return false;
//    }
//
//    private boolean validatePassword(String password) {
//        return true;
//    }
//
//    private boolean validateGender(String gender) {
//        return true;
//    }
//
//    private boolean validateEmail(String email) {
//        return true;
//    }
//
//    private boolean validateName(String name) {
//        if (name == null)
//            return false;
//
//        return true;
//    }
}