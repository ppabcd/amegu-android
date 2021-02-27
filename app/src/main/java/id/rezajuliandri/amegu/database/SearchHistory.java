package id.rezajuliandri.amegu.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class SearchHistory implements Parcelable {

    public static final Creator<SearchHistory> CREATOR = new Creator<SearchHistory>() {
        @Override
        public SearchHistory createFromParcel(Parcel in) {
            return new SearchHistory(in);
        }

        @Override
        public SearchHistory[] newArray(int size) {
            return new SearchHistory[size];
        }
    };
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private long id;
    @ColumnInfo(name = "keyword")
    private String keyword;
    @ColumnInfo(name = "created_at")
    private long createdAt;

    @ColumnInfo(name = "updated_at")
    private long updatedAt;

    @Ignore
    public SearchHistory() {
    }

    @Ignore
    public SearchHistory(long id, String keyword, Date createdAt, Date updatedAt) {
        this.id = id;
        this.keyword = keyword;
        this.createdAt = createdAt.getTime();
        this.updatedAt = updatedAt.getTime();
    }

    public SearchHistory(String keyword, Date createdAt, Date updatedAt) {
        this.keyword = keyword;
        this.createdAt = createdAt.getTime();
        this.updatedAt = updatedAt.getTime();
    }

    public SearchHistory(String keyword, Date createdAt) {
        this.keyword = keyword;
        this.createdAt = createdAt.getTime();
        this.updatedAt = new Date().getTime();
    }

    public SearchHistory(String keyword) {
        this.keyword = keyword;
        this.createdAt = new Date().getTime();
        this.updatedAt = new Date().getTime();
    }

    protected SearchHistory(Parcel in) {
        id = in.readLong();
        keyword = in.readString();
    }

    public static Creator<SearchHistory> getCREATOR() {
        return CREATOR;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getCreateat() {
        return this.createdAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(keyword);
    }

    @NonNull
    @Override
    public String toString() {
        return this.getClass() + ": _id=" + getId() + ", keyword=" + getKeyword() + ", created_at=" + getCreatedAt() + ", updated_at=" + getUpdatedAt();
    }
}
