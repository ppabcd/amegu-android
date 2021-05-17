package id.rezajuliandri.amegu.data.local.entity.location;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Entity kelurahan pada database lokal
 */
@Entity
public class KelurahanEntity implements Comparable<KelurahanEntity> {
    @PrimaryKey
    @ColumnInfo(name = "_id")
    private final long id;
    private final String nama;
    private long kecamatanId;

    public KelurahanEntity(long id, String nama, long kecamatanId) {
        this.id = id;
        this.nama = nama;
        this.kecamatanId = kecamatanId;
    }

    @Ignore
    public KelurahanEntity(long id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public long getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public long getKecamatanId() {
        return kecamatanId;
    }

    @Override
    public String toString() {
        return nama;
    }

    @Override
    public int compareTo(KelurahanEntity o) {
        return (int) (this.id - o.getId());
    }
}
