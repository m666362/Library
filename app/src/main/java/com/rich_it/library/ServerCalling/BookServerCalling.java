package com.rich_it.library.ServerCalling;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.rich_it.library.Model.Book;
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

    public static void postBook(Book book, StringRequestListener listener) {
        Log.d(TAG, "postBook: " + book.getName());
        AndroidNetworking.post(Constant.baseUrl + Constant.books)
                .addBodyParameter("name", book.getName())
                .addBodyParameter("owner", book.getOwner().get_id())
                .addBodyParameter("description", book.getDescription())
                .addBodyParameter("rent", String.valueOf(book.getRent()))
                .addBodyParameter("duration", String.valueOf(book.getDuration()))
                .setTag(TAG)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(listener);

    }
}
