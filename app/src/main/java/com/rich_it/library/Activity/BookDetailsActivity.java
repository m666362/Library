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
import com.rich_it.library.Model.Publication;
import com.rich_it.library.Model.User;
import com.rich_it.library.Others.DialogCaller;
import com.rich_it.library.Others.MyConnectionManager;
import com.rich_it.library.R;
import com.rich_it.library.Utils.TypeConverter;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

public class BookDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = BookDetailsActivity.class.getName();
    Book book;
    Author author;
    User owner;
    Publication publication;

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
    List<String> words;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        initObject();

        book = (Book) getIntent().getSerializableExtra("Book");
        words = Arrays.asList(book.getName().split(","));

        requiredTask();




        Log.d(TAG, "onCreate: " + words.get(0));
        Log.d(TAG, "onCreate: " + words.get(1));
        Log.d(TAG, "onCreate: " + words.get(2));
        Toast.makeText(this, words.get(0), Toast.LENGTH_SHORT).show();
//        owner = book.getOwner();
//        try{
//            publication = book.getPublication();
//            author = book.getAuthor();
//        }catch (Exception e){
//            publication = new Publication("Unknown Publication");
//            author = new Author("Unknown Author");
//        }
//        Log.d(TAG, "onCreate: " + author.getName());
//        Log.d(TAG, "onCreate: " + book.getName());
//        Log.d(TAG, "onCreate: " + owner.getName());

//        requiredTask();
    }

    private void initObject() {
        bookNameTv = findViewById(R.id.book_name_tv_detailsA);
        authorNameTV = findViewById(R.id.book_author_tv_detailsA);
        bookSummaryTv = findViewById(R.id.book_bio_tv_Button_detailsA);
        bookPriceTv = findViewById(R.id.book_price_tv_detailsA);

        coverImageView = findViewById(R.id.book_cover_iv_detailsA);

        buyNowButton = findViewById(R.id.book_buy_now_button_detailsA);
        readFreePageButton = findViewById(R.id.book_free_preview_button_detailsA);
    }

//    if(MyConnectionManager.isNetworkConnected(BookDetailsActivity.this)){
//
//    }else{
//        DialogCaller.showErrorAlert(BookDetailsActivity.this, "No Internet", "Please Check your internet Connection");
//    }


    private void requiredTask() {
        bookNameTv.setText( words.get(0));
        authorNameTV.setText( words.get(1));
        bookSummaryTv.setText(book.getDescription());
        bookPriceTv.setText(TypeConverter.getString(book.getRent()));

        Picasso.get().load("https://img.freepik.com/free-psd/book-cover-mockup_125540-453.jpg?size=626&ext=jpg&ga=GA1.2.1937619873.1628553600").into(coverImageView);
//        try {
//            bookSummaryTv.setText( book.getDescription());
//            Picasso.get().load(book.getCoverPage()).into(coverImageView);
//            Picasso.get().load(author.getImage()).into(authorIV);

//            bookPriceTv.setText( Integer.toString(book.getRent()));

//        }catch (Exception e){
//            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
//        }

        readFreePageButton.setOnClickListener(this);
        buyNowButton.setOnClickListener(BookDetailsActivity.this);
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