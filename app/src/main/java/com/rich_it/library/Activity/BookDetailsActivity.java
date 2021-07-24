package com.rich_it.library.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rich_it.library.Model.Book;
import com.rich_it.library.R;

public class BookDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    Book book;
    TextView nameTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        book = (Book) getIntent().getSerializableExtra("Book");
        nameTv = findViewById(R.id.book_name_tv_detailsA);
        nameTv.setText( book.getName() + " " + book.get_id());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.sendOtpButton:
//                // todo: show loading
//                String phoneNumber = numberEditText.getText().toString().trim();
//                Intent intent = new Intent(this, VerifyOtpActivity.class);
//                intent.putExtra("phoneNumber", phoneNumber);
//                startActivity(intent);
//                // sendVerificationCodeToUser(phoneNumber);
//                break;
        }
    }
}