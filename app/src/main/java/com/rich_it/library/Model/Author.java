package com.rich_it.library.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Author implements Parcelable {
    private String name;
    private String image;
    private String bio;
    ArrayList<Category> categories = new ArrayList<>();

    public Author(String name, String image, String bio, ArrayList<Category> categories) {
        this.name = name;
        this.image = image;
        this.bio = bio;
        this.categories = categories;
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

    protected Author(Parcel in) {
        name = in.readString();
        image = in.readString();
        bio = in.readString();
        categories = in.createTypedArrayList(Category.CREATOR);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(image);
        dest.writeString(bio);
        dest.writeTypedList(categories);
    }
}
