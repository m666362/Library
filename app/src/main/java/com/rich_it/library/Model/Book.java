package com.rich_it.library.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Book implements Parcelable {

    /*
    coverPage: {
            type: String,
        },
        name: {
            type: String,
        },
        author: {
            type: mongoose.Schema.Types.ObjectId,
            ref: 'author',
            autopopulate: { maxDepth: 1 }
        },
        owner: {
            type: mongoose.Schema.Types.ObjectId,
            ref: 'user',
            autopopulate: { maxDepth: 1 }
        },
        publication: {
            type: mongoose.Schema.Types.ObjectId,
            ref: 'publication',
            autopopulate: { maxDepth: 1 }
        },
        freePages: {
            type: String,
        },
        description: {
            type: String,
        },
        categories: [
            {
                type: mongoose.Schema.Types.ObjectId,
                ref: 'category',
                autopopulate: { maxDepth: 1 }
            }
        ],
        actualPrice: {
            type: Number,
        },
        rent: {
            type: Number,
        },
        duration: {
            type: Number,
        }

     */
    private String name;
    private String author;
    private String publication;
    private String coverPage;
    private String owner;
    private String freePages;
    private String description;
    private String categories;
    private int actualPrice;
    private int rent;
    private int duration;

    public Book(String name) {
        this.name = name;
    }

    protected Book(Parcel in) {
        name = in.readString();
        author = in.readString();
        publication = in.readString();
        coverPage = in.readString();
        owner = in.readString();
        freePages = in.readString();
        description = in.readString();
        categories = in.readString();
        actualPrice = in.readInt();
        rent = in.readInt();
        duration = in.readInt();
    }

    public Book(String name, String author, String publication, String coverPage, String owner, String freePages, String description, String categories, int actualPrice, int rent, int duration) {
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

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
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
        dest.writeString(coverPage);
        dest.writeString(owner);
        dest.writeString(freePages);
        dest.writeString(description);
        dest.writeString(categories);
        dest.writeInt(actualPrice);
        dest.writeInt(rent);
        dest.writeInt(duration);
    }
}
