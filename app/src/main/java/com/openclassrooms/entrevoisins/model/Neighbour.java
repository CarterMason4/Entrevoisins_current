package com.openclassrooms.entrevoisins.model;


import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/**
 * Model object representing a Neighbour
 */
public class Neighbour implements Parcelable {

    /** Identifier */
    private long id;

    /** Full name */
    private String name;

    /** Avatar */
    private String avatarUrl;

    /** Adress */
    private String address;

    /** Phone number */
    private String phoneNumber;

    /** About me */
    private String aboutMe;


    /**Room priority*/
    private int priority;



    /**
     * Constructor
     * @param id
     * @param name
     * @param avatarUrl
     * @param address
     * @param phoneNumber
     * @param aboutMe
     */
    public Neighbour(long id, String name, String avatarUrl, String address,
                     String phoneNumber, String aboutMe) {
        setId(id);
        setName(name);
        setAvatarUrl(avatarUrl);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        setAboutMe(aboutMe);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

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




    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

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
    public String toString() {

        return new StringBuffer()
                .append("ID : ").append(id).append('\n')
                .append("Name : ").append(name).append('\n')
                .append("Address : ").append(address).append('\n')
                .append("Phone number : ").append(phoneNumber).append('\n')
                .append("About me : ").append(aboutMe).append('\n').
                        toString();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighbour neighbour = (Neighbour) o;
        return Objects.equals(id, neighbour.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.avatarUrl);
        dest.writeString(this.address);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.aboutMe);
    }

    protected Neighbour(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.avatarUrl = in.readString();
        this.address = in.readString();
        this.phoneNumber = in.readString();
        this.aboutMe = in.readString();
    }

    public static final Creator<Neighbour> CREATOR = new Creator<Neighbour>() {
        @Override
        public Neighbour createFromParcel(Parcel source) {
            return new Neighbour(source);
        }

        @Override
        public Neighbour[] newArray(int size) {
            return new Neighbour[size];
        }
    };
}
