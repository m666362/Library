package com.rich_it.library.Others;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interfaces.StringRequestListener;
import com.rich_it.library.Model.User;

public class UserServerCalling {
    private static String users = "users/";

    public static void login(User user, StringRequestListener listener) {
        AndroidNetworking.post(Constant.baseUrl + users + "login/")
                .addBodyParameter(user)
                .build()
                .getAsString(listener);
    }
}
