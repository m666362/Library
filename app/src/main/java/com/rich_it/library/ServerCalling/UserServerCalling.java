package com.rich_it.library.ServerCalling;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.interfaces.StringRequestListener;
import com.rich_it.library.Model.User;
import com.rich_it.library.Others.Constant;

public class UserServerCalling {
    private static String users = "users/";

    public static void getUsers (StringRequestListener listener) {
        AndroidNetworking.get(Constant.baseUrl + Constant.books)
                .setPriority(Priority.LOW)
                .build()
                .getAsString(listener);
    }

    public static void getReferer (String referCode, StringRequestListener listener) {
        AndroidNetworking.get(Constant.baseUrl + Constant.users + Constant.byreferCode + referCode)
                .setPriority(Priority.LOW)
                .build()
                .getAsString(listener);
    }

    public static void createUser (User user, StringRequestListener listener) {
        AndroidNetworking.post(Constant.baseUrl + Constant.users)
                .addBodyParameter(user) // posting java object
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(listener);
    }

}
