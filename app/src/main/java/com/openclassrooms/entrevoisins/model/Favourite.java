package com.openclassrooms.entrevoisins.model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "Favourites_table")
public class Favourite implements Parcelable {


    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "neighbour_id_column")
    private long n_id;

    @ColumnInfo(name = "neighbour_name")
    private String name;

    @ColumnInfo(name = "neighbour_avatarUrl")
    private String avatarUrl;

    @ColumnInfo(name = "neighbour_address")
    private String address;

    @ColumnInfo(name = "neighbour_phoneNumber")
    private String phoneNumber;

    @ColumnInfo(name = "neighbour_aboutMe")
    private String aboutMe;

    // TODO Idée d'amélioration : passer directement un objet "Neighbour" au lieu de variables.

    public Favourite(long n_id, String name, String avatarUrl, String address, String phoneNumber, String aboutMe) {
        setN_id(n_id);
        setName(name);
        setAvatarUrl(avatarUrl);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        setAboutMe(aboutMe);
    }

    public int getId() {
        return id;
    }

    public long getN_id() {
        return n_id;
    }

    public String getName() { return name; }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAboutMe() {
        return aboutMe;
    }




    public void setId(int id) {
        this.id = id;
    }

    public void setN_id(long n_id) {
        this.n_id = n_id;
    }

    public void setName(String name) { this.name = name; }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeLong(this.n_id);
        dest.writeString(this.avatarUrl);
        dest.writeString(this.address);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.aboutMe);
    }

    public Favourite() { }

    protected Favourite(Parcel in) {
        this.id = in.readInt();
        this.n_id = in.readLong();
        this.avatarUrl = in.readString();
        this.address = in.readString();
        this.phoneNumber = in.readString();
        this.aboutMe = in.readString();
    }

    public static final Creator<Favourite> CREATOR = new Creator<Favourite>() {
        @Override
        public Favourite createFromParcel(Parcel source) {
            return new Favourite(source);
        }

        @Override
        public Favourite[] newArray(int size) {
            return new Favourite[size];
        }
    };
}
