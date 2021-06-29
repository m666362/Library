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
    private int imageId;
    private List<Review> ratings = new ArrayList<>();

    public Book(String name, String author, String publication, int pageNumber, float rating, int imageId) {
        this.name = name;
        this.author = author;
        this.publication = publication;
        this.pageNumber = pageNumber;
        this.rating = rating;
        this.imageId = imageId;
    }

    public Book(String name, String author, String publication, int pageNumber, float rating, int imageId, List<Review> ratings) {
        this.name = name;
        this.author = author;
        this.publication = publication;
        this.pageNumber = pageNumber;
        this.rating = rating;
        this.imageId = imageId;
        this.ratings = ratings;
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

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public List<Review> getRatings() {
        return ratings;
    }

    public void setRatings(List<Review> ratings) {
        this.ratings = ratings;
    }

    protected Book(Parcel in) {
        name = in.readString();
        author = in.readString();
        publication = in.readString();
        pageNumber = in.readInt();
        rating = in.readFloat();
        imageId = in.readInt();
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
        dest.writeInt(imageId);
        dest.writeTypedList(ratings);
    }
}
