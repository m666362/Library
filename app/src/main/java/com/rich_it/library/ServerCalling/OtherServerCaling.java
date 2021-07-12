package com.rich_it.library.ServerCalling;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.rich_it.library.Others.Constant;

import java.util.Calendar;

public class OtherServerCaling {

    private static String TAG = OtherServerCaling.class.getName();

    public static void getBooks (StringRequestListener listener) {
        AndroidNetworking.get(Constant.baseUrl + Constant.books)
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsString(listener);

    }

    public static void getCategories (StringRequestListener listener) {
        AndroidNetworking.get(Constant.baseUrl + Constant.categories)
                .setPriority(Priority.LOW)
                .build()
                .getAsString(listener);
    }

    public static void getCategory (StringRequestListener listener) {
        AndroidNetworking.get(Constant.baseUrl + Constant.categories + Constant.byid + "/60ea8d4817ae30002266b960")
                .setPriority(Priority.LOW)
                .build()
                .getAsString(listener);
    }

}
