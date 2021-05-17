package id.rezajuliandri.amegu.data.local.entity.location;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ProvinsiEntity implements Comparable<ProvinsiEntity> {
    @PrimaryKey
    @ColumnInfo(name = "_id")
    private final long id;
    private final String nama;

    public ProvinsiEntity(long id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public long getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    @Override
    public String toString() {
        return nama;
    }

    @Override
    public int compareTo(ProvinsiEntity o) {
        return (int) (this.id - o.getId());
    }
}
