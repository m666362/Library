package com.rich_it.library.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.rich_it.library.Model.User;
import com.rich_it.library.Others.DialogCaller;
import com.rich_it.library.R;
import com.rich_it.library.ServerCalling.UserServerCalling;

public class UserInformationActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = UserInformationActivity.class.getName();
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

    String dialogTitle = "Error";
    String dialogMessage = "Fullfill required doc";

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
                    createUserAtDb(user);
                }else{
                    DialogCaller.showErrorAlert(this, dialogTitle, dialogMessage);
                }
                break;
            case R.id.cancel_button:
                dialogTitle = "Warning";
                dialogMessage = "If you proceed u will not be registered and wont be able to post your books";
                DialogCaller.showDialog(this, dialogTitle, dialogMessage, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(UserInformationActivity.this, NavigationActivity.class);
                        startActivity(intent);
                    }
                });
                break;
        }
    }

    private void createUserAtDb(User user) {
        UserServerCalling.createUser(user, new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                // Save data at shared pref
                Intent intent = new Intent(UserInformationActivity.this, NavigationActivity.class);
                startActivity(intent);
            }

            @Override
            public void onError(ANError anError) {
                DialogCaller.showErrorAlert(UserInformationActivity.this, dialogTitle, anError.toString());
            }
        });
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
        if (TextUtils.isEmpty(user.getName()) || TextUtils.isEmpty(user.getEmail()) ||
                TextUtils.isEmpty(user.getLocation()) || TextUtils.isEmpty(user.getPassword()) ||
                user.getPassword().length() < 6 || !Patterns.EMAIL_ADDRESS.matcher(user.getEmail()).matches()) {
            Toast.makeText(this, "false", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            Toast.makeText(this, "true", Toast.LENGTH_SHORT).show();
            return true;
        }
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