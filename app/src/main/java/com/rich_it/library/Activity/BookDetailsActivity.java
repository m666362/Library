package com.rich_it.library.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rich_it.library.Adapter.CategoryAdapter;
import com.rich_it.library.Model.Author;
import com.rich_it.library.Model.Book;
import com.rich_it.library.Model.User;
import com.rich_it.library.Others.DialogCaller;
import com.rich_it.library.Others.MyConnectionManager;
import com.rich_it.library.R;
import com.squareup.picasso.Picasso;

public class BookDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = BookDetailsActivity.class.getName();
    Book book;
    Author author;
    User owner;

    TextView bookNameTv;
    ImageView coverImageView;
    CategoryAdapter categoryAdapter;
    RecyclerView categoryRecyclerView;
    ImageView authorIV;
    TextView authorNameTV;
    Button readFreePageButton;
    TextView bookPriceTv;
    Button buyNowButton;
    TextView bookSummaryTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        book = (Book) getIntent().getSerializableExtra("Book");
        author = book.getAuthor();
        owner = book.getOwner();
        Log.d(TAG, "onCreate: " + author.getName());
        Log.d(TAG, "onCreate: " + book.getName());
        Log.d(TAG, "onCreate: " + owner.getName());

        initObject();
        requiredTask();
    }

    private void initObject() {
        coverImageView = findViewById(R.id.book_cover_iv_detailsA);
        categoryRecyclerView = findViewById(R.id.category_recycler_view);
        categoryAdapter = new CategoryAdapter(BookDetailsActivity.this);
        bookNameTv = findViewById(R.id.book_name_tv_detailsA);
        authorIV = findViewById(R.id.book_author_iv_detailsA);
        authorNameTV = findViewById(R.id.book_author_tv_detailsA);
        readFreePageButton = findViewById(R.id.book_free_preview_button_detailsA);
        bookPriceTv = findViewById(R.id.book_price_tv_detailsA);
        buyNowButton = findViewById(R.id.book_buy_now_button_detailsA);
        bookSummaryTv = findViewById(R.id.book_bio_tv_Button_detailsA);
    }

//    if(MyConnectionManager.isNetworkConnected(BookDetailsActivity.this)){
//
//    }else{
//        DialogCaller.showErrorAlert(BookDetailsActivity.this, "No Internet", "Please Check your internet Connection");
//    }

    private void requiredTask() {

        Picasso.get().load(book.getCoverPage()).into(coverImageView);
        bookNameTv.setText( book.getName());
        Picasso.get().load(author.getImage()).into(authorIV);
        authorNameTV.setText( author.getName());
        bookPriceTv.setText( Integer.toString(book.getActualPrice()));
        bookSummaryTv.setText( book.getDescription());

        readFreePageButton.setOnClickListener(this);
        buyNowButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.book_free_preview_button_detailsA:
                // todo: show loading
                Toast.makeText(this, "Read some free pages", Toast.LENGTH_SHORT).show();
                // sendVerificationCodeToUser(phoneNumber);
                break;
            case R.id.book_buy_now_button_detailsA:
                // todo: show loading
                Toast.makeText(this, "Buy now", Toast.LENGTH_SHORT).show();
                // sendVerificationCodeToUser(phoneNumber);
                break;
        }
    }
}