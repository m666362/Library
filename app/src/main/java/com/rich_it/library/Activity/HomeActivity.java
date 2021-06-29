package com.rich_it.library.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rich_it.library.Adapter.NearbyBookAdapter;
import com.rich_it.library.Model.Book;
import com.rich_it.library.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    Button getLocationButton;
    RecyclerView nearbyBooksRV;
    RecyclerView dynamicBookRV;
    NearbyBookAdapter nearbyBookAdapter;
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
        books.add(new Book("Islami", "Jahangir", "As Sunnah Trust", 500, (float) 4.5, R.drawable.ic_baseline_notifications_none_24));
        books.add(new Book("Islami Aqidah", "D. Abdullah Jahangir", "As Sunnah Trust", 500, (float) 4.5, R.drawable.amu_bubble_mask));
        books.add(new Book("Islami Aqidah", "D. Abdullah Jahangir", "As Sunnah Trust", 500, (float) 4.5, R.drawable.ic_baseline_menu_24));
        books.add(new Book("Islami Aqidah", "D. Abdullah Jahangir", "As Sunnah Trust", 500, (float) 4.5, R.drawable.amu_bubble_mask));
        books.add(new Book("Islami Aqidah", "D. Abdullah Jahangir", "As Sunnah Trust", 500, (float) 4.5, R.drawable.ic_baseline_menu_24));
        books.add(new Book("Islami Aqidah", "D. Abdullah Jahangir", "As Sunnah Trust", 500, (float) 4.5, R.drawable.amu_bubble_mask));
        books.add(new Book("Islami Aqidah", "D. Abdullah Jahangir", "As Sunnah Trust", 500, (float) 4.5, R.drawable.ic_baseline_menu_24));
        books.add(new Book("Islami Aqidah", "D. Abdullah Jahangir", "As Sunnah Trust", 500, (float) 4.5, R.drawable.amu_bubble_mask));
        books.add(new Book("Islami Aqidah", "D. Abdullah Jahangir", "As Sunnah Trust", 500, (float) 4.5, R.drawable.ic_baseline_menu_24));
        books.add(new Book("Islami Aqidah", "D. Abdullah Jahangir", "As Sunnah Trust", 500, (float) 4.5, R.drawable.amu_bubble_mask));
        books.add(new Book("Islami Aqidah", "D. Abdullah Jahangir", "As Sunnah Trust", 500, (float) 4.5, R.drawable.ic_baseline_menu_24));
        books.add(new Book("Islami Aqidah", "D. Abdullah Jahangir", "As Sunnah Trust", 500, (float) 4.5, R.drawable.amu_bubble_mask));
        books.add(new Book("Islami Aqidah", "D. Abdullah Jahangir", "As Sunnah Trust", 500, (float) 4.5, R.drawable.ic_baseline_menu_24));
        books.add(new Book("Islami Aqidah", "D. Abdullah Jahangir", "As Sunnah Trust", 500, (float) 4.5, R.drawable.amu_bubble_mask));
        books.add(new Book("Islami Aqidah", "D. Abdullah Jahangir", "As Sunnah Trust", 500, (float) 4.5, R.drawable.ic_baseline_menu_24));
        books.add(new Book("Islami Aqidah", "D. Abdullah Jahangir", "As Sunnah Trust", 500, (float) 4.5, R.drawable.amu_bubble_mask));
    }

    private void initObject() {
        getLocationButton = findViewById(R.id.getLocationButton);
        nearbyBooksRV = findViewById(R.id.nearby_book_rv);
        nearbyBookAdapter = new NearbyBookAdapter(this, books);
    }

    private void requiredTask() {
        nearbyBooksRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        nearbyBooksRV.setAdapter(nearbyBookAdapter);
        nearbyBookAdapter.notifyDataSetChanged();
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