package id.rezajuliandri.amegu.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Pet implements Parcelable {
    public static final Creator<Pet> CREATOR = new Creator<Pet>() {
        @Override
        public Pet createFromParcel(Parcel in) {
            return new Pet(in);
        }

        @Override
        public Pet[] newArray(int size) {
            return new Pet[size];
        }
    };
    private long id;
    private String petName;

    public Pet(long id, String petName) {
        this.id = id;
        this.petName = petName;
    }

    public Pet(String petName) {
        this.petName = petName;
    }

    protected Pet(Parcel in) {
        id = in.readLong();
        petName = in.readString();
    }

    public static Creator<Pet> getCREATOR() {
        return CREATOR;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(petName);
    }
}
