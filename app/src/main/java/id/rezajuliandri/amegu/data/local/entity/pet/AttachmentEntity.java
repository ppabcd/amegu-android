package id.rezajuliandri.amegu.data.local.entity.pet;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity attachment pada database lokal
 */
@Entity
public class AttachmentEntity {
    @PrimaryKey
    @ColumnInfo(name = "_id")
    private final int id;
    private final int userId;
    private final int hewanId;
    private final String fileName;
    private final String mimetype;
    private final String url;
    private final String createdAt;
    private final String updatedAt;

    public AttachmentEntity(int id, int userId, int hewanId, String fileName, String mimetype, String url, String createdAt, String updatedAt) {
        this.id = id;
        this.userId = userId;
        this.hewanId = hewanId;
        this.fileName = fileName;
        this.mimetype = mimetype;
        this.url = url;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getHewanId() {
        return hewanId;
    }

    public String getFileName() {
        return fileName;
    }

    public String getMimetype() {
        return mimetype;
    }

    public String getUrl() {
        return url;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
