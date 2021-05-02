package id.rezajuliandri.amegu.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Favorite implements Parcelable {
    @PrimaryKey
    @ColumnInfo(name = "_id")
    @SerializedName("_id")
    @Expose
    private long id;

    @ColumnInfo(name = "pet_id")
    private long petId;

    @ColumnInfo(name = "created_at")
    private long createdAt;

    @ColumnInfo(name = "updated_at")
    private long updatedAt;

    protected Favorite(Parcel in) {
        id = in.readLong();
        petId = in.readLong();
        createdAt = in.readLong();
        updatedAt = in.readLong();
    }

    public Favorite(long id, long petId, long createdAt, long updatedAt) {
        this.id = id;
        this.petId = petId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static final Creator<Favorite> CREATOR = new Creator<Favorite>() {
        @Override
        public Favorite createFromParcel(Parcel in) {
            return new Favorite(in);
        }

        @Override
        public Favorite[] newArray(int size) {
            return new Favorite[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static Creator<Favorite> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(petId);
        dest.writeLong(createdAt);
        dest.writeLong(updatedAt);
    }
}
