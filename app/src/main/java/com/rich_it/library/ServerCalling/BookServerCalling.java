package com.rich_it.library.ServerCalling;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.rich_it.library.Others.Constant;

public class BookServerCalling {

    private static String TAG = BookServerCalling.class.getName();

    public static void getBooks (JSONArrayRequestListener listener) {
        AndroidNetworking.get(Constant.baseUrl + Constant.books)
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray((JSONArrayRequestListener) listener);

    }
}
