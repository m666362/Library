package com.rich_it.library.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.rich_it.library.Model.Book;
import com.rich_it.library.Model.User;
import com.rich_it.library.Others.DialogCaller;
import com.rich_it.library.Others.MyConnectionManager;
import com.rich_it.library.R;
import com.rich_it.library.ServerCalling.BookServerCalling;
import com.rich_it.library.ServerCalling.UserServerCalling;
import com.rich_it.library.Utils.TypeConverter;

public class AddBookActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = AddBookActivity.class.getName();

    TextInputLayout bookNameTextInput, authorNameTextInput, publicationNameTextInput, descriptionTextInput, rentTextInput, durationTextInput ;
    TextInputEditText bookNameEditText, authorNameEditText, publicationNameEditText, descriptionEditText, rentEditText, durationEditText;
    MaterialButton postBookButton;
    MaterialButton cancelButton;

    Book book = new Book("Undefined");
    String bookName = "";
    String authorName = "";
    String publicationName = "";
    String description = "";
    int rent = 0;
    int duration = 0;

    DialogCaller dialogCaller;
    User user;
    String dialogTitle = "Error";
    String dialogMessage = "Check Error";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        initObject();
        requiredTask();


        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        String _id = sharedPreferences.getString("_id", "");

        if (TextUtils.isEmpty(_id)) {
            dialogCaller.dismissDialog();
            Toast.makeText(this, "You are not registered. Please register with Ref code", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddBookActivity.this, PhoneNumberActivity.class);
            startActivity(intent);
            return; // or break, continue, throw
        }else{
            UserServerCalling.getUser(_id, new StringRequestListener() {
                @Override
                public void onResponse(String response) {
                    user = new Gson().fromJson(response, User.class);
                    Toast.makeText(AddBookActivity.this, user.getName(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "getName: " + user.getName() );
                    Log.d(TAG, "getEmail: " + user.getEmail() );
                    Log.d(TAG, "getPassword: " + user.getPassword() );
                    Log.d(TAG, "getLocation: " + user.getLocation() );
                    dialogCaller.dismissDialog();
                }

                @Override
                public void onError(ANError anError) {
                    DialogCaller.showErrorAlert(AddBookActivity.this, "Error", "No user");
                }
            });
        }

        Log.d(TAG, "onCreate: " + _id);
    }

    private void requiredTask() {

        postBookButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        dialogCaller = new DialogCaller(AddBookActivity.this);
        dialogCaller.showLoading();
    }

    private void initObject() {
        postBookButton = findViewById(R.id.postBookButtonAddActivity);
        cancelButton = findViewById(R.id.cancel_postBook_button);

        bookNameTextInput = findViewById(R.id.bookName_text_input);
        bookNameEditText = findViewById(R.id.bookName_edit_text);

        authorNameTextInput = findViewById(R.id.book_author_name_text_input);
        authorNameEditText = findViewById(R.id.book_author_name_edit_text);

        publicationNameTextInput = findViewById(R.id.book_publication_name_text_input);
        publicationNameEditText = findViewById(R.id.book_publication_name_edit_text);

        descriptionTextInput = findViewById(R.id.book_description_text_input);
        descriptionEditText = findViewById(R.id.book_description_edit_text);

        rentTextInput = findViewById(R.id.rent_text_input);
        rentEditText = findViewById(R.id.rent_edit_text);

        durationTextInput = findViewById(R.id.duration_text_input);
        durationEditText = findViewById(R.id.duration_edit_text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.postBookButtonAddActivity:
                dialogCaller.showLoading();
                if(MyConnectionManager.isNetworkConnected(AddBookActivity.this)){
                    book = getBookInformation();
                    if(validateBook(book)){
                        createBookAtDb(book);
                    }else{
                        DialogCaller.showErrorAlert(this, dialogTitle, dialogMessage);
                    }
                }else{
                    dialogMessage = "You are not connected to Internet";
                    DialogCaller.showErrorAlert(AddBookActivity.this, dialogTitle, dialogMessage);
                }
                break;
            case R.id.cancel_postBook_button:
                dialogTitle = "Warning";
                dialogMessage = "If you proceed u will not be registered and wont be able to post your books";
                DialogCaller.showDialog(this, dialogTitle, dialogMessage, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(AddBookActivity.this, NavigationActivity.class);
                        startActivity(intent);
                    }
                });
                break;
        }
    }

    private void createBookAtDb(Book book) {
        BookServerCalling.postBook(book, new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: " + response);
                dialogCaller.dismissDialog();
                DialogCaller.showErrorAlert(AddBookActivity.this, "Congratulation", "Your book has been uploaded");
            }

            @Override
            public void onError(ANError anError) {
                dialogCaller.dismissDialog();
                DialogCaller.showErrorAlert(AddBookActivity.this, "Error", "Book not created");
            }
        });
    }

    private boolean validateBook(Book book) {
        if (TextUtils.isEmpty(book.getName()) || TextUtils.isEmpty(authorName) ||
                TextUtils.isEmpty(publicationName) || TextUtils.isEmpty(book.getDescription()) ||
                TextUtils.isEmpty(String.valueOf(book.getRent())) || TextUtils.isEmpty(String.valueOf(book.getDuration()))) {
            Toast.makeText(this, "false", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            Toast.makeText(this, "true", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    private Book getBookInformation() {

        bookName = bookNameEditText.getText().toString().trim()
                + "," + authorNameEditText.getText().toString().trim()
                + "," + publicationNameEditText.getText().toString().trim();
        authorName = authorNameEditText.getText().toString().trim();
        publicationName = publicationNameEditText.getText().toString().trim();
        description = descriptionEditText.getText().toString().trim();
        rent = TypeConverter.getInt(rentEditText.getText().toString().trim());
        duration = TypeConverter.getInt(durationEditText.getText().toString().trim());

        book = new Book(bookName, user, description, rent, duration);
        Log.d(TAG, "getBookInformation: " + bookName);
        return book;
    }

}