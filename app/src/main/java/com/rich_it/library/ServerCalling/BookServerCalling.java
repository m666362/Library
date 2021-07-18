package com.rich_it.library.ServerCalling;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.rich_it.library.Others.Constant;

public class BookServerCalling {

    private static String TAG = BookServerCalling.class.getName();

    public static void getBooks(int page, StringRequestListener listener) {
        String url = Constant.baseUrl + Constant.books;
        AndroidNetworking.get(url)
                .setTag(TAG)
                .addQueryParameter("page", Integer.toString(page))
                .setPriority(Priority.LOW)
                .build()
                .getAsString(listener);

    }
}
