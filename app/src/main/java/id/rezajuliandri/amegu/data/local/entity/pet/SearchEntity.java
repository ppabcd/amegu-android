package id.rezajuliandri.amegu.data.local.entity.pet;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class SearchEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private long id;
    private String keyword;
    private long createdAt;
    private long updatedAt;

    @Ignore
    public SearchEntity(long id, String keyword, Date createdAt, Date updatedAt) {
        this.id = id;
        this.keyword = keyword;
        this.createdAt = createdAt.getTime();
        this.updatedAt = updatedAt.getTime();
    }

    public SearchEntity(String keyword, Date createdAt, Date updatedAt) {
        this.keyword = keyword;
        this.createdAt = createdAt.getTime();
        this.updatedAt = updatedAt.getTime();
    }

    public SearchEntity(String keyword, Date createdAt) {
        this.keyword = keyword;
        this.createdAt = createdAt.getTime();
        this.updatedAt = new Date().getTime();
    }

    public SearchEntity(String keyword) {
        this.keyword = keyword;
        this.createdAt = new Date().getTime();
        this.updatedAt = new Date().getTime();
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
}
