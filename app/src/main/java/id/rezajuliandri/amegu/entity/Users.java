package id.rezajuliandri.amegu.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Users implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private long id;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "token")
    private String token;

    @ColumnInfo(name = "alamat_id")
    private int alamatId;

    public int getAlamatId() {
        return alamatId;
    }

    public void setAlamatId(int alamatId) {
        this.alamatId = alamatId;
    }

    public static Creator<Users> getCREATOR() {
        return CREATOR;
    }

    @Ignore
    public Users() {
    }

    public Users(long id, String firstName, String lastName, String email, String token, int alamatId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.token = token;
        this.alamatId = alamatId;
    }

    @Ignore
    public Users(long id, String firstName, String lastName, String email,int alamatId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.alamatId = alamatId;
    }

    @Ignore
    public Users(String token) {
        this.token = token;
    }


    protected Users(Parcel in) {
        id = in.readLong();
        firstName = in.readString();
        lastName = in.readString();
        email = in.readString();
        token = in.readString();
        alamatId = in.readInt();
    }

    public static final Creator<Users> CREATOR = new Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel in) {
            return new Users(in);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(email);
        dest.writeString(token);
        dest.writeInt(alamatId);
    }
}
