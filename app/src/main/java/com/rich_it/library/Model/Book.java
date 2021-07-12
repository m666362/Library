package com.rich_it.library.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Book implements Parcelable {

    private String _id;
    private String name;
    private String author;
    private String publication;
    private String coverPage;
    private String owner;
    private String freePages;
    private String description;
    private ArrayList<Category> categories;
    private int actualPrice;
    private int rent;
    private int duration;
    private String createdAt;
    private String updatedAt;
    private String __v;

    public Book(String name) {
        this.name = name;
    }

    public Book(String _id, String name, String author, String publication, String coverPage, String owner, String freePages, String description, ArrayList<Category> categories, int actualPrice, int rent, int duration, String createdAt, String updatedAt, String __v) {
        this._id = _id;
        this.name = name;
        this.author = author;
        this.publication = publication;
        this.coverPage = coverPage;
        this.owner = owner;
        this.freePages = freePages;
        this.description = description;
        this.categories = categories;
        this.actualPrice = actualPrice;
        this.rent = rent;
        this.duration = duration;
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

    public String getCoverPage() {
        return coverPage;
    }

    public void setCoverPage(String coverPage) {
        this.coverPage = coverPage;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getFreePages() {
        return freePages;
    }

    public void setFreePages(String freePages) {
        this.freePages = freePages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public int getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(int actualPrice) {
        this.actualPrice = actualPrice;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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

    protected Book(Parcel in) {
        _id = in.readString();
        name = in.readString();
        author = in.readString();
        publication = in.readString();
        coverPage = in.readString();
        owner = in.readString();
        freePages = in.readString();
        description = in.readString();
        categories = in.createTypedArrayList(Category.CREATOR);
        actualPrice = in.readInt();
        rent = in.readInt();
        duration = in.readInt();
        createdAt = in.readString();
        updatedAt = in.readString();
        __v = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(name);
        dest.writeString(author);
        dest.writeString(publication);
        dest.writeString(coverPage);
        dest.writeString(owner);
        dest.writeString(freePages);
        dest.writeString(description);
        dest.writeTypedList(categories);
        dest.writeInt(actualPrice);
        dest.writeInt(rent);
        dest.writeInt(duration);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(__v);
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
