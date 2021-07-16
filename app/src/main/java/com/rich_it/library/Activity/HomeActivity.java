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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.android.gms.common.internal.Objects;
import com.google.gson.Gson;
import com.rich_it.library.Adapter.CategoryAdapter;
import com.rich_it.library.Adapter.NearbyBookAdapter;
import com.rich_it.library.Adapter.PublicatiionAdapter;
import com.rich_it.library.Adapter.SuggestedBookAdapter;
import com.rich_it.library.Model.Book;
import com.rich_it.library.Model.Category;
import com.rich_it.library.Model.Publication;
import com.rich_it.library.R;
import com.rich_it.library.ServerCalling.BookServerCalling;
import com.rich_it.library.ServerCalling.OtherServerCaling;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
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
    RecyclerView categorieRV;
    ArrayList<Category> categories = new ArrayList<>();
    ArrayList<Publication> publications = new ArrayList<>();
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<Book> temp = new ArrayList<>();
    CategoryAdapter categoryAdapter;
    PublicatiionAdapter publicatiionAdapter;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // on some click or some loading we need to wait for...
        pb = (ProgressBar) findViewById(R.id.pbLoading);
        pb.setVisibility(ProgressBar.VISIBLE);
// run a background job and once complete
        categorieRV = findViewById(R.id.category_rv);
        categorieRV.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        categoryAdapter = new CategoryAdapter(HomeActivity.this);
        publicatiionAdapter = new PublicatiionAdapter(HomeActivity.this);
        suggestedBookAdapter = new SuggestedBookAdapter(this);
        categorieRV.setAdapter(suggestedBookAdapter);
        categorieRV.setNestedScrollingEnabled(false);

        getLocationButton = findViewById(R.id.getLocationButton);
        getLocationButton.setOnClickListener(this);
        createCategories();

//        createList();
//        initObject();
//        requiredTask();
    }

    private void createCategories() {
        BookServerCalling.getBooks(1, new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                pb.setVisibility(ProgressBar.INVISIBLE);
                Book[] books1 = new Gson().fromJson(response, Book[].class);
                temp = new ArrayList<>( Arrays.asList( books1 ) );
                books.addAll(temp);
                suggestedBookAdapter.setBooks(books);
            }

            @Override
            public void onError(ANError anError) {
                Log.d(TAG, "onError: " + anError);
            }
        });

//        OtherServerCaling.getPublications(new StringRequestListener() {
//            @Override
//            public void onResponse(String response) {
//                Publication[] publications1 = new Gson().fromJson(response, Publication[].class);
//                temp = new ArrayList<>( Arrays.asList( publications1 ) );
//                publications.addAll(temp);
//                publicatiionAdapter.setPublications(publications);
//            }
//
//            @Override
//            public void onError(ANError anError) {
//                Log.d(TAG, "onError: " + anError);
//            }
//        });

//        OtherServerCaling.getCategories(new StringRequestListener() {
//            @Override
//            public void onResponse(String response) {
//                Category[] categories1 = new Gson().fromJson(response, Category[].class);
//                temp = new ArrayList<>( Arrays.asList( categories1 ) );
//                categories.addAll(temp);
//                categoryAdapter.setCategories( categories );
//            }
//
//            @Override
//            public void onError(ANError anError) {
//                Log.d(TAG, "onError: " + anError);
//            }
//        });
    }


    private void initObject() {
        getLocationButton = findViewById(R.id.getLocationButton);
        nearbyBooksRV = findViewById(R.id.nearby_book_rv);
        nearbyBookAdapter = new NearbyBookAdapter(this);
        suggestedBookRV = findViewById(R.id.suggested_book_rv);
        suggestedBookAdapter = new SuggestedBookAdapter(this);

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
                pb.setVisibility(ProgressBar.VISIBLE);
                Toast.makeText(HomeActivity.this, "Pressed", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(this, GoogleMapActivity.class);
//                startActivity(intent);
                createCategories();
                break;
        }
    }
}