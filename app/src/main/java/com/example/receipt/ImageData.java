package com.example.receipt;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class ImageData implements Serializable, Parcelable {

    private Uri fullPhotoUri;
    private String name;
    private String fileUriString;
    private String description;

    public ImageData(Uri fullPhotoUri) {
        this.fullPhotoUri = fullPhotoUri;
        this.name = name;
        this.fileUriString = fullPhotoUri.toString();
        this.description = "";

    }

    protected ImageData(Parcel in) {
        fullPhotoUri = in.readParcelable(Uri.class.getClassLoader());
        name = in.readString();
        fileUriString = in.readString();
        description = in.readString();
    }

    public static final Creator<ImageData> CREATOR = new Creator<ImageData>() {
        @Override
        public ImageData createFromParcel(Parcel in) {
            return new ImageData(in);
        }

        @Override
        public ImageData[] newArray(int size) {
            return new ImageData[size];
        }
    };

    public void setDescription(String description) {
        this.description = description;
    }

    public Uri getUri() {
        return fullPhotoUri;
    }

    public Uri getFullPhotoUri() {
        return fullPhotoUri.parse(fileUriString);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(fileUriString);
        dest.writeString(description);
        dest.writeParcelable(fullPhotoUri, Parcelable.CONTENTS_FILE_DESCRIPTOR);
    }
}







