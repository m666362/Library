package com.rich_it.library.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable {
    private String _id;
    private String name;
    private String icon;
    private String createdAt;
    private String updatedAt;
    private String __v;

    public Category(String _id, String name, String icon, String createdAt, String updatedAt, String __v) {
        this._id = _id;
        this.name = name;
        this.icon = icon;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    protected Category(Parcel in) {
        _id = in.readString();
        name = in.readString();
        icon = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        __v = in.readString();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
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
        dest.writeString(icon);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(__v);
    }
}
