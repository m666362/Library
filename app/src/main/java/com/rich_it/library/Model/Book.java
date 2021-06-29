package com.rich_it.library.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Book implements Parcelable {
    private String name;
    private String author;
    private String publication;
    private String pageNumber;
    private String rating;
    private int imageId;
    private List<Review> ratings = new ArrayList<>();

    public Book(String name, String author, String publication, String pageNumber, String rating, int imageId) {
        this.name = name;
        this.author = author;
        this.publication = publication;
        this.pageNumber = pageNumber;
        this.rating = rating;
        this.imageId = imageId;
    }

    public Book(String name, String author, String publication, String pageNumber, String rating, int imageId, List<Review> ratings) {
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

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
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
        pageNumber = in.readString();
        rating = in.readString();
        imageId = in.readInt();
        ratings = in.createTypedArrayList(Review.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(author);
        dest.writeString(publication);
        dest.writeString(pageNumber);
        dest.writeString(rating);
        dest.writeInt(imageId);
        dest.writeTypedList(ratings);
    }

    @Override
    public int describeContents() {
        return 0;
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
}
