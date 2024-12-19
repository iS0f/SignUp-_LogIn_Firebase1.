package com.example.signuploginfirebase;

import android.os.Parcel;
import android.os.Parcelable;

public class SubjectModel implements Parcelable {
    private String name;
    private int credits;
    private boolean isSelected; // Status apakah mata kuliah dipilih

    public SubjectModel(String name, int credits) {
        this.name = name;
        this.credits = credits;
        this.isSelected = false; // Status default awal
    }

    protected SubjectModel(Parcel in) {
        name = in.readString();
        credits = in.readInt();
        isSelected = in.readByte() != 0; // Convert byte ke boolean
    }

    public static final Creator<SubjectModel> CREATOR = new Creator<SubjectModel>() {
        @Override
        public SubjectModel createFromParcel(Parcel in) {
            return new SubjectModel(in);
        }

        @Override
        public SubjectModel[] newArray(int size) {
            return new SubjectModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(credits);
        dest.writeByte((byte) (isSelected ? 1 : 0)); // Convert boolean ke byte
    }
}
