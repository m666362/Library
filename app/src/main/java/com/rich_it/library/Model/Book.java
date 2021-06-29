package com.rich_it.library.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Book implements Parcelable {
    private String name;
    private String author;
    private String publication;
    private int pageNumber;
    private float rating;
    private int image;
    private List<Review> ratings = new ArrayList<Review>();
}
