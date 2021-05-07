package id.rezajuliandri.amegu.data.entity.location;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class Alamat implements Serializable, Parcelable {
    private final static long serialVersionUID = -4116725329306222880L;
    @PrimaryKey
    @ColumnInfo(name = "_id")
    @SerializedName("_id")
    @Expose
    private long id;

    @SerializedName("alamat")
    @Expose
    private String alamat;

    @SerializedName("kelurahanName")
    @Expose
    private String kelurahanName;

    @SerializedName("kelurahanId")
    @Expose
    private long kelurahanId;

    @SerializedName("kecamatanName")
    @Expose
    private String kecamatanName;

    @SerializedName("kecamatanId")
    @Expose
    private long kecamatanId;

    @SerializedName("kotaName")
    @Expose
    private String kotaName;

    @SerializedName("kotaId")
    @Expose
    private long kotaId;

    @SerializedName("provinsiName")
    @Expose
    private String provinsiName;

    @SerializedName("provinsiId")
    @Expose
    private long provinsiId;


    /**
     * No args constructor for use in serialization
     */
    @Ignore
    public Alamat() {
    }

    /**
     * @param kecamatanName
     * @param kotaName
     * @param kotaId
     * @param kecamatanId
     * @param provinsiName
     * @param alamat
     * @param kelurahanId
     * @param provinsiId
     * @param id
     * @param kelurahanName
     */
    public Alamat(long id, String alamat, long kelurahanId, String kelurahanName, long kecamatanId, String kecamatanName, long kotaId, String kotaName, long provinsiId, String provinsiName) {
        super();
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

    @Ignore
    protected Alamat(Parcel in) {
        id = in.readLong();
        alamat = in.readString();
        kelurahanName = in.readString();
        kelurahanId = in.readLong();
        kecamatanName = in.readString();
        kecamatanId = in.readLong();
        kotaName = in.readString();
        kotaId = in.readLong();
        provinsiName = in.readString();
        provinsiId = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(alamat);
        dest.writeString(kelurahanName);
        dest.writeLong(kelurahanId);
        dest.writeString(kecamatanName);
        dest.writeLong(kecamatanId);
        dest.writeString(kotaName);
        dest.writeLong(kotaId);
        dest.writeString(provinsiName);
        dest.writeLong(provinsiId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Alamat> CREATOR = new Creator<Alamat>() {
        @Override
        public Alamat createFromParcel(Parcel in) {
            return new Alamat(in);
        }

        @Override
        public Alamat[] newArray(int size) {
            return new Alamat[size];
        }
    };

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKelurahanName() {
        return kelurahanName;
    }

    public void setKelurahanName(String kelurahanName) {
        this.kelurahanName = kelurahanName;
    }

    public long getKelurahanId() {
        return kelurahanId;
    }

    public void setKelurahanId(long kelurahanId) {
        this.kelurahanId = kelurahanId;
    }

    public String getKecamatanName() {
        return kecamatanName;
    }

    public void setKecamatanName(String kecamatanName) {
        this.kecamatanName = kecamatanName;
    }

    public long getKecamatanId() {
        return kecamatanId;
    }

    public void setKecamatanId(long kecamatanId) {
        this.kecamatanId = kecamatanId;
    }

    public String getKotaName() {
        return kotaName;
    }

    public void setKotaName(String kotaName) {
        this.kotaName = kotaName;
    }

    public long getKotaId() {
        return kotaId;
    }

    public void setKotaId(long kotaId) {
        this.kotaId = kotaId;
    }

    public String getProvinsiName() {
        return provinsiName;
    }

    public void setProvinsiName(String provinsiName) {
        this.provinsiName = provinsiName;
    }

    public long getProvinsiId() {
        return provinsiId;
    }

    public void setProvinsiId(long provinsiId) {
        this.provinsiId = provinsiId;
    }

    @Override
    public String toString() {
        return "Alamat{" +
                "id=" + id +
                ", alamat='" + alamat + '\'' +
                ", kelurahanName='" + kelurahanName + '\'' +
                ", kelurahanId=" + kelurahanId +
                ", kecamatanName='" + kecamatanName + '\'' +
                ", kecamatanId=" + kecamatanId +
                ", kotaName='" + kotaName + '\'' +
                ", kotaId=" + kotaId +
                ", provinsiName='" + provinsiName + '\'' +
                ", provinsiId=" + provinsiId +
                '}';
    }
}
