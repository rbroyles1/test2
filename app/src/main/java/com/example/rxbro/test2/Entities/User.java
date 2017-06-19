package com.example.rxbro.test2.Entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rxbro on 6/19/2017.
 */

public class User {

    private String name;
    private String address;
    private String email;
    private String gender;
    private String phone;
    private String cell;
    private String dateOfBirth;
    private String nationality;
    private String registered;
    private String image;

    public User() {}

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User> () {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public User(String name, String address, String email, String gender, String phone, String cell, String dateOfBirth, String nationality, String registered, String image) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.cell = cell;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.registered = registered;
        this.image = image;
    }
    private User(Parcel in) {
        name = in.readString();
        address = in.readString();
        email = in.readString();
        gender = in.readString();
        phone = in.readString();
        cell = in.readString();
        dateOfBirth = in.readString();
        nationality = in.readString();
        registered = in.readString();
        image = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(email);
        dest.writeString(gender);
        dest.writeString(phone);
        dest.writeString(cell);
        dest.writeString(dateOfBirth);
        dest.writeString(nationality);
        dest.writeString(registered);
        dest.writeString(image);
    }


}
