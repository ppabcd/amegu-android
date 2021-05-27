package id.rezajuliandri.amegu.data.local.entity.user;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AdopsiEntity {

    @PrimaryKey
    @ColumnInfo(name = "_id")
    private int id;

    private int userId;

    private int hewanId;

    private int status;

    private String statusText;

    public AdopsiEntity(int id, int userId, int hewanId, int status, String statusText) {
        this.id = id;
        this.userId = userId;
        this.hewanId = hewanId;
        this.status = status;
        this.statusText = statusText;
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

    public int getStatus() {
        return status;
    }

    public String getStatusText() {
        return statusText;
    }
}
