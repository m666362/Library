package com.rich_it.library.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Publication implements Parcelable {

    private String _id;
    private String name;
    private ArrayList<Category> categories;
    private String createdAt;
    private String updatedAt;
    private String __v;

    public Publication(String _id, String name, ArrayList<Category> categories, String createdAt, String updatedAt, String __v) {
        this._id = _id;
        this.name = name;
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

    protected Publication(Parcel in) {
        _id = in.readString();
        name = in.readString();
        categories = in.createTypedArrayList(Category.CREATOR);
        createdAt = in.readString();
        updatedAt = in.readString();
        __v = in.readString();
    }

    public static final Creator<Publication> CREATOR = new Creator<Publication>() {
        @Override
        public Publication createFromParcel(Parcel in) {
            return new Publication(in);
        }

        @Override
        public Publication[] newArray(int size) {
            return new Publication[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(name);
        dest.writeTypedList(categories);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(__v);
    }
}
