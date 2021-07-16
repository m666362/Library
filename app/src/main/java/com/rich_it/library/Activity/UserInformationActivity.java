package com.rich_it.library.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.rich_it.library.Model.User;
import com.rich_it.library.R;

public class UserInformationActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout phoneNumberTextInput, nameTextInput, emailTextInput, passwordTextInput, locationTextInput ;
    TextInputEditText phoneNumberEditText, nameEditText, emailEditText, passwordEditText, locationEditText;
    MaterialButton submitButton;
    MaterialButton cancelButton;

    String phoneNumber = "";
    String name;
    String location;
    String email;
    String password;
    Intent intent;

    User user;

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

        submitButton = findViewById(R.id.submitButton);
        cancelButton = findViewById(R.id.cancel_button);

        phoneNumberTextInput = findViewById(R.id.phoneNumber_text_input);
        phoneNumberEditText = findViewById(R.id.phoneNumber_edit_text);
        nameTextInput = findViewById(R.id.name_text_input);
        nameEditText = findViewById(R.id.name_edit_text);
        emailTextInput = findViewById(R.id.email_text_input);
        emailEditText = findViewById(R.id.email_edit_text);
        passwordTextInput = findViewById(R.id.password_text_input);
        passwordEditText = findViewById(R.id.password_edit_text);
        locationEditText = findViewById(R.id.location_edit_text);
        locationTextInput = findViewById(R.id.location_text_input);

    }

    private void requiredTask() {
        phoneNumberEditText.setText(phoneNumber);
        submitButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submitButton:
                user = getUserInfo();
                if(validateFrom(user)){
                    Toast.makeText(this, "Account has been created successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, NavigationActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "Fullfill required doc", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void saveUser(User user) {
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        Log.d("TAG", "saveUser: " + user.toString());
    }

    private User getUserInfo() {
        name = nameEditText.getText().toString().trim();
        location = locationEditText.getText().toString().trim();
        email = emailEditText.getText().toString().trim();
        password = passwordEditText.getText().toString().trim();
        user = new User(name, email, location, phoneNumber, password);
        return user;
    }

    private boolean validateFrom(User user) {
        if (validateName(user.getName()) && validateEmail(user.getEmail()) && validateGender(user.getLocation()) && validatePassword(user.getPassword())) {
            return true;
        }
        return false;
    }

    private boolean validatePassword(String password) {
        return true;
    }

    private boolean validateGender(String gender) {
        return true;
    }

    private boolean validateEmail(String email) {
        return true;
    }

    private boolean validateName(String name) {
//        if (name == null)
//            return false;

        return true;
    }
}