package com.rich_it.library.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.rich_it.library.Activity.AddBookActivity;
import com.rich_it.library.Activity.PhoneNumberActivity;
import com.rich_it.library.Model.User;
import com.rich_it.library.Others.DialogCaller;
import com.rich_it.library.Others.MyConnectionManager;
import com.rich_it.library.R;
import com.rich_it.library.ServerCalling.UserServerCalling;
import com.rich_it.library.Utils.Validation;

import org.jetbrains.annotations.NotNull;

public class ProfileFragment extends Fragment {

    private static final String TAG = ProfileFragment.class.getName();
    TextView userNameTV;
    TextView userPhoneNumberTV;
    TextView purchasedBookTV;
    TextView uploadedBookTV;

    TextInputLayout nameTI ;
    TextInputEditText nameET;
    TextInputLayout emailTI ;
    TextInputEditText emailET;
    TextInputLayout addressTI ;
    TextInputEditText addressET;
    TextInputLayout passwordTI ;
    TextInputEditText passwordET;

    Button updateButton;

    String name;
    String email;
    String address;
    String password;

    User user;
    Boolean updateMode = false;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_profile, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE);
        String _id = sharedPreferences.getString("_id", "");
        initObject(view);

        if (TextUtils.isEmpty(_id)) {
            Toast.makeText(getActivity(), "You are not registered. Please register with Ref code", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), PhoneNumberActivity.class);
            startActivity(intent);
            return; // or break, continue, throw
        }else{
            if(MyConnectionManager.isNetworkConnected(getActivity())){
                UserServerCalling.getUser(_id, new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        user = new Gson().fromJson(response, User.class);
                        userNameTV.setText(user.getName());
                        userPhoneNumberTV.setText(user.getPhoneNumber());
                        nameET.setText(user.getName());
                        emailET.setText(user.getEmail());
                        addressET.setText(user.getPhysicalAddress());
                        passwordET.setText(user.getPassword());
                        Toast.makeText(getActivity(), user.getName(), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onResponse: " + user.getName());
                    }

                    @Override
                    public void onError(ANError anError) {
                        DialogCaller.showErrorAlert(getActivity(), "Error", "No user");
                    }
                });
            }else{
                DialogCaller.showErrorAlert(getActivity(), "Error", "Check your internet Connection");
            }
        }
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setName(nameET.getText().toString().trim());
                user.setEmail(emailET.getText().toString().trim());
                user.setPhysicalAddress(addressET.getText().toString().trim());
                user.setPassword(passwordET.getText().toString().trim());
                validateUserInfo(user);
            }
        });
    }

    private void validateUserInfo(User user) {
        if(Validation.validateUserInformation(user)){
            Toast.makeText(getActivity(), "updated", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(), "something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void initObject(View view) {

        userNameTV = view.findViewById(R.id.user_profile_name);
        userPhoneNumberTV = view.findViewById(R.id.user_phone_number);
        purchasedBookTV = view.findViewById(R.id.book_purchased_tv);
        uploadedBookTV = view.findViewById(R.id.book_uploaded_tv);

        nameTI = view.findViewById(R.id.user_profile_name_text_input);
        nameET = view.findViewById(R.id.user_profile_name_edit_text);
        emailTI = view.findViewById(R.id.user_profile_email_text_input);
        emailET = view.findViewById(R.id.user_profile_email_edit_text);
        addressTI = view.findViewById(R.id.user_profile_address_text_input);
        addressET = view.findViewById(R.id.user_profile_address_edit_text);
        passwordTI = view.findViewById(R.id.user_profile_password_text_input);
        passwordET = view.findViewById(R.id.user_profile_password_edit_text);

        updateButton = view.findViewById(R.id.update_user_button);
    }
}