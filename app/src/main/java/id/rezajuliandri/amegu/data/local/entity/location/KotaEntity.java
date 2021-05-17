package id.rezajuliandri.amegu.data.local.entity.location;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class KotaEntity implements Comparable<KotaEntity> {
    @PrimaryKey
    @ColumnInfo(name = "_id")
    long id;
    String nama;
    long provinsiId;

    @Ignore
    public KotaEntity(long id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public KotaEntity(long id, String nama, long provinsiId) {
        this.id = id;
        this.nama = nama;
        this.provinsiId = provinsiId;
    }

    public long getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public long getProvinsiId() {
        return provinsiId;
    }

    @Override
    public String toString() {
        return nama;
    }

    @Override
    public int compareTo(KotaEntity o) {
        return (int) (this.id - o.getId());
    }
}
