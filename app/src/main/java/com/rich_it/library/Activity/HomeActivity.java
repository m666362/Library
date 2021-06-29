package com.rich_it.library.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rich_it.library.Adapter.NearbyBookAdapter;
import com.rich_it.library.Adapter.SuggestedBookAdapter;
import com.rich_it.library.Model.Book;
import com.rich_it.library.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    Button getLocationButton;
    RecyclerView nearbyBooksRV;
    RecyclerView suggestedBookRV;
    NearbyBookAdapter nearbyBookAdapter;
    SuggestedBookAdapter suggestedBookAdapter;
    List<Book> books = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        createList();
        initObject();
        requiredTask();
    }

    private void createList() {
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
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
        nearbyBookAdapter.notifyDataSetChanged();
        suggestedBookRV.setLayoutManager(new LinearLayoutManager(this));
        suggestedBookRV.setAdapter(suggestedBookAdapter);
        suggestedBookAdapter.notifyDataSetChanged();
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