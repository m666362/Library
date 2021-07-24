package com.rich_it.library.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class User implements Parcelable, Serializable {
    private String _id;
    private String name;
    private String email;
    private String location;
    private String physicalAddress;
    private String phoneNumber;
    private String imageUrl;
    private String referCode;
    private Boolean isValidReferCode;
    private String password;
    private String status;
    private String createdAt;
    private String updatedAt;
    private String __v;

    public User(String name) {
        this.name = name;
    }

    public User(String name, String email, String location, String phoneNumber, String password) {
        this.name = name;
        this.email = email;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public User(String _id, String name, String email, String location, String physicalAddress, String phoneNumber, String imageUrl, String referCode, Boolean isValidReferCode, String password, String status, String createdAt, String updatedAt, String __v) {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.location = location;
        this.physicalAddress = physicalAddress;
        this.phoneNumber = phoneNumber;
        this.imageUrl = imageUrl;
        this.referCode = referCode;
        this.isValidReferCode = isValidReferCode;
        this.password = password;
        this.status = status;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getReferCode() {
        return referCode;
    }

    public void setReferCode(String referCode) {
        this.referCode = referCode;
    }

    public Boolean getValidReferCode() {
        return isValidReferCode;
    }

    public void setValidReferCode(Boolean validReferCode) {
        isValidReferCode = validReferCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    protected User(Parcel in) {
        _id = in.readString();
        name = in.readString();
        email = in.readString();
        location = in.readString();
        physicalAddress = in.readString();
        phoneNumber = in.readString();
        imageUrl = in.readString();
        referCode = in.readString();
        byte tmpIsValidReferCode = in.readByte();
        isValidReferCode = tmpIsValidReferCode == 0 ? null : tmpIsValidReferCode == 1;
        password = in.readString();
        status = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        __v = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
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
        dest.writeString(email);
        dest.writeString(location);
        dest.writeString(physicalAddress);
        dest.writeString(phoneNumber);
        dest.writeString(imageUrl);
        dest.writeString(referCode);
        dest.writeByte((byte) (isValidReferCode == null ? 0 : isValidReferCode ? 1 : 2));
        dest.writeString(password);
        dest.writeString(status);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(__v);
    }
}
