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

    /** Dans la listes des favoris.*/
    private boolean estFavori;



    /**
     * Constructor
     * @param id
     * @param name
     * @param avatarUrl
     * @param address
     * @param phoneNumber
     * @param aboutMe
     * @param estFavori
     */
    public Neighbour(long id, String name, String avatarUrl, String address,
                     String phoneNumber, String aboutMe, boolean estFavori) {
        setId(id);
        setName(name);
        setAvatarUrl(avatarUrl);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        setAboutMe(aboutMe);
        setFavori(estFavori);
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

    public boolean estFavori() {
        return estFavori;
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

    public void setFavori(boolean estFavori) {
        this.estFavori = estFavori;
    }


    @Override
    public String toString() {

        return new StringBuffer()
                .append("ID : ").append(id).append('\n')
                .append("Name : ").append(name).append('\n')
                .append("Address : ").append(address).append('\n')
                .append("Phone number : ").append(phoneNumber).append('\n')
                .append("About me : ").append(aboutMe).append('\n')
                .append(" Est favori : ") .append(estFavori)
                .toString();

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

    protected Neighbour(Parcel in) {
        id = in.readLong();
        name = in.readString();
        avatarUrl = in.readString();
        address = in.readString();
        phoneNumber = in.readString();
        aboutMe = in.readString();
        estFavori = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(avatarUrl);
        dest.writeString(address);
        dest.writeString(phoneNumber);
        dest.writeString(aboutMe);
        dest.writeByte((byte) (estFavori ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Neighbour> CREATOR = new Creator<Neighbour>() {
        @Override
        public Neighbour createFromParcel(Parcel in) {
            return new Neighbour(in);
        }

        @Override
        public Neighbour[] newArray(int size) {
            return new Neighbour[size];
        }
    };



}
