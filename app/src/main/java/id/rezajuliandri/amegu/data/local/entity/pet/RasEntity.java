package id.rezajuliandri.amegu.data.local.entity.pet;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RasEntity implements Comparable<RasEntity> {
    @PrimaryKey
    @ColumnInfo(name = "_id")
    long id;
    String ras;
    long jenisId;

    public RasEntity(long id, String ras, long jenisId) {
        this.id = id;
        this.ras = ras;
        this.jenisId = jenisId;
    }

    public long getId() {
        return id;
    }

    public String getRas() {
        return ras;
    }

    public long getJenisId() {
        return jenisId;
    }

    @Override
    public String toString() {
        return ras;
    }

    @Override
    public int compareTo(RasEntity o) {
        return (int) (this.id - o.getId());
    }
}
