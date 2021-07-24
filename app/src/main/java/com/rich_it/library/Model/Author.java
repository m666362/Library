package com.rich_it.library.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class Author implements Parcelable, Serializable {

    private String _id;
    private String name;
    private String image;
    private String bio;
    private ArrayList<Category> categories;
    private String createdAt;
    private String updatedAt;
    private String __v;

    public Author(String name) {
        this.name = name;
    }

    public Author(String _id, String name, String image, String bio, ArrayList<Category> categories, String createdAt, String updatedAt, String __v) {
        this._id = _id;
        this.name = name;
        this.image = image;
        this.bio = bio;
        this.categories = categories;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
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

    protected Author(Parcel in) {
        _id = in.readString();
        name = in.readString();
        image = in.readString();
        bio = in.readString();
        categories = in.createTypedArrayList(Category.CREATOR);
        createdAt = in.readString();
        updatedAt = in.readString();
        __v = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(name);
        dest.writeString(image);
        dest.writeString(bio);
        dest.writeTypedList(categories);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(__v);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Author> CREATOR = new Creator<Author>() {
        @Override
        public Author createFromParcel(Parcel in) {
            return new Author(in);
        }

        @Override
        public Author[] newArray(int size) {
            return new Author[size];
        }
    };
}
