package com.example.onnlineconsultancy.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Consultant implements Parcelable {
    private String name;
    private String country;
    private String service;
    private int profileImageResId;
    private String contactNumber;
    private String location;

    public Consultant(String name, String country, String service, int profileImageResId, String contactNumber, String location) {
        this.name = name;
        this.country = country;
        this.service = service;
        this.profileImageResId = profileImageResId;
        this.contactNumber = contactNumber;
        this.location = location;
    }

    protected Consultant(Parcel in) {
        name = in.readString();
        country = in.readString();
        service = in.readString();
        profileImageResId = in.readInt();
        contactNumber = in.readString();
        location = in.readString();
    }

    public static final Creator<Consultant> CREATOR = new Creator<Consultant>() {
        @Override
        public Consultant createFromParcel(Parcel in) {
            return new Consultant(in);
        }

        @Override
        public Consultant[] newArray(int size) {
            return new Consultant[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(country);
        parcel.writeString(service);
        parcel.writeInt(profileImageResId);
        parcel.writeString(contactNumber);
        parcel.writeString(location);
    }

    // Getters and setters for all fields

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getProfileImageResId() {
        return profileImageResId;
    }

    public void setProfileImageResId(int profileImageResId) {
        this.profileImageResId = profileImageResId;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
