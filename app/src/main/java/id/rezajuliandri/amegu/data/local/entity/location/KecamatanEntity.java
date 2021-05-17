package id.rezajuliandri.amegu.data.local.entity.location;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class KecamatanEntity implements Comparable<KecamatanEntity> {
    @PrimaryKey
    @ColumnInfo(name = "_id")
    private final long id;
    private final String nama;
    private long kotaId;

    public KecamatanEntity(long id, String nama, long kotaId) {
        this.id = id;
        this.nama = nama;
        this.kotaId = kotaId;
    }

    @Ignore
    public KecamatanEntity(long id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public long getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public long getKotaId() {
        return kotaId;
    }

    @Override
    public String toString() {
        return nama;
    }

    @Override
    public int compareTo(KecamatanEntity o) {
        return (int) (this.id - o.getId());
    }
}
