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

    protected Book(Parcel in) {
        name = in.readString();
        author = in.readString();
        publication = in.readString();
        pageNumber = in.readInt();
        rating = in.readFloat();
        image = in.readInt();
        ratings = in.createTypedArrayList(Review.CREATOR);
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(author);
        dest.writeString(publication);
        dest.writeInt(pageNumber);
        dest.writeFloat(rating);
        dest.writeInt(image);
        dest.writeTypedList(ratings);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public List<Review> getRatings() {
        return ratings;
    }

    public void setRatings(List<Review> ratings) {
        this.ratings = ratings;
    }
}
