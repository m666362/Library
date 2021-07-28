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

    public static void getUser (String _id, StringRequestListener listener) {
        AndroidNetworking.get(Constant.baseUrl + Constant.users + Constant.byid + _id)
                .setPriority(Priority.LOW)
                .build()
                .getAsString(listener);
    }

    public static void createUser (User user, String refererId, StringRequestListener listener) {
        AndroidNetworking.post(Constant.baseUrl + Constant.users)
                .addBodyParameter(user) // posting java object
                .addQueryParameter("refererId", refererId)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(listener);
    }

}
