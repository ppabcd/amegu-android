package id.rezajuliandri.amegu.data.local.entity.location;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity Alamat pada database lokal
 */
@Entity
public class AlamatEntity {
    @PrimaryKey
    @ColumnInfo(name = "_id")
    private final long id;
    private final String alamat;
    private final String kelurahanName;
    private final long kelurahanId;
    private final String kecamatanName;
    private final long kecamatanId;
    private final String kotaName;
    private final long kotaId;
    private final String provinsiName;
    private final long provinsiId;

    public AlamatEntity(long id, String alamat, String kelurahanName, long kelurahanId, String kecamatanName, long kecamatanId, String kotaName, long kotaId, String provinsiName, long provinsiId) {
        this.id = id;
        this.alamat = alamat;
        this.kelurahanName = kelurahanName;
        this.kelurahanId = kelurahanId;
        this.kecamatanName = kecamatanName;
        this.kecamatanId = kecamatanId;
        this.kotaName = kotaName;
        this.kotaId = kotaId;
        this.provinsiName = provinsiName;
        this.provinsiId = provinsiId;
    }

    public long getId() {
        return id;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getKelurahanName() {
        return kelurahanName;
    }

    public long getKelurahanId() {
        return kelurahanId;
    }

    public String getKecamatanName() {
        return kecamatanName;
    }

    public long getKecamatanId() {
        return kecamatanId;
    }

    public String getKotaName() {
        return kotaName;
    }

    public long getKotaId() {
        return kotaId;
    }

    public String getProvinsiName() {
        return provinsiName;
    }

    public long getProvinsiId() {
        return provinsiId;
    }
}
