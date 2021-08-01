package com.rich_it.library.Utils;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.rich_it.library.Model.User;

public class Validation {
    public static boolean validateUserInformation(User user) {
        if (TextUtils.isEmpty(user.getName()) || TextUtils.isEmpty(user.getEmail()) ||
                TextUtils.isEmpty(user.getLocation()) || TextUtils.isEmpty(user.getPassword()) ||
                user.getPassword().length() < 6 || !Patterns.EMAIL_ADDRESS.matcher(user.getEmail()).matches()) {
            return false;
        }else {
            return true;
        }
    }
}
