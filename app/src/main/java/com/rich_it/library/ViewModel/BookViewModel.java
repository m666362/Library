package com.rich_it.library.ViewModel;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.gson.Gson;
import com.rich_it.library.Model.Book;
import com.rich_it.library.ServerCalling.BookServerCalling;

import java.util.Arrays;
import java.util.List;

public class BookViewModel extends ViewModel {

    private static final String TAG = "hello";
    private MutableLiveData<List<Book>> books;
    public LiveData<List<Book>> getBooks() {
        if (books == null) {
            books = new MutableLiveData<List<Book>>();
            loadBooks();
        }
        return books;
    }

    private void loadBooks() {
        BookServerCalling.getBooks(new StringRequestListener() {

            @Override
            public void onResponse(String response) {
                Book[] books1 = new Gson().fromJson(response, Book[].class);
                books.setValue(Arrays.asList(books1));
            }

            @Override
            public void onError(ANError anError) {
                Log.d(TAG, "onError: " + "hello");
            }
        });
        // Do an asynchronous operation to fetch Books.
    }
}
