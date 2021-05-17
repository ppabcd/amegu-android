package id.rezajuliandri.amegu.data.local.entity.pet;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class JenisEntity implements Comparable<JenisEntity>{
    @PrimaryKey
    @ColumnInfo(name = "_id")
    long id;
    String jenis;

    public JenisEntity(long id, String jenis) {
        this.id = id;
        this.jenis = jenis;
    }

    public long getId() {
        return id;
    }

    public String getJenis() {
        return jenis;
    }

    @Override
    public String toString() {
        return jenis;
    }

    @Override
    public int compareTo(JenisEntity o) {
        return (int) (this.id - o.id);
    }
}
