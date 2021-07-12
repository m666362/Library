package com.rich_it.library.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.android.gms.common.internal.Objects;
import com.google.gson.Gson;
import com.rich_it.library.Adapter.CategoryAdapter;
import com.rich_it.library.Adapter.NearbyBookAdapter;
import com.rich_it.library.Adapter.SuggestedBookAdapter;
import com.rich_it.library.Model.Book;
import com.rich_it.library.Model.Category;
import com.rich_it.library.R;
import com.rich_it.library.ServerCalling.BookServerCalling;
import com.rich_it.library.ServerCalling.OtherServerCaling;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    String TAG = "hello";
    Button getLocationButton;
    RecyclerView nearbyBooksRV;
    RecyclerView suggestedBookRV;
    NearbyBookAdapter nearbyBookAdapter;
    SuggestedBookAdapter suggestedBookAdapter;
    List<Book> books = new ArrayList<>();
    RecyclerView categorieRV;
    ArrayList<Category> categories = new ArrayList<>();
    CategoryAdapter categoryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        categorieRV = findViewById(R.id.category_rv);
        createCategories();

//        createList();
//        initObject();
//        requiredTask();
    }

    private void createCategories() {
        categorieRV = findViewById(R.id.category_rv);
        categorieRV.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        categoryAdapter = new CategoryAdapter(HomeActivity.this);
        categorieRV.setAdapter(categoryAdapter);
        OtherServerCaling.getCategories(new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                Category[] categories1 = new Gson().fromJson(response, Category[].class);
                categories = new ArrayList<>( Arrays.asList( categories1 ) );
                categoryAdapter.setCategory( categories );
            }

            @Override
            public void onError(ANError anError) {
                Log.d(TAG, "onError: " + anError);
            }
        });
    }

    private void createList() {
        BookServerCalling.getBooks(new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                Book[] booksArray = new Gson().fromJson( response, Book[].class );
                books = new ArrayList<>( Arrays.asList( booksArray ) );

                nearbyBookAdapter.setBooks((ArrayList<Book>) books);
                suggestedBookAdapter.setBooks((ArrayList<Book>) books);

                new AlertDialog.Builder(HomeActivity.this)
                        .setTitle("Sandwich Dialog")
                        .setMessage(books.toString())
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(HomeActivity.this, "Declined", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(HomeActivity.this, "Accepted", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setCancelable(true)
                        .show();
            }

            @Override
            public void onError(ANError anError) {
                Log.d(TAG, "onError: " + anError.toString());
            }
        });
//        BookServerCalling.getBooks(new JSONArrayRequestListener() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//                new AlertDialog.Builder(HomeActivity.this)
//                        .setTitle("Sandwich Dialog")
//                        .setMessage(response.toString())
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(HomeActivity.this, "Declined", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(HomeActivity.this, "Accepted", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .setCancelable(true)
//                        .show();
//
//                Toast.makeText(HomeActivity.this, response.toString(), Toast.LENGTH_LONG).show();
//                for (int i=0; i<response.length(); i++){
//                    Log.d(TAG, "i" + i);
//                }
//            }
//
//            @Override
//            public void onError(ANError anError) {
//                Log.d(TAG, "onError: " + anError);
//            }
//        });
//        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
//        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
//        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
//        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
//        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
//        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
//        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
//        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
//        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
//        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
//        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
//        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
//        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
//        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
//        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
    }

    private void initObject() {
        getLocationButton = findViewById(R.id.getLocationButton);
        nearbyBooksRV = findViewById(R.id.nearby_book_rv);
        nearbyBookAdapter = new NearbyBookAdapter(this, books);
        suggestedBookRV = findViewById(R.id.suggested_book_rv);
        suggestedBookAdapter = new SuggestedBookAdapter(this, books);

    }

    private void requiredTask() {
        nearbyBooksRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        nearbyBooksRV.setAdapter(nearbyBookAdapter);
        suggestedBookRV.setLayoutManager(new LinearLayoutManager(this));
        suggestedBookRV.setAdapter(suggestedBookAdapter);
        suggestedBookRV.setNestedScrollingEnabled(false);
        getLocationButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.getLocationButton:
                Intent intent = new Intent(this, GoogleMapActivity.class);
                startActivity(intent);
                break;
        }
    }
}