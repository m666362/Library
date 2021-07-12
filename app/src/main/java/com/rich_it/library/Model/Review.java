package com.rich_it.library.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Review implements Parcelable {
    private String _id;
    private String bookStar;
    private String ownerStar;
    private String recommendation;
    private String description;
    private Book book;
    private User reviewer;
    private String createdAt;
    private String updatedAt;
    private String __v;

    public Review(String bookStar) {
        this.bookStar = bookStar;
    }

    public Review(String _id, String bookStar, String ownerStar, String recommendation, String description, Book book, User reviewer, String createdAt, String updatedAt, String __v) {
        this._id = _id;
        this.bookStar = bookStar;
        this.ownerStar = ownerStar;
        this.recommendation = recommendation;
        this.description = description;
        this.book = book;
        this.reviewer = reviewer;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.__v = __v;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getBookStar() {
        return bookStar;
    }

    public void setBookStar(String bookStar) {
        this.bookStar = bookStar;
    }

    public String getOwnerStar() {
        return ownerStar;
    }

    public void setOwnerStar(String ownerStar) {
        this.ownerStar = ownerStar;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String get__v() {
        return __v;
    }

    public void set__v(String __v) {
        this.__v = __v;
    }

    protected Review(Parcel in) {
        _id = in.readString();
        bookStar = in.readString();
        ownerStar = in.readString();
        recommendation = in.readString();
        description = in.readString();
        book = in.readParcelable(Book.class.getClassLoader());
        reviewer = in.readParcelable(User.class.getClassLoader());
        createdAt = in.readString();
        updatedAt = in.readString();
        __v = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(bookStar);
        dest.writeString(ownerStar);
        dest.writeString(recommendation);
        dest.writeString(description);
        dest.writeParcelable(book, flags);
        dest.writeParcelable(reviewer, flags);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(__v);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };
}
